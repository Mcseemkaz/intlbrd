package net.intelliboard.next.services.pages.connections;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import net.intelliboard.next.services.IBNextURLs;

import static com.codeborne.selenide.Selenide.$x;
import static org.assertj.core.api.Assertions.assertThat;

public class LoginCanvasPage {
    private SelenideElement emailField = $x("//input[@id=\"pseudonym_session_unique_id\"]");
    private SelenideElement passwordField = $x("//input[@id=\"pseudonym_session_password\"]");
    private SelenideElement buttonLogIn = $x("//button[@type=\"submit\"]");

    public static LoginCanvasPage init() {
        $x("//div[contains (@class,'ic-Login')]").shouldBe(Condition.visible);
        String currentURL = WebDriverRunner.getWebDriver().getCurrentUrl();
        assertThat(currentURL).isEqualTo(IBNextURLs.LOGIN_CANVAS_PAGE);
        return new LoginCanvasPage();
    }

    public LoginCanvasPage fillEmail(String email) {
        emailField.setValue(email);
        return this;
    }

    public LoginCanvasPage fillPassword(String password) {
        passwordField.setValue(password);
        return this;
    }

    public LoginOauth2ConfirmPage loginInCanvas() {
        buttonLogIn.click();
        return LoginOauth2ConfirmPage.init();
    }
}
