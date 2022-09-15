package net.intelliboard.next.services.pages.connections.connection.ilias;

import com.codeborne.selenide.Condition;
import net.intelliboard.next.services.pages.connections.connection.ConnectionFilterSettingsMainPage;
import net.intelliboard.next.services.pages.connections.connection.ConnectionTabsEnum;
import net.intelliboard.next.services.pages.connections.connection.blackboard.ConnectionFilterSettingsBlackBoardPage;
import net.intelliboard.next.services.pages.connections.connection.canvas.ConnectionFilterSettingsCanvasPage;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class ConnectionFiltersSettingsIliasPage extends ConnectionFilterSettingsMainPage {

    public static ConnectionFiltersSettingsIliasPage init() {
        $x("//div[@class='content-body']//ul[contains (@class, 'user-menu')]//li[.//a[contains (text(),'" + ConnectionTabsEnum.FILTERS_SETTINGS.value + "')]]")
                .shouldHave(Condition.cssClass("active"), Duration.ofSeconds(120));
        return new ConnectionFiltersSettingsIliasPage();
    }

    private void openCoursesList() {
        $x("//div[@name='filter_course_state']//button")
                .click();
        $x("//div[@name='filter_course_state']//div[@class='tree-drop']")
                .should(Condition.visible);
    }

    public ConnectionFiltersSettingsIliasPage selectCourseStatusAllCourses() {
        openCoursesList();
        $x("//div[@name='filter_course_state']//ul//li//strong[contains(text(),'All Courses')]")
                .click();
        return this;
    }

    private void openFilterSubaccount() {
        $x("//div[@name='filter_subaccount']//button")
                .click();
        $x("//div[@name='filter_subaccount']//div[@class='tree-drop']")
                .should(Condition.visible);
    }

    public ConnectionFiltersSettingsIliasPage selectSubAccountAll() {
        openFilterSubaccount();
        $x("//div[@name='filter_subaccount']//div[contains(@class,'tree-select-all')]//strong")
                .click();
        $x("//div[@name='filter_subaccount']//div[contains(@class,'tree-select-all')]//input")
                .should(Condition.checked);
        return this;
    }
}
