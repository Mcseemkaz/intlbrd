package net.intelliboard.next.services.pages.dashboard;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class DashboardPage {

    private SelenideElement dashboardTitle = $x("//div[contains(@class, 'left-sub-menu')]//h3");

    public static DashboardPage init() {
        $x("//div[contains(@class,'page-body')]").shouldBe(Condition.visible);
        return new DashboardPage();
    }

    public String getDashboardTitle() {
        return dashboardTitle.getText();
    }

    public CreateDashboardPage openDashboardforEdditing() {
        $x("//div[contains(@class,'data-set-actions')]//a[contains(@href,'/edit')]")
                .click();
        return CreateDashboardPage.init();
    }
}
