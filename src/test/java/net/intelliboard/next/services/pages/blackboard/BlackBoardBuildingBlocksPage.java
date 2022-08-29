package net.intelliboard.next.services.pages.blackboard;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;

import java.io.IOException;
import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;
import static net.intelliboard.next.AbstractTest.propertiesGetValue;

public class BlackBoardBuildingBlocksPage {
    public static BlackBoardBuildingBlocksPage init() throws IOException {
        Selenide.sleep(Long.parseLong(propertiesGetValue.getPropertyValue("sleep_time")));
        $x("//span[@id='pageTitleText']/span[contains(text(),'Building Blocks')]")
                .should(Condition.visible, Duration.ofSeconds(120));
        return new BlackBoardBuildingBlocksPage();
    }

    public BlackBoardAdminInstalledToolsPage selectInstalledTools() throws IOException {
        Selenide.sleep(Long.parseLong(propertiesGetValue.getPropertyValue("sleep_time")));
        $x("//a[contains(text(),'Installed Tools')]").click();
        return BlackBoardAdminInstalledToolsPage.init();
    }
}
