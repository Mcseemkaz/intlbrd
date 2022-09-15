package net.intelliboard.next.services.pages.connections.connection.d2l;

import com.codeborne.selenide.Condition;
import net.intelliboard.next.services.pages.connections.connection.ConnectionFilterSettingsMainPage;
import net.intelliboard.next.services.pages.connections.connection.ConnectionFiltersActiveStateEnum;
import net.intelliboard.next.services.pages.connections.connection.ConnectionTabsEnum;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class ConnectionFilterSettingsD2LPage extends ConnectionFilterSettingsMainPage {

    public static ConnectionFilterSettingsD2LPage init() {
        $x("//div[@class='content-body']//ul[contains (@class, 'user-menu')]//li[.//a[contains (text(),'" + ConnectionTabsEnum.FILTERS_SETTINGS.value + "')]]")
                .shouldHave(Condition.cssClass("active"), Duration.ofSeconds(120));
        return new ConnectionFilterSettingsD2LPage();
    }

    private void openCoursesList() {
        $x("//div[@name='filter_course_state']//button")
                .click();
        $x("//div[@name='filter_course_state']//div[@class='tree-drop']")
                .should(Condition.visible);
    }

    public ConnectionFilterSettingsD2LPage selectCourseStatusAllCourses() {
        openCoursesList();
        $x("//div[@name='filter_course_state']//ul//li//strong[contains(text(),'All Courses')]")
                .click();
        return this;
    }

    private void openFilterSemester() {
        $x("//div[@name='filter_semester']//button[@type='button']")
                .click();
    }

    public ConnectionFilterSettingsD2LPage selectFilterSemestrAll() {
        openFilterSemester();
        $x("//div[@name='filter_semester']//strong[contains (text(),'" + ConnectionFiltersActiveStateEnum.ALL_OPTIONS.value + "')]")
                .click();
        return this;
    }

    private void openFilterOrgUnit() {
        $x("//div[@name='filter_org_unit']//button")
                .click();
        $x("//div[@name='filter_org_unit']//div[@class='tree-drop']")
                .should(Condition.visible);
    }

    public ConnectionFilterSettingsD2LPage selectFilterOrgUnitAll() {
        openFilterOrgUnit();
        $x("//div[@name='filter_org_unit']//strong[contains (text(),'" + ConnectionFiltersActiveStateEnum.ALL_OPTIONS.value + "')]")
                .click();
        return this;
    }
}
