package com.validator;

import com.error.ErrorCode;
import com.error.ErrorHandler;

import java.io.PrintStream;
import java.net.Socket;

/**
 * This is the validator class for validating a GET Request
 *
 * @author Torsa Das
 */
public class GetRequestValidator {

    /**
     * This method checks whether the request is a valid GET . This method could be enhanced to check
     * other conditions of faliure
     *
     * @param request
     * @param printStream
     * @param connection
     * @return
     */
    public static boolean validateGetRequest(String request, PrintStream printStream, Socket connection) {
        boolean isValid = true;
        if (!request.startsWith("GET") || request.length() < 14 ||
                !(request.endsWith("HTTP/1.0") || request.endsWith("HTTP/1.1"))) {
            // bad request
            ErrorHandler.handleError(printStream, connection, ErrorCode.BAD_REQUEST,
                    "Your browser sent a request that " +
                            "this server could not understand.");
            printStream.flush();
            isValid = false;
        }
        return isValid;
    }
}
