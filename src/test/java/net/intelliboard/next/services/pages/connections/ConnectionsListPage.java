package net.intelliboard.next.services.pages.connections;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import io.qameta.allure.Step;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.pages.connections.categories.ConnectionCategoriesListPage;
import net.intelliboard.next.services.pages.connections.connection.ConnectionConnectionSettingsMainPage;
import net.intelliboard.next.services.pages.elements.spinners.PageSpinner;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.*;
import static net.intelliboard.next.AbstractTest.SLEEP_TIMEOUT_LONG;

public class ConnectionsListPage {

    private final SelenideElement buttonDelete = $x("//div //ul /li /a[contains(text(), 'Delete')]");
    private final SelenideElement buttonEdit = $x("//li/a[contains (@href,'/edit')]");
    private final IBNextAbstractTest ibNextAbstractTest = new IBNextAbstractTest();

    public static ConnectionsListPage init() {
        PageSpinner.waitPreloader();
        PageSpinner.waitSpinner();
        $x("//h1[contains (text(),'Connections')]")
                .shouldBe(Condition.visible, Duration.ofSeconds(90));
        return new ConnectionsListPage();
    }

    @Step("Delete Connection")
    public ConnectionsListPage deleteConnection(String connectionName) {
        searchConnectionByName(connectionName);
        $x("//a[contains(text(),'" + connectionName + "')]//ancestor-or-self::tr//button[contains (@class,'dropdown-toggle')]")
                .click();
        buttonDelete
                .click();
        Selenide
                .confirm();
        $x("//p[contains (text(), 'Success Delete')]")
                .shouldBe(Condition.visible);
        return this;
    }

    @Step("Search connection by name")
    public ConnectionsListPage searchConnectionByName(String connectionName) {
        $x("//input[contains(@class, 'search-input')][@placeholder='Search']")
                .setValue(connectionName)
                .sendKeys(Keys.ENTER);
        ibNextAbstractTest.waitForPageLoaded();
        PageSpinner.waitSpinner();
        return this;
    }

    public boolean isConnectionExist(String connectionName) {
        SelenideElement isConnection = $x("//a[contains(text(),'" + connectionName + "')]" +
                "/ancestor-or-self::tr//button[contains (@class,'dropdown-toggle')]");
        return isConnection.exists();
    }

    @Step("Edit connection")
    public ConnectionConnectionSettingsMainPage editConnection(String connectionName) {
        searchConnectionByName(connectionName);
        $x("//a[contains(text(),'" + connectionName + "')]//ancestor-or-self::tr//button[contains (@class,'dropdown-toggle')]")
                .click();
        buttonEdit.click();
        return ConnectionConnectionSettingsMainPage.init();
    }

    public boolean checkLastProcessing(String connectionName, LocalDateTime date) {
        searchConnectionByName(connectionName);
        String processingDate = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return $x("//tr[ .//td[contains(@class, 'connection-name')]//a[contains(text(),'" + connectionName + "')]]//td[contains(text(),'" + processingDate + "')]").exists();
    }

    public boolean checkIntegration(ConnectionIntegrationTypeEnum integration, String connectionName) {
        searchConnectionByName(connectionName);
        return $x("//tr[ .//td[contains(@class, 'connection-name')]//a[contains(text(),'" + connectionName + "')]]//td[.//*[contains (@alt,'" + integration.value + "')]]")
                .exists();
    }

    @Step("Set Connection Active")
    public ConnectionsListPage setActiveConnection(String connectionName, boolean setActive) {
        searchConnectionByName(connectionName);
        SelenideElement checkRadioButton = $x("//a[contains(text(),'" + connectionName + "')]//ancestor-or-self::tr//td[contains (@class, 'status-cell')]//ion-icon");
        if (checkRadioButton.getAttribute("name").contains("off") && setActive == true) {
            checkRadioButton.click();
            checkRadioButton.shouldHave(Condition.attribute("name", "radio-button-on-outline"));
        } else if (checkRadioButton.getAttribute("name").contains("button-on") && setActive == false) {
            checkRadioButton.click();
            checkRadioButton.shouldHave(Condition.attribute("name", "radio-button-off-outline"));
        }

        Selenide.sleep(SLEEP_TIMEOUT_LONG);
        PageSpinner.waitPreloader();
        PageSpinner.waitSpinner();
        return this;
    }

    @Step("Select Connection")
    public ConnectionsListPage selectConnection(String connectionName, boolean setSelected) {
        SelenideElement checkbox = $x("//a[contains(text(),'" + connectionName + "')]//ancestor-or-self::tr//input[@type='checkbox']");
        if (setSelected == true && checkbox.isSelected() == false) {
            checkbox.click();
            checkbox.should(Condition.selected);
        } else if (setSelected == false && checkbox.isSelected() == true) {
            checkbox.click();
            checkbox.should(Condition.not(Condition.selected));

        }
        return this;
    }

    private ConnectionsListPage openActionMenu() throws ElementNotFound {
        SelenideElement actionMenuDropdown = $x("//div[contains(@class, 'intelli-dropdown')][.//strong[contains(text(), 'Action')]]");
        actionMenuDropdown.click();
        $x("//div[contains(@class, 'intelli-dropdown')][.//strong[contains(text(), 'Action')]]//div[contains(@class, 'dropdown-menu')]//ul")
                .shouldBe(Condition.visible);
        return this;
    }

    @Step("Mass deletion connection by Action menu")
    public ConnectionsListPage deleteSelectedConnectionsByActionDropdown() {
        openActionMenu();
        $x("//div[contains(@class, 'intelli-dropdown')][.//strong[contains(text(), 'Action')]]//div[contains(@class, 'dropdown-menu')]//ul//li//a[contains(text(),'Delete Selected')]")
                .click();
        //Modal confirmation
        $x("//div[@class='modal-content']//button[contains(@class,'error')]").click();
        $x("//div[@class='modal-content']").shouldNotBe(Condition.visible, Duration.ofSeconds(30));
        return this;
    }

    @Step("Deactivate selected connection by Action menu")
    public ConnectionsListPage deactivateSelectedConnectionByActionMenu() {
        openActionMenu();
        $x("//div[contains(@class, 'intelli-dropdown')][.//strong[contains(text(), 'Action')]]//div[contains(@class, 'dropdown-menu')]//ul//li//a[contains(text(),'Deactivate Selected')]")
                .click();
        ibNextAbstractTest.waitForPageLoaded();
        return ConnectionsListPage.init();
    }

    public boolean isConnectionActivation(String connectionName, boolean isActivated) {
        SelenideElement checkRadioButton = $x("//a[contains(text(),'" + connectionName + "')]//ancestor-or-self::tr//td[contains (@class, 'status-cell')]//ion-icon");
        if (checkRadioButton.getAttribute("name").contains("off") && isActivated == true) {
            return false;
        } else if (checkRadioButton.getAttribute("name").contains("button-on") && isActivated == false) {
            return false;
        } else
            return true;
    }

    public ConnectionCategoriesListPage openConnectionCollectionsList() {
        $x("//a[contains (@href,'/categories')]").click();
        return ConnectionCategoriesListPage.init();
    }

    public int getNumberConnections() {
        return $$x("//table//tbody/tr")
                .size();
    }

    public int getNumberActiveConnections() {
        return $$x("//table/tbody/tr[./td/button[contains (@aria-label,'Deactivate')]]")
                .size();
    }

    @Step("Open Processing History")
    public ConnectionProcessingHistoryMainPage openProcessingConnectionsHistory() {
        $x("//a[contains (@href,'/audit')]").click();
        return ConnectionProcessingHistoryMainPage.init();
    }
}
