package net.intelliboard.next.services.pages.blackboard;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;

import java.io.IOException;
import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;
import static net.intelliboard.next.AbstractTest.propertiesGetValue;

public class BlackBoardAdminBlockSettingsPage {
    public static BlackBoardAdminBlockSettingsPage init() throws IOException {
        Selenide.sleep(Long.parseLong(propertiesGetValue.getPropertyValue("sleep_time")));
        $x("//span[@class='main-content-title' and contains (text(),'BLOCK SETTINGS')]")
                .should(Condition.visible, Duration.ofSeconds(120));

        return new BlackBoardAdminBlockSettingsPage();
    }

    public BlackBoardAdminBlockSettingsPage performDump() {
        Selenide.sleep(5000);
        $x("//input[@id='initDbDump']")
                .scrollTo()
                .should(Condition.enabled, Duration.ofSeconds(30))
                .click();

        Selenide.confirm();
        $x("//input[@id='stopInitDump']").scrollTo();
        Selenide.sleep(30000);
        $x("//li[@id='dumpStatuses']/span[contains (text(), 'Common dump')]")
                .should(Condition.visible, Duration.ofSeconds(30));
        $x("//li[@id='dumpStatuses']/span[contains (text(), 'UserEnrollmentDataLoader')]")
                .scrollTo()
                .should(Condition.text("FINISHED"), Duration.ofMinutes(10));
        return this;
    }
}
