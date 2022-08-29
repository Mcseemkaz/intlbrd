package net.intelliboard.next.services.pages.blackboard;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import net.intelliboard.next.services.PropertiesGetValue;

import java.io.IOException;
import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class BlackBoardAdminToolMainPage {

    static PropertiesGetValue propertiesGetValue = new PropertiesGetValue();

    public static BlackBoardAdminToolMainPage init() throws IOException {
        Selenide.sleep(Long.parseLong(propertiesGetValue.getPropertyValue("sleep_time")));
        $x("//div[@id='globalNavPageContentArea']")
                .should(Condition.visible, Duration.ofSeconds(60));
        return new BlackBoardAdminToolMainPage();
    }

    public void selectBlackBoardMenuItem(BlackBoardSideMenuOptionsEnum option) throws IOException {
        Selenide.sleep(Long.parseLong(propertiesGetValue.getPropertyValue("sleep_time")));
        $x("//span[contains(text(),'"+option.value+"')]").click();
    }
}
