package com.immortal.half.wu.utils;

import java.io.File;

public class FileUtil {

    private static final String DIR_ROOT = System.getProperty("user.dir");
    private static final String DIR_NAME_CONFIG = "config";

    private static final String FILE_NAME_CONFIG_LOG4J = "log4j2.xml";


    public static String getPathForLog4gConfig () {
        return appendString(DIR_ROOT, DIR_NAME_CONFIG, FILE_NAME_CONFIG_LOG4J);
    }


    private static String appendString(String... strings) {
        if (strings.length == 0) {
            return "";
        }

        StringBuilder builder = new StringBuilder();
        for (String iString : strings) {
            builder.append(iString);
            builder.append(File.separator);
        }
        builder.trimToSize();
        return builder.toString();
    }
}
