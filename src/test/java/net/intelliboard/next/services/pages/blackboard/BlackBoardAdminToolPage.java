package net.intelliboard.next.services.pages.blackboard;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;

import java.io.IOException;
import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;
import static net.intelliboard.next.AbstractTest.SLEEP_TIMEOUT_SHORT;

public class BlackBoardAdminToolPage {
    public static BlackBoardAdminToolPage init() throws IOException {
        Selenide.sleep(SLEEP_TIMEOUT_SHORT);
        $x("//div[contains(@class,'containerPortal')]").should(Condition.visible, Duration.ofSeconds(30));
        return new BlackBoardAdminToolPage();
    }

    public BlackBoardBuildingBlocksPage openBuildingBlocks() throws IOException {
        Selenide.sleep(SLEEP_TIMEOUT_SHORT);
        $x("//a[contains(text(),'Building Blocks')]").click();
        return BlackBoardBuildingBlocksPage.init();
    }


}
