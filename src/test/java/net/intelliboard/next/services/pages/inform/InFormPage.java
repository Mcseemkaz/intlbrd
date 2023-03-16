package net.intelliboard.next.services.pages.inform;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.pages.elements.spinners.PageSpinner;
import net.intelliboard.next.services.pages.report.builder.ReportSettingsModal;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class InFormPage {

    public static InFormPage init() {
        PageSpinner.waitPreloader();
        PageSpinner.waitSpinner();
        $x("//h1[@class='left']").shouldBe(Condition.visible, Duration.ofSeconds(30));
        $x("//div[@class='table-container']").shouldBe(Condition.visible, Duration.ofSeconds(30));
        return new InFormPage();
    }

    @Step("Open InForm Add Table Page")
    public AddNewInFormTablePage openAddTablePage() {
        $x("//a[contains (@href,'/in-form/create')]").click();
        return AddNewInFormTablePage.init();
    }

    @Step("Search InForm Table")
    public InFormPage searchInfoTable(String tableName) {
        $x("//div[@class='table-panel']//input[contains (@class, 'search-input')]")
                .setValue(tableName)
                .sendKeys(Keys.ENTER);
        IBNextAbstractTest ibNextAbstractTest = new IBNextAbstractTest();
        ibNextAbstractTest.waitForPageLoaded();
        return InFormPage.init();
    }

    @Step("Delete inForm Table")
    public InFormPage deleteTable(String tableName) {
        openActionMenu(tableName);
        $x("//ul[contains (@class, 'dropdown-menu')]//a[contains (text(),'Delete')]")
                .click();
        confirmDelete();
        return this;
    }

    @Step("Generated Standard Report")
    public ReportSettingsModal generateStandardReport(String tableName) {
        openActionMenu(tableName);
        $x("//ul[contains (@class, 'dropdown-menu')]//a[contains (text(),'Generate Standard Report')]")
                .click();
        return ReportSettingsModal.init();
    }

    @Step("Is Table Exist")
    public boolean isTableExist(String tableName) {
        return getTableElement(tableName).exists();
    }

    private SelenideElement getTableElement(String tableName) {
        return $x("//tr[ ./td[text()='" + tableName + "']]");
    }

    private InFormPage openActionMenu(String tableName) {
        $x("//tr[ ./td[text()='" + tableName + "']]//td[contains (@class, 'actions-cell')]")
                .click();
        return this;
    }

    private InFormPage confirmDelete() {
        ConfirmationDeleteFormPopup
                .init()
                .submitFormDeletion();


        ConfirmationDeletePopup
                .init()
                .submitDeletion();
        return this;
    }

    @Step("Open InForm Importing List")
    public ImportInFormListPage openImportFileList() {
        $x("//a[contains (@href,'/in-form/import')]")
                .click();
        return ImportInFormListPage.init();
    }

    @Step("Open InForm Shows")
    public InFormRecordsShowPage openShows(String itemName){
        openActionMenu(itemName);
        $x("//ul[contains (@class, 'dropdown-menu')]//a[contains (text(),'Show')]")
                .click();
        return InFormRecordsShowPage.init();
    }
}
