package net.intelliboard.next.services.pages.report.create_wizard;

import com.codeborne.selenide.Condition;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class ReportCreationWizardVisualizationPage {

    public static ReportCreationWizardVisualizationPage init() {
        $x("//div[contains (@class, 'wizard-page')]")
                .shouldBe(Condition.visible, Duration.ofSeconds(60));
        $x("//label[contains (text(),'Label Choose Type')]").shouldBe(Condition.visible);
        return new ReportCreationWizardVisualizationPage();
    }

    public ReportCreationWizardLMSPage proceedNext(){
        $x("//div[contains (@class,'active')]//a[contains (@class, 'app-button primary')]").click();
        return ReportCreationWizardLMSPage.init();
    }

    public ReportCreationWizardVisualizationPage selectReportType(ReportTypeEnum reportType){
        $x("//input[@value='"+reportType.value+"']").click();
        $x("//input[@value='"+reportType.value+"']/following-sibling::span//ion-icon")
                .shouldBe(Condition.exist);
        return this;
    }
}
