package net.intelliboard.next.services.pages.blackboard;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;

import java.io.IOException;
import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;
import static net.intelliboard.next.AbstractTest.SLEEP_TIMEOUT_SHORT;

public class BlackBoardAdminInstalledToolsPage {
    public static BlackBoardAdminInstalledToolsPage init() throws IOException {
        Selenide.sleep(SLEEP_TIMEOUT_SHORT);
        $x("//span[@id='pageTitleText' and contains(text(),'Installed Tools')]")
                .should(Condition.visible, Duration.ofSeconds(120));
        return new BlackBoardAdminInstalledToolsPage();
    }

    public BlackBoardAdminBlockSettingsPage openSettings() throws IOException {
        Selenide.sleep(SLEEP_TIMEOUT_SHORT);
        System.err.println($x("//tr[@id='unfilteredList_row:0']").exists());
        System.err.println($x("//tr[@id='unfilteredList_row:0']").exists());
        $x("//tr[@id='unfilteredList_row:0']").hover();
        $x("//tr[@id='unfilteredList_row:0']//a[contains (@id,'cmlink')]").click();
        $x("//a[@title='Settings']").click();
        return BlackBoardAdminBlockSettingsPage.init();
    }
}
