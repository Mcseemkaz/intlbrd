package net.intelliboard.next.services.pages.connections.connection.sakai;

import com.codeborne.selenide.Condition;
import net.intelliboard.next.services.pages.connections.connection.ConnectionFilterSettingsMainPage;
import net.intelliboard.next.services.pages.connections.connection.ConnectionTabsEnum;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class ConnectionFilterSettingsSakaiPage extends ConnectionFilterSettingsMainPage {

    public static ConnectionFilterSettingsSakaiPage init() {
        $x("//div[@class='content-body']//ul[contains (@class, 'user-menu')]//li[.//a[contains (text(),'" + ConnectionTabsEnum.FILTERS_SETTINGS.value + "')]]")
                .shouldHave(Condition.cssClass("active"), Duration.ofSeconds(120));
        return new ConnectionFilterSettingsSakaiPage();
    }

    private void openCoursesList() {
        $x("//div[@name='filter_course_state']//button")
                .click();
        $x("//div[@name='filter_course_state']//div[@class='tree-drop']")
                .should(Condition.visible);
    }

    public ConnectionFilterSettingsSakaiPage selectCourseStatusAllCourses() {
        openCoursesList();
        $x("//div[@name='filter_course_state']//ul//li//strong[contains(text(),'Active Courses')]")
                .click();
        return this;
    }
}
