package net.intelliboard.next.services.pages.login;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import net.intelliboard.next.services.pages.signup.SignUpFormPage;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {

    public SelenideElement loginField = $x("//input[@id=\"login-email\"]");
    public SelenideElement passwordField = $x("//input[@id=\"login-password\"]");
    public SelenideElement buttonSubmit = $x("//button[@type=\"submit\"]");

    public static LoginPage init(){
        $x("//form[@class='auth-form']")
                .shouldBe(Condition.visible, Duration.ofSeconds(100));
        return new LoginPage();
    }

    public SignUpFormPage goToRegistration() {
        $x("//a[contains (@class,'signup')]").click();
        return SignUpFormPage.init();
    }
}


