package com.util;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WebServerUtilTest {

    @Test
    public void testMIMEType(){
        assertEquals("text/html",WebServerUtil.getMIMEType(".html"));
        assertEquals("text/plain",WebServerUtil.getMIMEType(".txt"));
        assertEquals("application/octet-stream",WebServerUtil.getMIMEType(".class"));
        assertEquals("image/gif",WebServerUtil.getMIMEType(".gif"));
        assertEquals("image/jpeg",WebServerUtil.getMIMEType(".jpeg"));
        assertEquals("text/plain",WebServerUtil.getMIMEType(".java"));
        assertEquals("text/xml",WebServerUtil.getMIMEType(".xml"));
    }

    @Test
    public void testDefaultMIMEType(){
         assertEquals("text/plain",WebServerUtil.getMIMEType("aaa"));
    }
}
