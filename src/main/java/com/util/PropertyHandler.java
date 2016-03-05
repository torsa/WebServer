package com.util;

import java.io.IOException;
import java.util.Properties;


/**
 * This is a Property Utility class that could be used to read configuration.
 *
 * @author Torsa Das
 */
public class PropertyHandler {

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

    public String getValue(String propKey) {
        return this.properties.getProperty(propKey);
    }
}
