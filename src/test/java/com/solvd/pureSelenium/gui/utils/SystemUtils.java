package com.solvd.pureSelenium.gui.utils;

public class SystemUtils {

    public static String getEnvVariable(String variable) {
        String value = System.getenv(variable);
        if (value == null) {
            throw new IllegalStateException(variable + " not set");
        }
        return value;
    }
}
