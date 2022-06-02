package net.intelliboard.next;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import net.intelliboard.next.services.ConsoleColors;
import net.intelliboard.next.services.PropertiesGetValue;
import net.intelliboard.next.services.login.LoginCookieHandler;
import net.intelliboard.next.services.looger.TestsWatcherImpl;
import net.intelliboard.next.services.webdriver.WebDriverService;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;

@ExtendWith(TestsWatcherImpl.class)
public abstract class AbstractTest {

    public static PropertiesGetValue propertiesGetValue = new PropertiesGetValue();
    private static final Logger LOGGER = Logger.getLogger("AbstractTest");

    @BeforeEach
    public void setUp(TestInfo testInfo) throws IOException {

        LOGGER.info(String.format("Test started : %s", testInfo.getDisplayName()));

        WebDriverService driver = new WebDriverService();
        driver.initWebDriver(propertiesGetValue.getPropertyValue("browser"),
                Integer.valueOf(propertiesGetValue.getPropertyValue("timeout")),
                propertiesGetValue.getPropertyValue("resolution"));
        LoginCookieHandler.run();
    }

    @AfterEach
    public void tearDown(TestInfo testInfo) {
        LOGGER.info(String.format("Test finished : %s", testInfo.getDisplayName()));
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
        WebDriverRunner.driver().close();
    }

    @AfterAll
    public static void wrapUp() {
        System.out.println(String.format("--------------------- %sTEST RESULTS%s -----------------------------", ConsoleColors.BLACK_BACKGROUND, ConsoleColors.RESET));
        TestsWatcherImpl.testResultsStatus.forEach((k, v) -> System.out.println(String.format("| %s | %s |", k, v)));
        System.out.println("----------------------------------------------------------------");
        LoginCookieHandler.setIsCookieSet(false);
    }
}
