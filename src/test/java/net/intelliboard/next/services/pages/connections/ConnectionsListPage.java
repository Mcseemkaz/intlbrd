package net.intelliboard.next.services.pages.connections;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import net.intelliboard.next.IBNextAbstractTest;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.*;

public class ConnectionsListPage {

    private SelenideElement buttonDelete = $x("//div //ul /li /a[contains(text(), 'Delete')]");
    private SelenideElement buttonEdit = $x("//li/a[contains (@href,'/edit')]");

    public static ConnectionsListPage init() {
        $x("//h2[contains (text(),'Connections')]")
                .shouldBe(Condition.visible, Duration.ofSeconds(90));
        return new ConnectionsListPage();
    }

    public ConnectionsListPage deleteConnection(String connectionName) {
        findConnectionByName(connectionName);
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

    public ConnectionsListPage findConnectionByName(String connectionName) {
        IBNextAbstractTest ibNextAbstractTest = new IBNextAbstractTest();
        while (!(isConnectionExist(connectionName))) {
            $x("//a[@rel='next']").click();
            ibNextAbstractTest.waitForPageLoaded();
        }
        return this;
    }

    public boolean isConnectionExist(String connectionName) {
        SelenideElement isConnection = $x("//a[contains(text(),'" + connectionName + "')]" +
                "/ancestor-or-self::tr//button[contains (@class,'dropdown-toggle')]");
        return isConnection.exists();
    }

    public EditConnectionPage editConnection(String connectionName) {
        findConnectionByName(connectionName);
        $x("//a[contains(text(),'" + connectionName + "')]//ancestor-or-self::tr//button[contains (@class,'dropdown-toggle')]")
                .click();
        buttonEdit.click();
        return EditConnectionPage.init();
    }

    public boolean checkLastProcessing(String connectionName, LocalDateTime date) {
        findConnectionByName(connectionName);
        String processingDate = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return $x("//tr[ .//td[contains(@class, 'connection-name')]//a[contains(text(),'" + connectionName + "')]]//td[contains(text(),'" + processingDate + "')]").exists();
    }

    public boolean checkIntegration(ConnectionIntegrationType integration, String connectionName) {
        findConnectionByName(connectionName);
        return $x("//tr[ .//td[contains(@class, 'connection-name')]//a[contains(text(),'" + connectionName + "')]]//td[.//*[contains (@alt,'" + integration.value + "')]]")
                .exists();
    }

    public ConnectionsListPage setActiveConnection(String connectionName, boolean setActive) {
        findConnectionByName(connectionName);
        SelenideElement checkRadioButton = $x("//a[contains(text(),'" + connectionName + "')]//ancestor-or-self::tr//td[contains (@class, 'status-cell')]//ion-icon");
        if (checkRadioButton.getAttribute("name").contains("off") && setActive == true) {
            checkRadioButton.click();
            checkRadioButton.shouldHave(Condition.attribute("name", "radio-button-on-outline"));
        } else if (checkRadioButton.getAttribute("name").contains("button-on") && setActive == false) {
            checkRadioButton.click();
            checkRadioButton.shouldHave(Condition.attribute("name", "radio-button-off-outline"));
        }

        return this;
    }
}
