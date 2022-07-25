package net.intelliboard.next.services.pages.report.builder;

import com.codeborne.selenide.Condition;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class ReportSettingsModal {
    public static ReportSettingsModal init() {
        $x("//div[@class='builder-settings-wrapper']").shouldBe(Condition.visible, Duration.ofSeconds(30));
        return new ReportSettingsModal();
    }

    public ReportSettingsModal fillInName(String reportName) {
        $x("//input[@name='name']").setValue(reportName);
        return this;
    }

    public ReportSettingsModal fillInDescription(String reportDescription) {
        $x("//textarea[@name='description']").setValue(reportDescription);
        return this;
    }

    public ReportBuilderMainPage continueToPreview() {
        $x("//div[@class='data-actions']//a[contains(text(),'Continue To Preview')]").click();
        return ReportBuilderMainPage.init();
    }
}
