package net.intelliboard.next.services.pages.myintelliboard;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import net.intelliboard.next.AbstractTest;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.IBNextURLs;
import net.intelliboard.next.services.pages.dashboard.DashboardPage;
import net.intelliboard.next.services.pages.elements.spinners.PageSpinner;
import net.intelliboard.next.services.pages.header.ReleaseNotesModal;
import net.intelliboard.next.services.pages.library.LibraryItemTypeEnum;
import net.intelliboard.next.services.pages.myintelliboard.modals.DashboardDeleteModalPage;
import net.intelliboard.next.services.pages.report.ReportPage;
import net.intelliboard.next.services.pages.report.builder.ReportBuilderMainPage;

import static com.codeborne.selenide.Selenide.$x;

public class MyIntelliBoardPage {

    public static MyIntelliBoardPage init() {
        PageSpinner.waitPreloader();
        PageSpinner.waitSpinner();
        IBNextAbstractTest ibNextAbstractTest = new IBNextAbstractTest();
        ibNextAbstractTest.waitForPageLoaded();
        $x("//div[@class='content-body']")
                .shouldBe(Condition.visible);
        ibNextAbstractTest.checkPageURL(IBNextURLs.MY_INTELLIBOARD_PAGE);

        //TODO [MO] need fix that to reduce time of test
        Selenide.sleep(AbstractTest.SLEEP_TIMEOUT_SHORT);
        if (ReleaseNotesModal.releaseModal.isDisplayed()) {
            ReleaseNotesModal.init().closeReleaseModal();
        }

        return new MyIntelliBoardPage();
    }

    public boolean isDashboardPresentsByName(String dashboardName) {
        return $x("//div[contains (@class,'data-library-info')]//h4[contains (text(),'" + dashboardName + "')]")
                .exists();
    }

    @Step("Search element on Dashboard")
    public MyIntelliBoardPage searchItem(String searchString) {
        $x("//input[contains (@class,'input-search')]").setValue(searchString);
        return this;
    }

    @Step("Set Dashboard favorite")
    public MyIntelliBoardPage setDashboardFavorite(String dashboardName) {
        Selenide.actions()
                .moveToElement($x("//li[.//h4[contains (text(),'" + dashboardName + "')]]//div[contains (@class,'data-library-item-wrapper')]"))
                .click($x("//li[.//h4[contains (text(),'" + dashboardName + "')]]//button"))
                .perform();
        return this;
    }

    @Step("View Dashboard by order number")
    public MyIntelliBoardPage openView(int numberDashboard) {
        Selenide.actions()
                .moveToElement($x("//div[@class='data-library-list']//li[" + numberDashboard + "]//div[contains (@class,'data-library-item-wrapper')]"))
                .click($x("//div[@class='data-library-list']//li[" + numberDashboard + "]//div[contains (@class,'data-library-item')]//span[@class='dropdown-trigger']//span[@class='action-item']"))
                .click($x("//div[contains (@class,'dropdown-body')]//a[ .//ion-icon[@name='eye-outline']]"))
                .perform();
        return this;
    }

    @Step("Edit Dashboard by order number")
    public DashboardPage openEdit(int numberDashboard) {
        Selenide.actions()
                .moveToElement($x("//div[@class='data-library-list']//li[" + numberDashboard + "]//div[contains (@class,'data-library-item-wrapper')]"))
                .click($x("//div[@class='data-library-list']//li[" + numberDashboard + "]//div[contains (@class,'data-library-item')]//span[@class='dropdown-trigger']//span[@class='action-item']"))
                .click($x("//div[contains (@class,'dropdown-body')]//a[ .//ion-icon[@name='create-outline']]"))
                .perform();
        return DashboardPage.init();
    }

    public String getNameofDashboardByOrderNumber(int number) {
        return $x("//div[contains(@class,'cards-view')]//li[" + number + "]//div[contains (@class,'data-library-item')]//div[contains(@class,'data-library-item-info')]//h4")
                .getText();
    }

    @Step("Check that item is in Favorite")
    public boolean isDashboardPresentsInFavorite(String dashboardName) {
        return $x("//div[@class='data-library-list' and  .//h2[contains (text(),'Favorites')]]//h4[@class='title' and contains (text(),'" + dashboardName + "')]")
                .exists();
    }

    public boolean isReportExist(String reportName) {
        return $x("//div[@class='data-library-list' and ./header/h2[contains (text(),'Reports')] and not(@style)]//li[.//h4[contains (text(),'" + reportName + "')]]")
                .exists();
    }

    @Step("Delete Report")
    public DashboardDeleteModalPage deleteReport(String reportName) {
        Selenide
                .actions()
                .moveToElement($x("//div[@class='data-library-list' and ./header/h2[contains (text(),'Reports')] and not(@style)]//li[.//h4[contains (text(),'" + reportName + "')]]//div[contains (@class,'data-library-item-wrapper')]"))
                .click($x("//div[@class='data-library-list' and ./header/h2[contains (text(),'Reports')] and not(@style)]//li[.//h4[contains (text(),'" + reportName + "')]]//span[@class='dropdown-trigger']"))
                .click($x("//div[@class='data-library-list' and ./header/h2[contains (text(),'Reports')] and not(@style)]//li[.//h4[contains (text(),'" + reportName + "')]]//div[contains(@class,'dropdown-menu')]//a[contains (text(),'Delete')]"))
                .perform();
        return DashboardDeleteModalPage.init();
    }

