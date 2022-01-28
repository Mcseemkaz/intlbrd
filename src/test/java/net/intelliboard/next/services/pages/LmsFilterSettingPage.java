package net.intelliboard.next.services.pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;

public class LmsFilterSettingPage {
    public SelenideElement buttonSave = $x("//button[@type=\"submit\" and normalize-space()='Save']");
}
