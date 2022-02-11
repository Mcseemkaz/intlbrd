package net.intelliboard.next.tests.core.login;

import com.codeborne.selenide.Condition;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.IBNextURLs;
import net.intelliboard.next.services.helpers.DataGenerator;
import net.intelliboard.next.services.pages.LoginPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class LoginTest extends IBNextAbstractTest {

    @Test
    @DisplayName("SP-T22: Verify success login to IB Next")
    @Tags(value = {@Tag("smoke"), @Tag("critical"), @Tag("SP-T22")})
    public void testLoginAppSPT22() {
        loginAppUI(USER_LOGIN, USER_PASS);
    }

    @Test
    @Tags(value = {@Tag("regression"), @Tag("normal"), @Tag("SP-T24")})
    @DisplayName("SP-T24: Verify validation of entering an invalid email address")
    public void testCheckInvalidEmailSPT24() {
        LoginPage loginPage = new LoginPage();
        open(IBNextURLs.MAIN_URL);
        loginPage.loginField.setValue(DataGenerator.getRandomValidEmail());
        loginPage.buttonSubmit.click();
        $x("//ul[@class='notifications']//div[contains(@class, 'error')]/div[contains(@class, 'info-block')]")
                .shouldBe(Condition.visible);
    }

    @Test
    @Tags(value = {@Tag("regression"), @Tag("normal"), @Tag("SP-T36")})
    @DisplayName("SP-T36: Verify validation of entering an invalid password")
    public void testCheckInvalidPassword() {
        LoginPage loginPage = new LoginPage();
        open(IBNextURLs.MAIN_URL);
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
