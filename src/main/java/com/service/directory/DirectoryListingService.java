package com.service.directory;

import com.error.ErrorCode;
import com.error.ErrorHandler;
import com.server.WebServer;
import com.service.ApplicationService;
import com.util.PropertyHandler;
import com.util.WebServerUtil;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.Socket;
import java.util.Date;

/**
 * This is the Directory listing Service which displays the folder structure or the file.
 *
 * @author Torsa Das
 */
public class DirectoryListingService implements ApplicationService {

    final static Logger logger = Logger.getLogger(WebServer.class);

    private OutputStream outputStream;
    private PrintStream printStream;
    private String directory;
    private  ErrorHandler errorHandler =new ErrorHandler();
    public DirectoryListingService(OutputStream out, PrintStream print) {
        this.outputStream = out;
        this.printStream = print;
    }

    /**
     * This method is used to service the request based on whether a file or a directory has been requested.
     *
     * @param connection
     * @param request
     */
    public void doService(Socket connection, String request) {

        try {
            String req = request.substring(4, request.length() - 9).trim();
            String path = System.getProperty("user.dir") + "/" + req;
            File f = new File(path);
            if (f.isDirectory()) {
                printStream.print("HTTP/1.0 200 OK\r\n" +
                        "Content-Type: text/html\r\n" +
                        "Date: " + new Date() + "\r\n" +
                        "Server: FileServer 1.0\r\n\r\n");
                displayDirectoryListing(f, printStream);
                logger.info("Directory displayed Content 200 OK");

            } else {
                try {
                    // send file
                    InputStream file = new FileInputStream(f);
                    printStream.print("HTTP/1.0 200 OK\r\n" +
                            "Content-Type: " + WebServerUtil.getMIMEType(path) + "\r\n" +
                            "Date: " + new Date() + "\r\n" +
                            "Server: FileServer 1.0\r\n\r\n");
                    sendFile(file, outputStream);
                    logger.info("File sent Content 200 OK");
                } catch (FileNotFoundException e) {
                    // file not found
                    ErrorHandler.handleError(printStream, connection, ErrorCode.NOT_FOUND,
                            "The requested URL was not found on this erver.");
                }
            }
            printStream.flush();
        } catch (IOException iOException) {
            logger.error("Unexpected Exception", iOException);
        }
    }

    /**
     * This methods sends the file back as a response
     *
     * @param file
     * @param out
     */
    private static void sendFile(InputStream file, OutputStream out) throws IOException {

        byte[] buffer = new byte[1000];
        while (file.available() > 0)
            out.write(buffer, 0, file.read(buffer));

    }

    /**
     * This method builds the folder structure
     *
     * @param dir
     * @param pr
     * @throws IOException
     */
    private static void displayDirectoryListing(File dir, OutputStream pr) throws IOException {

        File[] filesList = dir.listFiles();
        pr.write(new String("<html><head><h1>" + "Directory Listing" + "</h1></head><body><HR><BR>").getBytes());
        pr.write(new String("<Table border=2>").getBytes());
        pr.write(new String("<TR>").getBytes());
        pr.write(new String("<TD><strong>Name</strong></TD>").getBytes());
        pr.write(new String("</TR>").getBytes());
        for (File file : filesList) {
            pr.write(new String("<TR>").getBytes());
            if (file.isFile()) {
                pr.write(new String("<TD><a href=\"" + file.getName() + "\">" + file.getName() + "</a></TD>").getBytes());
            } else {
                pr.write(new String("<TD><a href=\"" + file.getName() + "/\">" + file.getName() + "</a></TD>").getBytes());

            }
            pr.write(new String("</TR>").getBytes());
        }

    }

}
