package com.util;

/**
 * This is an utility class.
 *
 * @author Torsa Das
 */
public class WebServerUtil {

    /**
     * This method is used to return the MIME type
     *
     * @param path
     * @return
     */
    public static String getMIMEType(String path) {
        if (path.endsWith(".html") || path.endsWith(".htm"))
            return "text/html";
        else if (path.endsWith(".txt") || path.endsWith(".java"))
            return "text/plain";
        else if (path.endsWith(".gif"))
            return "image/gif";
        else if (path.endsWith(".class"))
            return "application/octet-stream";
        else if (path.endsWith(".jpg") || path.endsWith(".jpeg"))
            return "image/jpeg";
        else if ( path.endsWith(".xml"))
            return "text/xml";
        else
            return "text/plain";
    }
}
