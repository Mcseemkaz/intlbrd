package net.intelliboard.next.services.pages.dashboard;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import net.intelliboard.next.services.pages.elements.spinners.PageSpinner;

import static com.codeborne.selenide.Selenide.$x;

public class DashboardPage {

    private final SelenideElement dashboardTitle = $x("//div[contains(@class, 'left-sub-menu')]//h3");

    public static DashboardPage init() {
        PageSpinner.waitPreloader();
        PageSpinner.waitSpinner();
        $x("//div[contains(@class,'page-body')]")
                .shouldBe(Condition.visible);
        return new DashboardPage();
    }

    public String getDashboardTitle() {
        return dashboardTitle.getText();
    }

    @Step("Open Dashboard for Edit")
    public CreateDashboardPage openDashboardForEdditing() {
        $x("//div[contains(@class,'data-set-actions')]//a[contains(@href,'/edit')]")
                .click();
        return CreateDashboardPage.init();
    }
}
