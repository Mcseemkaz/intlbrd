package net.intelliboard.next.services.pages.blackboard;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;

import java.io.IOException;
import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;
import static net.intelliboard.next.AbstractTest.SLEEP_TIMEOUT_SHORT;

public class BlackBoardAdminToolMainPage {

    public static BlackBoardAdminToolMainPage init() throws IOException {
        Selenide.sleep(SLEEP_TIMEOUT_SHORT);
        $x("//div[@id='globalNavPageContentArea']")
                .should(Condition.visible, Duration.ofSeconds(60));
        return new BlackBoardAdminToolMainPage();
    }

    public void selectBlackBoardMenuItem(BlackBoardSideMenuOptionsEnum option) {
        Selenide.sleep(SLEEP_TIMEOUT_SHORT);
        $x("//span[contains(text(),'" + option.value + "')]").click();
    }
}
