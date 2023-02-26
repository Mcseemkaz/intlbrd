package net.intelliboard.next.services.webdriver;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WebDriverService {

    @Step("Init Web Driver")
    public void initWebDriver(String type, int timeout, String resolution, TestInfo testInfo) {

        switch (type) {

            case "remote":
                DesiredCapabilities cap = new DesiredCapabilities();
                String testName = String.format("%s | %s | %s", testInfo.getDisplayName(),
                        LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME), System.getProperty("TestEnvironment").toUpperCase());
                cap.setAcceptInsecureCerts(true);
                cap.setBrowserName("chrome");
                cap.setVersion("110.0");
                cap.setCapability("enableVNC", true);
                cap.setCapability("enableVideo", false);
                cap.setCapability("name", testName);
                cap.setCapability("videoName", testName);
//                cap.setCapability("pageLoadStrategy","none");
                Configuration.browserCapabilities = cap;
                Configuration.remote = "http://localhost:4444/wd/hub";

                break;

            case "chrome":
                Configuration.browser = "chrome";
                break;

            case "firefox":
                Configuration.browser = "firefox";
                WebDriverManager.firefoxdriver().driverVersion("0.30.0").setup();
                break;

            default:
        }
        WebDriverRunner.clearBrowserCache();
        Configuration.browserSize = resolution;
        Configuration.timeout = timeout;
    }
}
