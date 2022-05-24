package net.intelliboard.next.services.pages.connections;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import net.intelliboard.next.IBNextAbstractTest;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class LoginD2LPage extends IBNextAbstractTest {

    private SelenideElement loginField =$x("//input[@id='userName']");
    private SelenideElement passwordField =$x("//input[@id='password']");
    private SelenideElement loginButton =$x("//button");


    public static LoginD2LPage init(){
        $x("//d2l-navigation").shouldBe(Condition.visible, Duration.ofSeconds(100));
        return new LoginD2LPage();
    }

    public LoginD2LPage loginFill(String loginValue) {
        loginField.setValue(loginValue);
        return this;
    }

    public LoginD2LPage fillPassword(String password) {
        passwordField.setValue(password);
        return this;
    }

    public LmsFilterSettingPage confirmAuthorize() {
        loginButton.click();
        return LmsFilterSettingPage.init();
    }
}
