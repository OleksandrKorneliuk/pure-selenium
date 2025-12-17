package com.solvd.pureSelenium.gui.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final String CONFIG_FILE = "_config.properties";

    private static final Properties PROPERTIES = new Properties();

    static {
        try (InputStream is = ConfigReader.class
                .getClassLoader()
                .getResourceAsStream(CONFIG_FILE)) {

            if (is == null) {
                throw new RuntimeException(CONFIG_FILE + " not found");
            }

            PROPERTIES.load(is);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load " + CONFIG_FILE, e);
        }
    }

    private ConfigReader() {}

    public static String getBaseUrl() {
        return System.getProperty(
                "base_url",
                PROPERTIES.getProperty("base_url")
        );
    }
}
