package net.intelliboard.next.services.pages.report;

import com.codeborne.selenide.Condition;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.pages.report.builder.ReportShareOptionEnum;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class ReportPage {

    IBNextAbstractTest ibNextAbstractTest = new IBNextAbstractTest();
    public static ReportPage init() {
        $x("//div[contains (@class, 'data-set') and contains (@class, 'report')]")
                .shouldBe(Condition.visible, Duration.ofSeconds(60));
        return new ReportPage();
    }

    private void openShareOption() {
        $x("//div[contains (@class,'btn') and .//span[contains (text(),'Share')]]")
                .click();
        $x("//div[contains (@class,'intelli-dropdown') and .//span[contains (text(),'Share')]]//ul")
                .shouldBe(Condition.visible);

        ibNextAbstractTest.waitForPageLoaded();
    }

    public void selectShareOption(ReportShareOptionEnum reportShareOptionEnum) {
        openShareOption();
        $x("//div[contains (@class,'intelli-dropdown') and .//span[contains (text(),'Share')]]//li[./a[contains (text(),'" +
                reportShareOptionEnum.value + "')]]")
                .click();
    }
}
