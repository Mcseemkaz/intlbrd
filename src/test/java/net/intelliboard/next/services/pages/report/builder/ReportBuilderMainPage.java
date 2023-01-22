package net.intelliboard.next.services.pages.report.builder;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.pages.myintelliboard.MyIntelliBoardPage;
import net.intelliboard.next.services.pages.report.ReportPage;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class ReportBuilderMainPage {

    public static ReportBuilderMainPage init() {
        IBNextAbstractTest ibNextAbstractTest = new IBNextAbstractTest();
        ibNextAbstractTest.waitForPageLoaded();
        $x("//main[@class='builder-page']").shouldBe(Condition.visible, Duration.ofSeconds(90));
        return new ReportBuilderMainPage();
    }

    public ReportPage saveReportToDashboard() {
        $x("//a[contains(text(),'Save to Dashboard')]").click();
        return ReportPage.init();
    }

    public ReportSettingsModal openSettingsModal() {
        $x("//div[contains (@class,'data-header')]//a[contains (text(),'Settings')]").click();
        return ReportSettingsModal.init();
    }

    public MyIntelliBoardPage cancelSavingReport() {
        $x("//a[contains (text(),'Cancel')]").click();
        Selenide.confirm();
        return MyIntelliBoardPage.init();
    }

    public String getTableRowValue(String columnName, int rowNumber) {
        return $x("(//tbody//span[@title='" + columnName + "'])[" + rowNumber + "]").getText();
    }
}
