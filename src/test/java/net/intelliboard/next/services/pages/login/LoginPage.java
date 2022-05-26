package net.intelliboard.next.services.pages.login;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.pages.signup.SignUpFormPage;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {

    private SelenideElement loginField = $x("//input[@id='login-email']");
    private SelenideElement passwordField = $x("//input[@id='login-password']");
    @Getter
    private SelenideElement buttonSubmit = $x("//button[@type='submit']");

    static IBNextAbstractTest ibNextAbstractTest = new IBNextAbstractTest();

    public static LoginPage init() {
        $x("//form[@class='auth-form']")
                .shouldBe(Condition.visible, Duration.ofSeconds(100));
        ibNextAbstractTest.waitForPageLoaded();
        return new LoginPage();
    }

    public LoginPage fillInLoginFiled(String login) {
        loginField.setValue(login);
        return this;
    }

    public LoginPage fillInPassFiled(String pass) {
        passwordField.setValue(pass);
        return this;
    }

    public SignUpFormPage goToRegistration() {
        $x("//a[contains (@class,'signup')]").click();
        return SignUpFormPage.init();
    }

    public void submitForm() {
        buttonSubmit.click();
        $x("//header").shouldBe(Condition.visible, Duration.ofSeconds(100));
        ibNextAbstractTest.waitForPageLoaded();
    }
}


