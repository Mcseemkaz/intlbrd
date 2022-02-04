package net.intelliboard.next.services.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class LmsFilterSettingPage {
    private SelenideElement buttonSave = $x("//button[@type=\"submit\" and normalize-space()='Save']");

    public void save() {
        buttonSave.shouldBe(Condition.visible).click();
    }
}
