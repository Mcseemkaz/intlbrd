package net.intelliboard.next.services.pages.report.create_wizard;

import com.codeborne.selenide.Condition;
import net.intelliboard.next.services.pages.connections.ConnectionsTypeEnum;
import net.intelliboard.next.services.pages.report.builder.ReportBuilderMainPage;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class ReportCreationWizardLMSPage {

    public static ReportCreationWizardLMSPage init() {
        $x("//div[contains (@class, 'wizard-page')]")
                .shouldBe(Condition.visible, Duration.ofSeconds(60));
        $x("//label[contains (text(),'Choose your LMS')]").shouldBe(Condition.visible);
        return new ReportCreationWizardLMSPage();
    }

    public ReportCreationWizardLMSPage selectLMSType(ConnectionsTypeEnum lmsType){
        $x("//input[ following-sibling::span[contains (text(),'"+lmsType.value+"')]]").click();
        $x("//span[contains(text(),'"+lmsType.value+"')]/ion-icon")
                .shouldBe(Condition.exist);
        return this;
    }

    public ReportCreationWizardLMSPage proceedNext(){
        $x("//div[contains (@class,'active')]//a[contains (@class, 'app-button primary')]").click();
        return this;
    }

    public ReportBuilderMainPage goToReport(){
        $x("//button[@type='submit']").click();
        return ReportBuilderMainPage.init();
    }
}