    @Step("Edit Report")
    public ReportBuilderMainPage editReport(String reportName) {
        Selenide
                .actions()
                .moveToElement($x("//div[@class='data-library-list' and ./header/h2[contains (text(),'Reports')] and not(@style)]//li[.//h4[contains (text(),'" + reportName + "')]]//div[contains (@class,'data-library-item-wrapper')]"))
                .click($x("//div[@class='data-library-list' and ./header/h2[contains (text(),'Reports')] and not(@style)]//li[.//h4[contains (text(),'" + reportName + "')]]//span[@class='dropdown-trigger']"))
                .click($x("//div[@class='data-library-list' and ./header/h2[contains (text(),'Reports')] and not(@style)]//li[.//h4[contains (text(),'" + reportName + "')]]//div[contains(@class,'dropdown-menu')]//a[contains (text(),'Edit')]"))
                .perform();
        return ReportBuilderMainPage.init();
    }

    public ReportPage viewReport(String reportName) {
        $x("//div[@class='data-library-list' and ./header/h2[contains (text(),'Reports')] and not(@style)]//li[.//h4[contains (text(),'" + reportName + "')]]//div[contains (@class,'data-library-item-wrapper')]")
                .click();
        return ReportPage.init();
    }

    public String getReportBackgroundColors(String reportName) {
        return $x("//div[@class='data-library-list' and ./header/h2[contains (text(),'Reports')] and not(@style)]//li[.//h4[contains (text(),'" + reportName + "')]]//div[@class='data-library-item-wrapper']")
                .getAttribute("style");
    }

    public boolean isItemPresentsByOrderNumber(LibraryItemTypeEnum type, int numberOfItem) {
        return $x("//div[@class='data-library-list' and .//h2[contains (text(), '" + type.value + "')]]//li[" + numberOfItem + "]//h4")
                .exists();
    }

    @Step("Edit Chart")
    public ReportBuilderMainPage editChart(String reportName) {
        Selenide
                .actions()
                .moveToElement($x("//div[@class='data-library-list' and ./header/h2[contains (text(),'Charts')] and not(@style)]//li[.//h4[contains (text(),'" + reportName + "')]]//div[contains (@class,'data-library-item-wrapper')]"))
                .click($x("//div[@class='data-library-list' and ./header/h2[contains (text(),'Charts')] and not(@style)]//li[.//h4[contains (text(),'" + reportName + "')]]//span[@class='dropdown-trigger']"))
                .click($x("//div[@class='data-library-list' and ./header/h2[contains (text(),'Charts')] and not(@style)]//li[.//h4[contains (text(),'" + reportName + "')]]//div[contains(@class,'dropdown-menu')]//a[contains (text(),'Edit')]"))
                .perform();
        return ReportBuilderMainPage.init();
    }

    @Step("Delete Chart")
    public DashboardDeleteModalPage deleteChart(String reportName) {
        Selenide
                .actions()
                .moveToElement($x("//div[@class='data-library-list' and ./header/h2[contains (text(),'Charts')] and not(@style)]//li[.//h4[contains (text(),'" + reportName + "')]]//div[contains (@class,'data-library-item-wrapper')]"))
                .click($x("//div[@class='data-library-list' and ./header/h2[contains (text(),'Charts')] and not(@style)]//li[.//h4[contains (text(),'" + reportName + "')]]//span[@class='dropdown-trigger']"))
                .click($x("//div[@class='data-library-list' and ./header/h2[contains (text(),'Charts')] and not(@style)]//li[.//h4[contains (text(),'" + reportName + "')]]//div[contains(@class,'dropdown-menu')]//a[contains (text(),'Delete')]"))
                .perform();
        return DashboardDeleteModalPage.init();
    }

    public boolean isChartExist(String reportName) {
        return $x("//div[@class='data-library-list' and ./header/h2[contains (text(),'Reports')] and not(@style)]//li[.//h4[contains (text(),'" + reportName + "')]]")
                .exists();
    }

    //Generic methods for refactoring
    @Step("Check that item is in Favorite")
    public boolean isItemPresentsInFavorite(LibraryItemTypeEnum dashboardItemType, String itemName) {
        return $x("//div[@class='data-library-list' and  .//h2[contains (text(),'" + dashboardItemType.value + "')]]//h4[@class='title' and contains (text(),'" + itemName + "')]")
                .exists();
    }

    @Step("Get item Name")
    public String getItemName(LibraryItemTypeEnum type, int numberOfItem) {
        return $x("//div[@class='data-library-list' and .//h2[contains (text(), '" + type.value + "')]]//li[" + numberOfItem + "]//h4[@class='title']")
                .getText();
    }

    @Step("Open Item")
    public void viewItem(LibraryItemTypeEnum dashboardItemType, String itemName) {
        $x("//div[@class='data-library-list' and ./header/h2[contains (text(),'" + dashboardItemType.value + "')] and not(@style)]//li[.//h4[contains (text(),'" + itemName + "')]]//div[contains (@class,'data-library-item-wrapper')]")
                .click();
    }

}