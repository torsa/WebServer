package com.com.service.directory;

import com.service.directory.DirectoryListingService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

public class DirectoryListingServiceTest {
    DirectoryListingService directoryListingService;
    @Before
    public void setup(){
        OutputStream outputStrem = Mockito.mock(OutputStream.class);
        PrintStream printStream = Mockito.mock(PrintStream.class);
        directoryListingService=new DirectoryListingService(outputStrem,printStream);
    }

    @Test
    public void testDoService(){
        String request="GET / HTTP/1.1";
        Socket socket = Mockito.mock(Socket.class);
        directoryListingService.doService(socket,request);

    }
}
