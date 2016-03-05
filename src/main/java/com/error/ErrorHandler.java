package com.error;

import org.apache.log4j.Logger;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Date;

/**
 * This is the general Error Handler class for the Web Server.
 * This could be enhanced for further Error handling
 *
 * @author Torsa Das
 */
public class ErrorHandler {

    final static Logger logger = Logger.getLogger(ErrorHandler.class);


    public static void handleError(PrintStream printStream, Socket connection,
                                        ErrorCode error, String msg) {
        printStream.print("HTTP/1.0 " + error.getCode() + " " +  error.getErrorName() + "\r\n" +
                "\r\n" +
                "<!DOCTYPE HTML PUBLIC \"-//IETF//DTD HTML 2.0//EN\">\r\n" +
                "<TITLE>" +  error.getCode() + " " +  error.getErrorName()+ "</TITLE>\r\n" +
                "</HEAD><BODY>\r\n" +
                "<H1>" +  error.getCode() + "</H1>\r\n" + msg + "<P>\r\n" +
                "<HR><ADDRESS>FileServer 1.0 at " +
                connection.getLocalAddress().getHostName() +
                " Port " + connection.getLocalPort() + "</ADDRESS>\r\n" +
                "</BODY></HTML>\r\n");
        log(connection,  error.getCode() + " " +  error.getErrorName());
    }

    private static void log(Socket connection, String msg) {
        logger.error(new Date() + " [" + connection.getInetAddress().getHostAddress() +
                ":" + connection.getPort() + "] " + msg);
    }
}
