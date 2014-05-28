package com.harshitha.calendar.operation;

import java.util.ResourceBundle;

public class PropertyReaderUtil {

    public static String getProperty(String key) {
        String value = null;
        ResourceBundle bundle = ResourceBundle.getBundle("com.harshitha.calendar.operation.application");
        return bundle.getString(key);
    }
}
