package net.intelliboard.next.login;

import com.codeborne.selenide.Condition;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.helpers.DataGenerator;
import net.intelliboard.next.services.pages.LoginPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class SignInTest extends IBNextAbstractTest {

    @Test
    @DisplayName("Check invalid email")
    public void testCheckInvalidEmail() {
        LoginPage loginPage = new LoginPage();
        open(MAIN_URL);
        loginPage.loginField.setValue(DataGenerator.getRandomValidEmail());
        loginPage.buttonSubmit.click();
        $x("//ul[@class='notifications']//div[contains(@class, 'error')]/div[contains(@class, 'info-block')]")
                .shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("Check invalid password")
    public void testCheckInvalidPassword() {
        LoginPage loginPage = new LoginPage();
        open(MAIN_URL);
        loginPage.loginField.setValue(USER_LOGIN);
        loginPage.buttonSubmit.click();
        loginPage.passwordField.setValue(DataGenerator.getRandomString());
        loginPage.buttonSubmit.click();
        $x("//div[contains (@class, 'form-group') and ./input[@id='login-email'] ]")
                .shouldHave(Condition.cssClass("has-error"));
        $x("//div[contains (@class, 'form-group') and ./input[@id='login-email'] ]/span[@class='help-block']")
                .shouldBe(Condition.visible);
    }
}
