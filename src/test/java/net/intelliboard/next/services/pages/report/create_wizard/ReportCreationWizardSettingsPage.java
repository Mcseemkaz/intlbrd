package net.intelliboard.next.services.pages.report.create_wizard;

import com.codeborne.selenide.Condition;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class ReportCreationWizardSettingsPage {

    public static ReportCreationWizardSettingsPage init() {
        $x("//div[contains (@class, 'wizard-page')]")
                .shouldBe(Condition.visible, Duration.ofSeconds(60));
        return new ReportCreationWizardSettingsPage();
    }

    public ReportCreationWizardSettingsPage fillName(String name){
        $x("//input[@id='name']").setValue(name);
        return this;
    }

    public ReportCreationWizardSettingsPage fillDescription(String description){
        $x("//textarea[@name='description']").setValue(description);
        return this;
    }

    public ReportCreationWizardVisualizationPage proceedNext(){
        $x("//div[contains (@class,'active')]//a[contains (@class, 'app-button primary')]").click();
        return ReportCreationWizardVisualizationPage.init();
    }
}
