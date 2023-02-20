package net.intelliboard.next.services.pages.login;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.pages.signup.SignUpInvitePage;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {

    private final SelenideElement loginField = $x("//input[@id='login-email']");
    private final SelenideElement passwordField = $x("//input[contains (@id, 'password')]");
    @Getter
    private final SelenideElement buttonSubmit = $x("//button[@type='submit']");
    @Getter
    private final SelenideElement errorMessageLogin = $x("//div[contains (@class, 'error-message') and preceding-sibling::input[@id='login-email']]");
    @Getter
    private final SelenideElement errorMessagePassword = $x("(//div[contains (@class,'login-password')]//div[@class='error-message'])[2]");


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

    public SignUpInvitePage goToRegistration() {
        $x("//a[contains (@class,'signup')]").click();
        return SignUpInvitePage.init();
    }

    public void submitForm() {
        buttonSubmit.click();
        $x("//header").shouldBe(Condition.visible, Duration.ofSeconds(100));
        ibNextAbstractTest.waitForPageLoaded();
    }
}