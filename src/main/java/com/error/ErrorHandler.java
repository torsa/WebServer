package com.error;

import org.apache.log4j.Logger;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Date;

/**
 * This is the general Error Handler class for the Web Server.
 * This could be enhanced for further Error handling
 * @author Torsa Das
 */
public class ErrorHandler {

    final static Logger logger = Logger.getLogger(ErrorHandler.class);


    public static void handleBadRequest(PrintStream printStream, Socket connection,
                                        String code, String title, String msg)
    {
        printStream.print("HTTP/1.0 " + code + " " + title + "\r\n" +
                "\r\n" +
                "<!DOCTYPE HTML PUBLIC \"-//IETF//DTD HTML 2.0//EN\">\r\n" +
                "<TITLE>" + code + " " + title + "</TITLE>\r\n" +
                "</HEAD><BODY>\r\n" +
                "<H1>" + title + "</H1>\r\n" + msg + "<P>\r\n" +
                "<HR><ADDRESS>FileServer 1.0 at " +
                connection.getLocalAddress().getHostName() +
                " Port " + connection.getLocalPort() + "</ADDRESS>\r\n" +
                "</BODY></HTML>\r\n");
        log(connection, code + " " + title);
    }

    private static void log(Socket connection, String msg)
    {
        logger.error(new Date() + " [" + connection.getInetAddress().getHostAddress() +
                ":" + connection.getPort() + "] " + msg);
    }
}
