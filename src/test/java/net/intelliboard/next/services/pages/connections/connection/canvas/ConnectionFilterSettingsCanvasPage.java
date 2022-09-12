package net.intelliboard.next.services.pages.connections.connection.canvas;

import com.codeborne.selenide.Condition;
import net.intelliboard.next.services.pages.connections.connection.ConnectionFilterSettingsMainPage;
import net.intelliboard.next.services.pages.connections.connection.ConnectionFiltersActiveStateEnum;
import net.intelliboard.next.services.pages.connections.connection.ConnectionTabsEnum;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class ConnectionFilterSettingsCanvasPage extends ConnectionFilterSettingsMainPage {


    public static ConnectionFilterSettingsCanvasPage init() {
        $x("//div[@class='content-body']//ul[contains (@class, 'user-menu')]//li[.//a[contains (text(),'" + ConnectionTabsEnum.FILTERS_SETTINGS.value + "')]]")
                .shouldHave(Condition.cssClass("active"), Duration.ofSeconds(120));
        return new ConnectionFilterSettingsCanvasPage();
    }

    public ConnectionFilterSettingsMainPage setFilterActivityState(ConnectionFiltersActiveStateEnum activeState) {
        openFilterActivityStateDropdown();
        $x("//div[@name='filter_activity_state']//div[@class='tree-drop']//strong[contains(text(),'" + activeState.value + "')]")
                .click();
        $x("//div[@name='filter_activity_state']//button[@type='submit']")
                .click();
        return this;
    }

    private void openFilterActivityStateDropdown() {
        $x("//div[@name='filter_activity_state']//button[@class='tree-choice']")
                .click();
        $x("//div[@name='filter_activity_state']//ion-icon[@name='chevron-up-outline']")
                .should(Condition.visible, Duration.ofSeconds(30));
    }
}
