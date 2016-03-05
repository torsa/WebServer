package com.util;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by dtorsa on 5/03/2016.
 */
public class PropertyHandler{

    private static PropertyHandler instance = null;

    private Properties properties = null;

    private PropertyHandler() throws IOException {
        properties = new Properties();
        properties.load(getClass().getClassLoader().getResourceAsStream("config.properties"));

    }

    public static synchronized PropertyHandler getInstance() throws IOException {
        if (instance == null)
            instance = new PropertyHandler();
        return instance;
    }

    public String getValue(String propKey){
        return this.properties.getProperty(propKey);
    }
}
