package net.intelliboard.next.services.webdriver;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.remote.DesiredCapabilities;

public class WebDriverService {

    public void initWebDriver(String type, int timeout, String resolution) {

        switch (type) {

            case "remote":
                DesiredCapabilities cap = new DesiredCapabilities();
                cap.setAcceptInsecureCerts(true);
                cap.setBrowserName("chrome");
                cap.setVersion("99.0");
                cap.setCapability("enableVNC", true);
                cap.setCapability("enableVideo", false);
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
