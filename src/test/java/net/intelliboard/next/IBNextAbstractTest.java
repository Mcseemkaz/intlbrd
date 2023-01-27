package net.intelliboard.next;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

public class IBNextAbstractTest extends AbstractTest {

    public Object checkPageURL(String expectedURL) {
        String actualURL = WebDriverRunner.getWebDriver().getCurrentUrl();
        assertThat((actualURL).contains(expectedURL))
                .withFailMessage("The page's URL  is not match: Expected : %s, Actual: %s",expectedURL,actualURL )
                .isTrue();
        return this;
    }

    public Object checkPageTitle(String expectedTitle) {
        String actualTitle = WebDriverRunner.getWebDriver().getTitle();
        assertThat(expectedTitle.equals(actualTitle))
                .withFailMessage("The page's Tile is not match")
                .isTrue();
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
