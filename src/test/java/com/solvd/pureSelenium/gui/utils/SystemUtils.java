package com.solvd.pureSelenium.gui.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SystemUtils {

    private static final String CONFIG_FILE = "gui/user.properties";

    private static final Properties PROPERTIES = new Properties();

    static {
        try (InputStream is = SystemUtils.class
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

    private SystemUtils() {
    }

    public static String getTestEmail() {
        return getEnvVariable(getCredential("test_email"));
    }

    private static String getCredential(String propertyKey) {
        return System.getProperty(
                propertyKey,
                PROPERTIES.getProperty(propertyKey)
        );
    }

    private static String getEnvVariable(String variable) {
        String value = System.getenv(variable);
        if (value == null) {
            throw new IllegalStateException(variable + " not set");
        }
        return value;
    }

    public static String getTestPassword() {
        return getEnvVariable(getCredential("test_password"));
    }
}
