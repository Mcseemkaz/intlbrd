package net.intelliboard.next.services.pages.connections;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class LmsFilterSettingPage {
    private SelenideElement buttonSave =
            $x("//button[@type=\"submit\" and normalize-space()='Save']");

    public static LmsFilterSettingPage init() {
        $x("//div[contains (@class,'content-header')]")
                .shouldBe(Condition.visible, Duration.ofSeconds(100));
        return new LmsFilterSettingPage();
    }

    public ConnectionsListPage saveFilterSettings() {
        buttonSave.shouldBe(Condition.visible).click();
        return ConnectionsListPage.init();
    }
}
