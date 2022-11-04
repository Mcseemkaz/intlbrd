package net.intelliboard.next.services.pages.header;

import com.codeborne.selenide.Condition;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.pages.dashboard.CreateDashboardPage;
import net.intelliboard.next.services.pages.myintelliboard.MyIntelliBoardPage;
import net.intelliboard.next.services.pages.report.create_wizard.ReportCreationWizardSettingsPage;

import static com.codeborne.selenide.Selenide.$x;

public class HeaderObject {

    public static HeaderObject init() {
        IBNextAbstractTest ibNextAbstractTest = new IBNextAbstractTest();
        ibNextAbstractTest.waitForPageLoaded();
        $x("//header//div[contains(@class, 'left-header-section')]")
                .shouldBe(Condition.visible);
        return new HeaderObject();
    }

    public HeaderDropDownMenu openDropDownMenu() {
        $x("//div[contains (@class, 'user-menu-link')]//div[@aria-label='Open User Menu']")
                .click();
        return HeaderDropDownMenu.init();
    }

    public MyIntelliBoardPage openMyIntelliBoardPage() {
        $x("//a[contains(@href,'/data-sets')]").click();
        return MyIntelliBoardPage.init();
    }

    public CreateDashboardPage openCreateDashboard() {
        openHeaderCreateMenu();
        $x("//div[contains (@class, 'dropdown-menu')]//div[@class='dropdown-body']//a[contains (@href,'/data-sets/create')]")
                .click();
        return CreateDashboardPage.init();
    }

    public ReportCreationWizardSettingsPage createReport() {
        openHeaderCreateMenu();
        $x("//a[contains (@href,'/reports/create')]").click();
        return ReportCreationWizardSettingsPage.init();
    }

    private HeaderObject openHeaderCreateMenu() {
        $x("//button[contains (@class,'add-data-set-button')]").click();
        $x("//div[contains (@class,'intelli-dropdown')]//div[contains (@class, 'dropdown-menu') and contains (@class, 'active')]")
                .shouldBe(Condition.visible);
        return this;
    }
}
