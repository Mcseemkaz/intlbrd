package net.intelliboard.next.services.pages.connections;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import net.intelliboard.next.services.pages.elements.spinners.PageSpinner;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class LmsFilterSettingPage {
    private final SelenideElement buttonSave =
            $x("//button[@type=\"submit\" and normalize-space()='Save']");

    @Step("LMS Filter Settings Page init")
    public static LmsFilterSettingPage init() {
        PageSpinner.waitPreloader();
        PageSpinner.waitSpinner();
        $x("//div[contains (@class,'content-header')]")
                .shouldBe(Condition.visible, Duration.ofSeconds(120));
        return new LmsFilterSettingPage();
    }

    @Step("Save LMS Filters")
    public ConnectionsListPage saveFilterSettings() {
        PageSpinner.waitPreloader();
        PageSpinner.waitSpinner();
        buttonSave.shouldBe(Condition.visible, Duration.ofSeconds(280))
                .click();
        return ConnectionsListPage.init();
    }
}
