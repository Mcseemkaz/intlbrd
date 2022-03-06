package net.intelliboard.next.services.pages;

import com.codeborne.selenide.SelenideElement;
import net.intelliboard.next.services.pages.signup.SignUpFormPage;
import net.intelliboard.next.services.pages.signup.SignUpInvitePage;

import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {

    public SelenideElement loginField = $x("//input[@id=\"login-email\"]");
    public SelenideElement passwordField = $x("//input[@id=\"login-password\"]");
    public SelenideElement buttonSubmit = $x("//button[@type=\"submit\"]");

    public SignUpFormPage goToRegistartion() {
        $x("//a[contains (@class,'signup')]").click();
        return SignUpFormPage.init();
    }

}


