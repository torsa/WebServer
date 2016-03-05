package com.validator;


import com.error.ErrorCode;
import com.error.ErrorHandler;
import org.junit.Test;
import org.mockito.Mockito;
import java.io.PrintStream;
import java.net.Socket;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;

public class GetRequestValidatorTest {

   @Test
    public void testValidGetRequest(){
       PrintStream printStream = Mockito.mock(PrintStream.class);
       Socket socket = Mockito.mock(Socket.class);
       String request="GET / HTTP/1.1";
       assertEquals(true,GetRequestValidator.validateGetRequest(request,printStream,socket));

    }

}
