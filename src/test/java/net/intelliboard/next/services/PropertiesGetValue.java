package net.intelliboard.next.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesGetValue {
    String result = "";
    InputStream inputStream;

    public String getPropertyValue(String key) throws IOException {

        try {
            String configFileName = "./%s-config.properties";
            String EnvironmentName = System.getProperty("TestEnvironment");
            configFileName = String.format(configFileName, EnvironmentName);

            Properties prop = new Properties();
            inputStream = getClass().getClassLoader().getResourceAsStream(configFileName);
            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + configFileName + "' not found in the classpath");
            }

            result = prop.getProperty(key);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            inputStream.close();
        }
        return result;
    }
}

