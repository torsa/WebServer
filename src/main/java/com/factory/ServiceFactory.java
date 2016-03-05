package com.factory;

import com.service.ApplicationService;
import com.service.directory.DirectoryListingService;

import java.io.BufferedReader;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * This is the Service Factory
 * This could be enhanced for further services like Directory
 *
 * @author Torsa Das
 */
public class ServiceFactory {

    public static ApplicationService getService(OutputStream outputStream, PrintStream printStream) {
        return new DirectoryListingService(outputStream, printStream);
    }
}
