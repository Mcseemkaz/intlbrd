package net.intelliboard.next.services.pages.report.builder;

import com.codeborne.selenide.Condition;
import net.intelliboard.next.services.pages.report.ReportPage;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class ReportBuilderMainPage {

    public static ReportBuilderMainPage init() {
        $x("//main[@class='builder-page']").shouldBe(Condition.visible, Duration.ofSeconds(90));
        return new ReportBuilderMainPage();
    }

    public ReportPage saveReportToDashboard() {
        $x("//a[contains(text(),'Save to Dashboard')]").click();
        return ReportPage.init();
    }
}
