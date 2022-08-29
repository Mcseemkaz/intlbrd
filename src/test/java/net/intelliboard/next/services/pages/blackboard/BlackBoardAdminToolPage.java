package net.intelliboard.next.services.pages.blackboard;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;

import java.io.IOException;
import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;
import static net.intelliboard.next.AbstractTest.propertiesGetValue;

public class BlackBoardAdminToolPage {
    public static BlackBoardAdminToolPage init() throws IOException {
        Selenide.sleep(Long.parseLong(propertiesGetValue.getPropertyValue("sleep_time")));
        $x("//div[contains(@class,'containerPortal')]").should(Condition.visible, Duration.ofSeconds(30));
        return new BlackBoardAdminToolPage();
    }

    public BlackBoardBuildingBlocksPage openBuildingBlocks() throws IOException {
        Selenide.sleep(Long.parseLong(propertiesGetValue.getPropertyValue("sleep_time")));
        $x("//a[contains(text(),'Building Blocks')]").click();
        return BlackBoardBuildingBlocksPage.init();
    }


}
