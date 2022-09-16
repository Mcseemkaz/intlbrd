package net.intelliboard.next.services.pages.connections.connection.moodle;

import com.codeborne.selenide.Condition;
import net.intelliboard.next.services.pages.connections.connection.ConnectionFilterSettingsMainPage;
import net.intelliboard.next.services.pages.connections.connection.ConnectionFiltersActiveStateEnum;
import net.intelliboard.next.services.pages.connections.connection.ConnectionTabsEnum;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class ConnectionFilterSettingsMoodlePage extends ConnectionFilterSettingsMainPage {

    public static ConnectionFilterSettingsMoodlePage init() {
        $x("//div[@class='content-body']//ul[contains (@class, 'user-menu')]//li[.//a[contains (text(),'" + ConnectionTabsEnum.FILTERS_SETTINGS.value + "')]]")
                .shouldHave(Condition.cssClass("active"), Duration.ofSeconds(120));
        return new ConnectionFilterSettingsMoodlePage();
    }

    private void openCoursesList() {
        $x("//div[@name='filter_course_state']//button")
                .click();
        $x("//div[@name='filter_course_state']//div[@class='tree-drop']")
                .should(Condition.visible);
    }

    public ConnectionFilterSettingsMoodlePage selectCourseStatusAllCourses() {
        openCoursesList();
        $x("//div[@name='filter_course_state']//ul//li//strong[contains(text(),'All Courses')]")
                .click();
        return this;
    }

    private void openFilterCategory() {
        $x("//div[@name='filter_category']//button[@type='button']")
                .click();
        $x("//div[@name='filter_category']//div[@class='tree-drop']")
                .should(Condition.visible);
    }

    public ConnectionFilterSettingsMoodlePage selectFilterCategoryAll() {
        openFilterCategory();
        $x("//div[@name='filter_category']//strong[contains (text(),'" + ConnectionFiltersActiveStateEnum.ALL_OPTIONS.value + "')]")
                .click();
        return this;
    }

}
