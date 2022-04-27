package net.intelliboard.next;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import net.intelliboard.next.services.PropertiesGetValue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;


public abstract class AbstractTest {

    protected static PropertiesGetValue propertiesGetValue = new PropertiesGetValue();

    @BeforeEach
    public void setUp() throws IOException {

        switch (propertiesGetValue.getPropertyValue("browser")) {

            case "remote":
                DesiredCapabilities cap = new DesiredCapabilities();
                cap.setAcceptInsecureCerts(true);
                cap.setBrowserName("chrome");
                cap.setVersion("99.0");
                cap.setCapability("enableVNC", true);
                cap.setCapability("enableVideo", true);
                Configuration.browserCapabilities = cap;
                Configuration.remote = "http://localhost:4444/wd/hub";

            case "chrome":

        }

//        Configuration.browser = propertiesGetValue.getPropertyValue("browser");
        Configuration.timeout = 20000;
        Configuration.browserSize = "1600x1200";
//        if (WebDriverRunner.isFirefox()) {
//            WebDriverManager.firefoxdriver().driverVersion("0.30.0").setup();
//        }
        WebDriverRunner.clearBrowserCache();
    }

    @AfterEach
    public void tearDown() {
        WebDriverRunner.driver().close();
    }


}
