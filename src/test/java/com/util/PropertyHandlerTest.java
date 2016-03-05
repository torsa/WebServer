package com.util;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Created by dtorsa on 6/03/2016.
 */
public class PropertyHandlerTest {

    @Test
    public void testGetValue()throws IOException{
        assertEquals("9000",PropertyHandler.getInstance().getValue("port"));
    }
}
