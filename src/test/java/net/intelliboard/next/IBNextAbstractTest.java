package net.intelliboard.next;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import net.intelliboard.next.services.IBNextURLs;
import net.intelliboard.next.services.pages.LoginPage;
import org.assertj.core.api.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;

public class IBNextAbstractTest extends AbstractTest {

    protected static String USER_LOGIN;
    protected static String USER_PASS;
    protected static String MOODLE_CLIENT_ID;
    protected static String MOODLE_LMS_URL;
    protected static String SIGNUP_INVITE_REGISTRATION_CODE;
    protected static String CANVAS_CLIENT_ID;
    protected static String CANVAS_LMS_URL;
    protected static String CANVAS_CLIENT_SECRET;
    protected static String CANVAS_DATA_CLIENT_ID;
    protected static String CANVAS_DATA_CLIENT_SECRET;
    protected static String CANVAS_USER_LOGIN;
    protected static String CANVAS_USER_PASS;
    protected static String BLACKBOARD_CLIENT_ID;
    protected static String BLACKBOARD_LMS_URL;

    static {
        try {
            USER_LOGIN = propertiesGetValue.getPropertyValue("user_login");
            USER_PASS = propertiesGetValue.getPropertyValue("user_pass");
            SIGNUP_INVITE_REGISTRATION_CODE = propertiesGetValue.getPropertyValue("invite_code");
            MOODLE_CLIENT_ID = propertiesGetValue.getPropertyValue("moodel_client_id");
            MOODLE_LMS_URL = propertiesGetValue.getPropertyValue("moodel_lms_url");
            CANVAS_CLIENT_ID = propertiesGetValue.getPropertyValue("canvas_client_id");
            CANVAS_LMS_URL = propertiesGetValue.getPropertyValue("canvas_lms_url");
            CANVAS_CLIENT_SECRET = propertiesGetValue.getPropertyValue("canvas_client_secret");
            CANVAS_DATA_CLIENT_ID = propertiesGetValue.getPropertyValue("canvas_data_client_id");
            CANVAS_DATA_CLIENT_SECRET = propertiesGetValue.getPropertyValue("canvas_data_client_secret");
            CANVAS_USER_LOGIN = propertiesGetValue.getPropertyValue("canvas_user_login");
            CANVAS_USER_PASS = propertiesGetValue.getPropertyValue("canvas_user_pass");
            BLACKBOARD_LMS_URL = propertiesGetValue.getPropertyValue("blackboard_lms_url");
            BLACKBOARD_CLIENT_ID = propertiesGetValue.getPropertyValue("blackboard_client_id");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loginAppUI(String userLogin, String userPass) {

        LoginPage loginPage = new LoginPage();

        open(IBNextURLs.LOGIN_PAGE);
        waitPage();
        loginPage.loginField.setValue(userLogin);
        loginPage.passwordField.shouldBe(Condition.visible).setValue(userPass);
        waitPage();
        loginPage.buttonSubmit.click();
        waitPage();
        $x("//header").shouldBe(Condition.visible, Duration.ofSeconds(100));
    }

    public Object checkPageURL(String expectedURL) {
        String actualURL = WebDriverRunner.getWebDriver().getCurrentUrl();
        assertThat(IBNextURLs.MAIN_URL + expectedURL.equals(actualURL))
                .as("The page's URL is not match");
        return this;
    }

    public Object checkPageTitle(String expectedTitle) {
        String actualTitle = WebDriverRunner.getWebDriver().getTitle();
        assertThat(expectedTitle.equals(actualTitle)).as("The page's Tile is not match");
        return this;
    }

    public Object waitForPageLoaded() {

        WebDriver driver = WebDriverRunner.getWebDriver();

        try {
            Thread.sleep(1000);
            WebDriverWait waitForLoad = new WebDriverWait(WebDriverRunner.getWebDriver(), Duration.ofSeconds(10));
            waitForLoad.until(
                    webDriver -> (((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete")
                            && ((Boolean) ((JavascriptExecutor) driver).executeScript("return jQuery.active === 0"))));
        } catch (Throwable error) {
            assertThat(false).as("Timeout waiting for Page Load Request to complete.");
        }
        return this;
    }

    public static void waitPage() {

        WebDriver driver = WebDriverRunner.getWebDriver();

        try {
            Thread.sleep(1000);
            WebDriverWait waitForLoad = new WebDriverWait(WebDriverRunner.getWebDriver(), Duration.ofSeconds(10));
            waitForLoad.until(
                    webDriver -> (((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete")
                            && ((Boolean) ((JavascriptExecutor) driver).executeScript("return jQuery.active === 0"))));
        } catch (Throwable error) {
            assertThat(false).as("Timeout waiting for Page Load Request to complete.");
        }
    }
}
