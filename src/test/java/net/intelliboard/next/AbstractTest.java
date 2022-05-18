package net.intelliboard.next;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import net.intelliboard.next.services.ConsoleColors;
import net.intelliboard.next.services.PropertiesGetValue;
import net.intelliboard.next.services.TestsWatcherImpl;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;

@ExtendWith(TestsWatcherImpl.class)
public abstract class AbstractTest {

    public static PropertiesGetValue propertiesGetValue = new PropertiesGetValue();
    private static final Logger LOGGER = Logger.getLogger("AbstractTest");
    @BeforeEach
    public void setUp(TestInfo testInfo) throws IOException {

        LOGGER.info(String.format("Test started : %s", testInfo.getDisplayName()));

        switch (propertiesGetValue.getPropertyValue("browser")) {

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
        Configuration.browserSize = "1600x1200";
        Configuration.timeout = 20000;
    }

    @AfterEach
    public void tearDown(TestInfo testInfo) {
        LOGGER.info(String.format("Test finished : %s", testInfo.getDisplayName()));
        WebDriverRunner.driver().close();
    }

    @AfterAll
    public static void rumpUp(){
        System.out.println(String.format("--------------------- %sTEST RESULTS%s -----------------------------", ConsoleColors.BLACK_BACKGROUND,ConsoleColors.RESET));
        TestsWatcherImpl.testResultsStatus.forEach((k,v) -> System.out.println(String.format("| %s | %s |", k, v)));
        System.out.println("----------------------------------------------------------------");
    }
}
