package com.com.service.directory;


import com.service.directory.DirectoryListingService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;


import static org.junit.Assert.assertNotNull;


public class DirectoryListingServiceTest {
    DirectoryListingService directoryListingService;
    PrintStream printStream;
    OutputStream outputStrem;

    @Before
    public void setup() {
        outputStrem = Mockito.mock(OutputStream.class);
        printStream = Mockito.mock(PrintStream.class);
        directoryListingService = new DirectoryListingService(outputStrem, printStream);
    }

    @Test
    public void testDoService() {
        String request = "GET / HTTP/1.1";
        Socket socket = Mockito.mock(Socket.class);
        directoryListingService.doService(socket, request);
        assertNotNull(printStream);
    }
}
