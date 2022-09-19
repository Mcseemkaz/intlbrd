package net.intelliboard.next.services.pages.connections.connection;

import com.codeborne.selenide.Condition;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class ConnectionFilterSettingsMainPage extends MainConnectionPage {
    public static ConnectionFilterSettingsMainPage init() {
        $x("//li[.//a[contains (text(),'" + ConnectionTabsEnum.FILTERS_SETTINGS.value + "')]]")
                .shouldHave(Condition.cssClass("active"), Duration.ofSeconds(120));
        return new ConnectionFilterSettingsMainPage();
    }
}

