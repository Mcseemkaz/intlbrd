package net.intelliboard.next.services.pages.header;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import net.intelliboard.next.services.pages.elements.spinners.PageSpinner;
import org.openqa.selenium.WebElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;
import static net.intelliboard.next.AbstractTest.SLEEP_TIMEOUT_SHORT;

public class HeaderConnectionManager {

    @Step("Expand Connection Manager")
    public static HeaderConnectionManager expandOpenConnectionManager() {
        $x("//div[contains(@class, 'connection-selector')]").click();
        $x("//div[contains(@class, 'dropdown connections-folder')]")
                .shouldBe(Condition.visible);
        return new HeaderConnectionManager();
    }

    @Step("Select Connection")
    public HeaderConnectionManager selectConnection(String connectionName) {

        Selenide.sleep(SLEEP_TIMEOUT_SHORT);
        WebElement connection = $x("//div[contains(@class, 'connection-name') and contains (text(),'" + connectionName + "')]");
        Selenide
                .actions()
                .scrollToElement(connection)
                .click(connection)
                .perform();

        PageSpinner.waitPreloader();
        PageSpinner.waitSpinner();
        $x("//span[contains (@class, 'selected-connection')]//strong")
                .shouldHave(Condition.text(connectionName), Duration.ofSeconds(60));
        return this;
    }
}
