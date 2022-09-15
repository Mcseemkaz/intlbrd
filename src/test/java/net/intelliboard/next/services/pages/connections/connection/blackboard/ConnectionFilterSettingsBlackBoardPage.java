package net.intelliboard.next.services.pages.connections.connection.blackboard;

import com.codeborne.selenide.Condition;
import net.intelliboard.next.services.pages.connections.connection.ConnectionFilterSettingsMainPage;
import net.intelliboard.next.services.pages.connections.connection.ConnectionTabsEnum;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class ConnectionFilterSettingsBlackBoardPage extends ConnectionFilterSettingsMainPage {

    public static ConnectionFilterSettingsBlackBoardPage init() {
        $x("//div[@class='content-body']//ul[contains (@class, 'user-menu')]//li[.//a[contains (text(),'" + ConnectionTabsEnum.FILTERS_SETTINGS.value + "')]]")
                .shouldHave(Condition.cssClass("active"), Duration.ofSeconds(120));
        return new ConnectionFilterSettingsBlackBoardPage();
    }

    private void openCoursesList() {
        $x("//div[@name='filter_course_state']//button")
                .click();
        $x("//div[@name='filter_course_state']//div[@class='tree-drop']")
                .should(Condition.visible);
    }

    public ConnectionFilterSettingsBlackBoardPage selectCourseStatusAllCourses() {
        openCoursesList();
        $x("//div[@name='filter_course_state']//ul//li//strong[contains(text(),'All Courses')]")
                .click();
        return this;
    }

    private void openFilterTerm() {
        $x("//div[@name='filter_term']//button").click();
    }

    public ConnectionFilterSettingsBlackBoardPage selectCourseFilterTermFirst() {
        openFilterTerm();
        $x("(//div[@name='filter_term']//ul//li)[2]")
                .click();
        return this;
    }

    private void openFilterNode() {
        $x("//div[@name='filter_node']//button")
                .click();
    }

    public ConnectionFilterSettingsBlackBoardPage selectFilterNodeFirst() {
        openFilterNode();
        $x("(//div[@name='filter_node']//ul//li)[2]")
                .click();
        return this;
    }
}
