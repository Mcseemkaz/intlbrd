package net.intelliboard.next.tests.core.login;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.IBNextURLs;
import net.intelliboard.next.services.helpers.DataGenerator;
import net.intelliboard.next.services.login.LoginService;
import net.intelliboard.next.services.pages.login.LoginPage;
import net.intelliboard.next.services.pages.dashboard.DashboardPage;
import net.intelliboard.next.services.pages.header.HeaderObject;
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
    public void testLoginApp() {
        WebDriverRunner.getWebDriver().manage().deleteAllCookies();
        LoginService.loginAppUI(LoginService.getUSER_LOGIN(),
                LoginService.getUSER_PASS());
    }

    @Test
    @Tags(value = {@Tag("regression"), @Tag("normal"), @Tag("SP-T24")})
    @DisplayName("SP-T24: Verify validation of entering an invalid email address")
    public void testCheckInvalidEmail() {
        WebDriverRunner.getWebDriver().manage().deleteAllCookies();
        open(IBNextURLs.MAIN_URL);
        LoginPage.init()
                .fillInLoginFiled(DataGenerator.getRandomValidEmail());
        LoginPage.init()
                .getButtonSubmit().click();

        $x("//div[contains (@class, 'error-message') and preceding-sibling::input[@id='login-email']]")
                .shouldBe(Condition.visible);
    }

    @Test
    @Tags(value = {@Tag("regression"), @Tag("normal"), @Tag("SP-T36")})
    @DisplayName("SP-T36: Verify validation of entering an invalid password")
    public void testCheckInvalidPassword() {
        WebDriverRunner.getWebDriver().manage().deleteAllCookies();
        open(IBNextURLs.MAIN_URL);
        LoginPage.init()
                .fillInLoginFiled(LoginService.getUSER_LOGIN())
                .fillInPassFiled(DataGenerator.getRandomString());
        LoginPage.init().getButtonSubmit().click();

        $x("//div[contains (@class, 'error-message') and preceding-sibling::input[@id='login-password']]")
                .shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("SP-T813: Integrations page opens when user has any connections")
    @Tags(value = {@Tag("smoke"), @Tag("normal"), @Tag("SP-T813")})
    public void testIntegrationPageIfUser() {
        DashboardPage.init();
    }

    @Test
    @DisplayName("SP-TXXX: Logout from the app")
    @Tags(value = {@Tag("smoke"), @Tag("critical"), @Tag("SP-TXXX")})
    public void testLogout() {
        HeaderObject
                .init()
                .openDropDownMenu()
                .logOut();
    }
}
