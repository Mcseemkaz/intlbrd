package net.intelliboard.next.services.pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;

public class CreateConnectionPage {
    public SelenideElement lmsNameField = $x("//input[@id=\"lmsName\"]");
    public SelenideElement clientIdField = $x("//input[@id=\"clientId\"]");
    public SelenideElement lmsUrlField = $x("//input[@id=\"lmsUrl\"]");
    public SelenideElement buttonContinue = $x("//button[@type=\"submit\"]");
}
