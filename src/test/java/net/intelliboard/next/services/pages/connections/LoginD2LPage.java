package net.intelliboard.next.services.pages.connections;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import net.intelliboard.next.IBNextAbstractTest;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class LoginD2LPage extends IBNextAbstractTest {

    private final SelenideElement loginField =$x("//input[@id='userName']");
    private final SelenideElement passwordField =$x("//input[@id='password']");
    private final SelenideElement loginButton =$x("//button");


    public static LoginD2LPage init(){
        $x("//d2l-navigation").shouldBe(Condition.visible, Duration.ofSeconds(100));
        return new LoginD2LPage();
    }
    @Step("D2L : Fill in Login field")
    public LoginD2LPage loginFill(String loginValue) {
        loginField.setValue(loginValue);
        return this;
    }

    @Step("D2L : Fill in password field")
    public LoginD2LPage fillPassword(String password) {
        passwordField.setValue(password);
        return this;
    }

    @Step("D2L : Submit login")
    public LmsFilterSettingPage confirmAuthorize() {
        loginButton.click();
        return LmsFilterSettingPage.init();
    }
}
