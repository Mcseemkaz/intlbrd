package net.intelliboard.next.services.pages.connections;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import net.intelliboard.next.services.IBNextURLs;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;
import static org.assertj.core.api.Assertions.assertThat;

public class LoginCanvasPage {
    private final SelenideElement emailField = $x("//input[@id='pseudonym_session_unique_id']");
    private final SelenideElement passwordField = $x("//input[@id='pseudonym_session_password']");
    private final SelenideElement buttonLogIn = $x("//button[@type='submit']");

    public static LoginCanvasPage init() {
        $x("//div[contains (@class,'ic-Login')]").shouldBe(Condition.visible, Duration.ofSeconds(90));
        String currentURL = WebDriverRunner.getWebDriver().getCurrentUrl();
        assertThat(currentURL).isEqualTo(IBNextURLs.LOGIN_CANVAS_PAGE);
        return new LoginCanvasPage();
    }

    @Step("Canvas : Fill in Email field")
    public LoginCanvasPage fillEmail(String email) {
        emailField.sendKeys(email);
        return this;
    }

    @Step("Canvas : Fill in password field")
    public LoginCanvasPage fillPassword(String password) {
        passwordField.sendKeys(password);
        return this;
    }

    @Step("Canvas : Submit Login form")
    public LoginOauth2ConfirmPage loginInCanvas() {
        buttonLogIn.click();
        return LoginOauth2ConfirmPage.init();
    }
}
