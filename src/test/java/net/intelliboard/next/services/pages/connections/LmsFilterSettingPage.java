package net.intelliboard.next.services.pages.connections;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import net.intelliboard.next.services.pages.elements.spinners.PageSpinner;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class LmsFilterSettingPage {
    private SelenideElement buttonSave =
            $x("//button[@type=\"submit\" and normalize-space()='Save']");

    public static LmsFilterSettingPage init() {
        PageSpinner.waitPreloader();
        PageSpinner.waitSpinner();
        $x("//div[contains (@class,'content-header')]")
                .shouldBe(Condition.visible, Duration.ofSeconds(120));
        return new LmsFilterSettingPage();
    }

    public ConnectionsListPage saveFilterSettings() {
        buttonSave.shouldBe(Condition.visible, Duration.ofSeconds(120)).click();
        return ConnectionsListPage.init();
    }
}
