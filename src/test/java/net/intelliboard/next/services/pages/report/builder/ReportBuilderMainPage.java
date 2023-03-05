package net.intelliboard.next.services.pages.report.builder;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.pages.elements.spinners.PageSpinner;
import net.intelliboard.next.services.pages.myintelliboard.MyIntelliBoardPage;
import net.intelliboard.next.services.pages.report.ReportPage;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class ReportBuilderMainPage {

    public static ReportBuilderMainPage init() {
        PageSpinner.waitSpinner();
        PageSpinner.waitSpinner();
        IBNextAbstractTest ibNextAbstractTest = new IBNextAbstractTest();
        ibNextAbstractTest.waitForPageLoaded();
        $x("//main[@class='builder-page']")
                .shouldBe(Condition.visible, Duration.ofSeconds(90));
        return new ReportBuilderMainPage();
    }

    @Step("Save Report to Dashboard")
    public ReportPage saveReportToDashboard() {
        $x("//button[contains(text(),'Save to Dashboard')]").click();
        return ReportPage.init();
    }

    @Step("Open Setting Modal Window")
    public ReportSettingsModal openSettingsModal() {
        $x("//div[contains (@class,'data-header')]//a[contains (text(),'Settings')]").click();
        return ReportSettingsModal.init();
    }

    @Step("Cancel Saving Report")
    public MyIntelliBoardPage cancelSavingReport() {
        $x("//a[contains (text(),'Cancel')]").click();
        Selenide.confirm();
        return MyIntelliBoardPage.init();
    }

    @Step("Get Table Row value")
    public String getTableRowValue(String columnName, int rowNumber) {
        return $x("(//tbody//span[@title='" + columnName + "'])[" + rowNumber + "]").getText();
    }
}
