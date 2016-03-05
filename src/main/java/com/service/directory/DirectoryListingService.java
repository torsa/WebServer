package com.service.directory;

import com.server.WebServer;
import com.error.ErrorHandler;
import com.service.ApplicationService;
import com.util.FileUtil;
import com.util.PropertyHandler;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.Socket;
import java.util.Date;

public class DirectoryListingService implements ApplicationService {

    final static Logger logger = Logger.getLogger(WebServer.class);

    private OutputStream outputStream;
    private PrintStream printStream;


    public DirectoryListingService(OutputStream out,PrintStream print){
         this.outputStream = out;
         this.printStream = print;
    }
    private static void log(Socket connection, String msg)
    {
        logger.error(new Date() + " [" + connection.getInetAddress().getHostAddress() +
                ":" + connection.getPort() + "] " + msg);
    }

      public void doService(Socket connection,String request){

         try{
                String req = request.substring(4, request.length()-9).trim();

                String path = PropertyHandler.getInstance().getValue("directory") + "/" + req;
                File f = new File(path);

                    if (f.isDirectory()) {
                        // if directory, implicitly add 'index.html'
                        printStream.print("HTTP/1.0 200 OK\r\n" +
                                "Content-Type: text/html\r\n" +
                                "Date: " + new Date() + "\r\n" +
                                "Server: FileServer 1.0\r\n\r\n");
                        displayDirectoryListing(f, printStream);


                    } else {
                        try {
                            // send file
                            InputStream file = new FileInputStream(f);
                            printStream.print("HTTP/1.0 200 OK\r\n" +
                                    "Content-Type: " + FileUtil.guessContentType(path) + "\r\n" +
                                    "Date: " + new Date() + "\r\n" +
                                    "Server: FileServer 1.0\r\n\r\n");
                            sendFile(file, outputStream); // send raw file
                            log(connection, "200 OK");
                        } catch (FileNotFoundException e) {
                            // file not found
                            ErrorHandler.handleBadRequest(printStream, connection, "404", "Not Found",
                                    "The requested URL was not found on this erver.");
                        }
                    }


           printStream.flush();
        } catch (IOException e) { System.err.println(e); }
        try {
            if (connection != null) connection.close();
        } catch (IOException e) { System.err.println(e); }
    }

    private static void sendFile(InputStream file, OutputStream out)
    {
        try {
            byte[] buffer = new byte[1000];
            while (file.available()>0)
                out.write(buffer, 0, file.read(buffer));
        } catch (IOException e) { System.err.println(e); }
    }

    private static void displayDirectoryListing(File dir, OutputStream pr) throws IOException {

        File[] filesList = dir.listFiles();
        for (File file : filesList) {
            if (file.isFile()) {
                pr.write(new String("<li><a href=\""+file.getName() +"\">" + file.getName() + "</a></li>").getBytes());
            } else {
                pr.write(new String("<li><a href=\""+file.getName() +"\">" + file.getName() + "</a></li>").getBytes());
            }
        }

    }






}
