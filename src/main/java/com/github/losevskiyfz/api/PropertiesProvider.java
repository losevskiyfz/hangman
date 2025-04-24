package com.github.losevskiyfz.api;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesProvider {
    private static final Properties PROPERTIES = new Properties();

    static {
        try (InputStream input = PropertiesProvider.class.getClassLoader()
                .getResourceAsStream("application.properties")) {
            PROPERTIES.load(input);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String get(String property) {
        return PROPERTIES.getProperty(property);
    }
}