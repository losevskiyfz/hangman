package com.github.losevskiyfz.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesProvider {
    private static final Logger LOG = LogManager.getLogger(PropertiesProvider.class);
    private static final Properties PROPERTIES = new Properties();

    static {
        LOG.info("Loading application.properties to the project.");
        try (InputStream input = PropertiesProvider.class.getClassLoader()
                .getResourceAsStream("application.properties")) {
            PROPERTIES.load(input);
        } catch (Exception e) {
            LOG.error(e);
            throw new RuntimeException(e);
        }
    }

    public static String get(String property) {
        return PROPERTIES.getProperty(property);
    }
}