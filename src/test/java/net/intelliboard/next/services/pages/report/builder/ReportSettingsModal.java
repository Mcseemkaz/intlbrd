package net.intelliboard.next.services.pages.report.builder;

import com.codeborne.selenide.Condition;

import java.time.Duration;
import java.util.ArrayList;

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

    public ReportSettingsModal changeAvailability(ArrayList<ReportConnectionTypeAvailabilityEnum> connectionsList, boolean setAvailability) {
        connectionsList.stream().forEach(k -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (!$x("//div[@label='Availability']//div[@class='tree-drop']").isDisplayed()) {
                $x("//div[@label='Availability']").click();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            $x("//div[@placeholder='Availability']//div[@class='dropdown-body']//div[@class='tree-drop']//ul//li[.//*[contains (text(),'" + k.value + "')]]//input").setSelected(setAvailability);
        });
        return this;
    }

    public ReportSettingsModal setReportColor(ReportSettingsColorsEnum color){
        $x("//div[@class='color-selector']//label[./input[@name='color' and @value='"+color.value+"']]").click();
        return this;
    }
}
