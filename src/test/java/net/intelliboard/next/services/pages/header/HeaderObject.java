package net.intelliboard.next.services.pages.header;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.pages.dashboard.CreateDashboardPage;

import net.intelliboard.next.services.pages.elements.spinners.PageSpinner;
import net.intelliboard.next.services.pages.incontact.InContactMainPage;
import net.intelliboard.next.services.pages.inform.InFormFormCreatePage;
import net.intelliboard.next.services.pages.myintelliboard.MyIntelliBoardPage;
import net.intelliboard.next.services.pages.report.create_wizard.ReportCreationWizardSettingsPage;


import static com.codeborne.selenide.Selenide.$x;

public class HeaderObject {

    public static HeaderObject init() {
        PageSpinner.waitPreloader();
        IBNextAbstractTest ibNextAbstractTest = new IBNextAbstractTest();
        ibNextAbstractTest.waitForPageLoaded();
        $x("//header//div[contains(@class, 'left-header-section')]")
                .shouldBe(Condition.visible);
        PageSpinner.waitSpinner();
        return new HeaderObject();
    }

    @Step("Open Dropdown Menu")
    public HeaderDropDownMenu openDropDownMenu() {
        $x("//div[contains (@class, 'user-menu-link')]//div[@aria-label='Open User Menu']")
                .click();
        return HeaderDropDownMenu.init();
    }

    @Step("Open MyIntelliboard Page")
    public MyIntelliBoardPage openMyIntelliBoardPage() {
        $x("//a[contains(@href,'/data-sets')]").click();
        return MyIntelliBoardPage.init();
    }

    @Step("Open Create Dashboard")
    public CreateDashboardPage openCreateDashboard() {
        openHeaderCreateMenu();
        $x("//div[contains (@class, 'dropdown-menu')]//div[@class='dropdown-body']//a[contains (@href,'/data-sets/create')]")
                .click();
        return CreateDashboardPage.init();
    }

    @Step("Open Create Report")
    public ReportCreationWizardSettingsPage createReport() {
        openHeaderCreateMenu();
        $x("//a[contains (@href,'/reports/create')]").click();
        return ReportCreationWizardSettingsPage.init();
    }

    @Step("Open create InForm Form")
    public InFormFormCreatePage createInFormForm() {
        openHeaderCreateMenu();
        $x("//a[contains (@href,'/in-form/forms/create')]")
                .click();
        return InFormFormCreatePage.init();
    }

    private HeaderObject openHeaderCreateMenu() {
        $x("//button[contains (@class,'add-data-set-button')]").click();
        $x("//div[contains (@class,'intelli-dropdown')]//div[contains (@class, 'dropdown-menu') and contains (@class, 'active')]")
                .shouldBe(Condition.visible);
        return this;
    }

    @Step("Open App Menu")
    public void openApp(HeaderAppsItemEnum type) {
        openMenuItem(HeaderMenuItemEnum.APPS);
        $x("//header//ul[@class='header-menu-item-sublist']//li[.//a[contains (text(), '" + type.value + "')]]")
                .click();
    }

    @Step("Open Menu item")
    public void openMenuItem(HeaderMenuItemEnum type) {
        $x("//header//ul[@class='header-menu']//li[.//span[contains (text(), '" + type.value + "')]  or .//a[contains (text(),'" + type.value + "')]]")
                .click();
    }

    @Step("Open Success Bar")
    public SuccessBarModal openSuccessBar() {
        $x("//li[.//span[contains (@aria-label,'Open Platform Menu')]]")
                .click();
        return SuccessBarModal.init();
    }
}
