package net.intelliboard.next.services.pages.connections;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import net.intelliboard.next.services.pages.elements.spinners.PageSpinner;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class ConnectionProcessingHistoryMainPage {

    public static ConnectionProcessingHistoryMainPage init() {
        PageSpinner.waitPreloader();
        PageSpinner.waitSpinner();
        $x("//div[@class='content-body' and ./div[contains (@class,'audit-progress')]]//div[@class='panel']")
                .shouldBe(Condition.visible, Duration.ofSeconds(120));
        return new ConnectionProcessingHistoryMainPage();
    }

    @Step("Search Connection by name")
    public ConnectionProcessingHistoryMainPage searchConnectionByName(String connectionName) {
        $x("//input[contains(@class, 'search-input') and (@placeholder='Search')]")
                .setValue(connectionName)
                .sendKeys(Keys.ENTER);
        return this;
    }

    public boolean isConnectionExist(String connectionName) {
        searchConnectionByName(connectionName);
        return $x("//table//td[contains (text(),'" + connectionName + "')]")
                .exists();
    }

    public String checkConnectionStatus(String connectionName) {
        SelenideElement statusCell = $x("//tr[./td[contains (text(),'" + connectionName + "')]]//td[2]");
        return statusCell.getText();
    }

    public ConnectionProcessingHistoryOverviewPage openOverviewHistoryPage(String connectionName) {
        $x("//tr[./td[contains (text(),'" + connectionName + "')]]//td/a")
                .click();

        return ConnectionProcessingHistoryOverviewPage.init();
    }

    public int getNumberActiveConnection() {
        return Integer.parseInt($x("//div[contains (@class,'audit-progress')]//span[contains (@class, 'display-3')]")
                .getText());
    }
}
