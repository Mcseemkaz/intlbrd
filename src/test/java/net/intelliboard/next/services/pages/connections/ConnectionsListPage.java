package net.intelliboard.next.services.pages.connections;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import net.intelliboard.next.IBNextAbstractTest;
import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class ConnectionsListPage {

    private SelenideElement buttonDelete = $x("//div //ul /li /a[contains(text(), 'Delete')]");

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
}
