package net.intelliboard.next.services.pages.connections.connection;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.pages.connections.ConnectionsListPage;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class MainConnectionPage {

    protected final SelenideElement saveButton = $x("//button[@type='submit' and contains(text(),'Save')]");
    protected static IBNextAbstractTest ibNextAbstractTest = new IBNextAbstractTest();

    @Step("Open Settings Tab")
    public void openSettingsTab(ConnectionTabsEnum tab) {
        $x("//div[@class='content-body']//ul[contains (@class, 'user-menu')]//a[contains (text(),'" + tab.value + "')]")
                .click();
        $x("//div[@class='content-body']//ul[contains (@class, 'user-menu')]//li[.//a[contains (text(),'" + tab.value + "')]]")
                .shouldHave(Condition.cssClass("active"), Duration.ofSeconds(120));
    }

    @Step("Save Connection Settings")
    public ConnectionsListPage saveConnectionSettings() {
        saveButton.click();
        return ConnectionsListPage.init();
    }
}
