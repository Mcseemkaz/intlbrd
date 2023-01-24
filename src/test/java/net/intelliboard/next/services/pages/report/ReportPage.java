package net.intelliboard.next.services.pages.report;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.pages.report.builder.ReportShareOptionEnum;

import java.io.File;
import java.io.FileNotFoundException;
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
        Selenide.sleep(2000);
        ibNextAbstractTest.waitForPageLoaded();
    }

    public void selectShareOption(ReportShareOptionEnum reportShareOptionEnum) {
        openShareOption();
        $x("//div[contains (@class,'intelli-dropdown') and .//span[contains (text(),'Share')]]//li[./a[contains (text(),'" +
                reportShareOptionEnum.value + "')]]")
                .click();
    }

    public File downloadFileByInfoBlockPopup() throws FileNotFoundException {
        return $x("//a[contains (text(),'Download file')]")
                .shouldBe(Condition.visible)
                .download();
    }
}
