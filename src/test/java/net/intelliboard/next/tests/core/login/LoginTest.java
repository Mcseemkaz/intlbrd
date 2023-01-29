package net.intelliboard.next.tests.core.login;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Feature;
import io.qameta.allure.Flaky;
import lombok.extern.java.Log;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.IBNextURLs;
import net.intelliboard.next.services.helpers.DataGenerator;
import net.intelliboard.next.services.login.LoginService;
import net.intelliboard.next.services.pages.login.LoginPage;
import net.intelliboard.next.services.pages.dashboard.DashboardPage;
import net.intelliboard.next.services.pages.header.HeaderObject;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

@Feature("Login")
@Tag("Login")
class LoginTest extends IBNextAbstractTest {

    @Flaky
    @Test
    @DisplayName("SP-T22: Verify success login to IB Next")
    @Tags(value = {@Tag("smoke"), @Tag("critical"), @Tag("SP-T22")})
    void testLoginApp() {
        LoginService.loginAppUI(LoginService.getUSER_LOGIN(),
                LoginService.getUSER_PASS());
    }

    @Test
    @Tags(value = {@Tag("regression"), @Tag("normal"), @Tag("SP-T24")})
    @DisplayName("SP-T24: Verify validation of entering an invalid email address")
    void testCheckInvalidEmail() {
        LoginService.clearCookiesAndRefresh();
        open(IBNextURLs.LOGIN_PAGE);

        LoginPage loginPage = LoginPage.init();

        loginPage
                .fillInLoginFiled(DataGenerator.getRandomValidEmail())
                .getButtonSubmit()
                .click();

        loginPage
                .getErrorMessageLogin()
                .shouldBe(Condition.visible);
    }

    @Test
    @Tags(value = {@Tag("regression"), @Tag("normal"), @Tag("SP-T36")})
    @DisplayName("SP-T36: Verify validation of entering an invalid password")
    void testCheckInvalidPassword() {
        LoginService.clearCookiesAndRefresh();
        open(IBNextURLs.LOGIN_PAGE);

        LoginPage loginPage = LoginPage.init();
        loginPage
                .fillInLoginFiled(LoginService.getUSER_LOGIN())
                .fillInPassFiled(DataGenerator.getRandomString())
                .getButtonSubmit()
                .click();

        loginPage
                .getErrorMessagePassword()
                .shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("SP-T813: Integrations page opens when user has any connections")
    @Tags(value = {@Tag("smoke"), @Tag("normal"), @Tag("SP-T813")})
    void testIntegrationPageIfUser() {
        DashboardPage.init();
    }

    @Test
    @DisplayName("SP-T732: Not able to view previous pages if the user is logged out")
    @Tags(value = {@Tag("normal"), @Tag("SP-T732")})
    void testUnableGetPreviousPageAfterLogout() {

        open(IBNextURLs.LIBRARY_MAIN);

        HeaderObject
                .init()
                .openDropDownMenu()
                .logOut();

        Selenide.back();

        LoginPage.init();
    }
}
