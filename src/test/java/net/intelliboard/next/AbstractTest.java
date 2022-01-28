package net.intelliboard.next;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import net.intelliboard.next.services.PropertiesGetValue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;


public abstract class AbstractTest {

    protected static PropertiesGetValue propertiesGetValue = new PropertiesGetValue();

    @BeforeEach
    public void setUp() throws IOException {
        Configuration.browser = propertiesGetValue.getPropertyValue("browser");
        Configuration.timeout = 10000;
        Configuration.browserSize = "1600x1200";
        WebDriverRunner.clearBrowserCache();
    }

    @AfterEach
    public void tearDown() {
        WebDriverRunner.driver().close();
    }


}
