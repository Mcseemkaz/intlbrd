package net.intelliboard.next.services.helpers;

import com.codeborne.selenide.Configuration;
import net.intelliboard.next.services.PropertiesGetValue;

import java.io.FileOutputStream;
import java.util.Properties;

public class AllureReportEnvPropertiesManager {

    public static void allureEnvPropertiesWrite() {

        PropertiesGetValue propertiesGetValue = new PropertiesGetValue();

        String path = System.getProperty("allure.results.directory");
        try {
            Properties props = new Properties();
            FileOutputStream fos = new FileOutputStream(path + "/environment.properties");
            props.setProperty("Browser", Configuration.browser);
            props.setProperty("Browser Version", Configuration.browserCapabilities.getBrowserVersion());
            props.setProperty("URL", propertiesGetValue.getPropertyValue("base_url"));
            props.setProperty("User_Login", propertiesGetValue.getPropertyValue("user_login"));
            props.setProperty("User_Password", propertiesGetValue.getPropertyValue("user_pass"));
            props.store(fos, "See https://github.com/allure-framework/allure-app/wiki/Environment");
            fos.close();

        } catch (Exception e) {
            System.err.println("IO problem when writing allure properties file");
            e.printStackTrace();
        }
    }
}
