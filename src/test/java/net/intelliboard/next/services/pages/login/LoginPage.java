package net.intelliboard.next.services.pages.login;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.pages.elements.spinners.PageSpinner;
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

    private static final SelenideElement authForm = $x("//form[@class='auth-form']");

    static IBNextAbstractTest ibNextAbstractTest = new IBNextAbstractTest();

    public static LoginPage init() {
        authForm.shouldBe(Condition.visible, Duration.ofSeconds(100));
        ibNextAbstractTest.waitForPageLoaded();
        return new LoginPage();
    }

    @Step("Fill in Login field")
    public LoginPage fillInLoginFiled(String login) {
        loginField.setValue(login);
        return this;
    }

    @Step("Fill in Password field")
    public LoginPage fillInPassFiled(String pass) {
        passwordField.setValue(pass);
        return this;
    }

    @Step("Go To Registration Page")
    public SignUpInvitePage goToRegistration() {
        $x("//a[contains (@class,'signup')]").click();
        return SignUpInvitePage.init();
    }

    @Step("Submit Login Form and Login in The App")
    public void submitForm() {
        continueLogin();
        authForm.should(Condition.not(Condition.visible), Duration.ofSeconds(100));
        PageSpinner.waitPreloader();
        PageSpinner.waitSpinner();
        ibNextAbstractTest.waitForPageLoaded();
    }

    @Step("Proceed Login")
    public void continueLogin() {
        buttonSubmit.click();
    }
}