package com.factory;

import com.service.ApplicationService;
import com.service.directory.DirectoryListingService;

import java.io.BufferedReader;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * This is the general Error Handler class for the Web Server.
 * This could be enhanced for further Error handling
 * @author Torsa Das
 */
public class ServiceFactory{

        public static ApplicationService getService(OutputStream outputStream, PrintStream printStream){

                return new DirectoryListingService(outputStream,printStream);
        }
}
