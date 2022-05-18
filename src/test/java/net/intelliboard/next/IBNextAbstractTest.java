package net.intelliboard.next;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import net.intelliboard.next.services.IBNextURLs;
import net.intelliboard.next.services.pages.CreateConnectionPage;
import net.intelliboard.next.services.pages.login.LoginPage;
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

    static {
        try {
            USER_LOGIN = propertiesGetValue.getPropertyValue("user_login");
            USER_PASS = propertiesGetValue.getPropertyValue("user_pass");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loginAppUI(String userLogin, String userPass) {

        open(IBNextURLs.LOGIN_PAGE);

        LoginPage loginPage = LoginPage.init();
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
