package net.intelliboard.next;

import net.intelliboard.next.services.ConsoleColors;
import net.intelliboard.next.services.PropertiesGetValue;
import net.intelliboard.next.services.login.LoginCookieHandler;
import net.intelliboard.next.services.looger.TestsWatcherImpl;
import net.intelliboard.next.services.webdriver.WebDriverService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@ExtendWith(TestsWatcherImpl.class)

public abstract class AbstractTest {

    static Logger logger = LoggerFactory.getLogger(AbstractTest.class);
    public static PropertiesGetValue propertiesGetValue = new PropertiesGetValue();
    public static Long WAIT_TIMEOUT_LONG;

    static {
        try {
            WAIT_TIMEOUT_LONG = Long.parseLong(AbstractTest.propertiesGetValue.getPropertyValue("waiting_time_seconds"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeEach
    public void setUp(TestInfo testInfo) throws IOException {
        logger.info(String.format("Test started : %s", testInfo.getDisplayName()));
        WebDriverService driver = new WebDriverService();
        driver.initWebDriver(
                propertiesGetValue.getPropertyValue("browser"),
                Integer.parseInt(propertiesGetValue.getPropertyValue("timeout")),
                propertiesGetValue.getPropertyValue("resolution"), testInfo);
        LoginCookieHandler.run();
    }

    @AfterEach
    public void tearDown(TestInfo testInfo) {
        logger.info(String.format("Test finished : %s", testInfo.getDisplayName()));
    }

    @AfterAll
    public static void wrapUp() {
        System.out.println(String.format("--------------------- %sTEST RESULTS%s -----------------------------%n",
                ConsoleColors.BLACK_BACKGROUND, ConsoleColors.RESET));
        TestsWatcherImpl.testResultsStatus.forEach((k, v) -> System.out.printf("| %s | %s |%n", k, v));
        System.out.println("----------------------------------------------------------------");
        LoginCookieHandler.setIsCookieSet(false);

    }
}
