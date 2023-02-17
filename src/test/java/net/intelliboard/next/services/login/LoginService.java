package net.intelliboard.next.services.login;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import lombok.Getter;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.IBNextURLs;
import net.intelliboard.next.services.pages.login.LoginPage;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.open;

public class LoginService extends IBNextAbstractTest {

    @Getter
    private static String USER_LOGIN;
    @Getter
    private static String USER_PASS;

    static {
        try {
            USER_LOGIN = propertiesGetValue.getPropertyValue("user_login");
            USER_PASS = propertiesGetValue.getPropertyValue("user_pass");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Step("Login into App")
    public static void loginAppUI(String userLogin, String userPass) {

        open(IBNextURLs.LOGIN_PAGE);
        clearCookiesAndRefresh();

        LoginPage.init()
                .fillInLoginFiled(userLogin)
                .fillInPassFiled(userPass)
                .submitForm();
    }

    public static void clearCookiesAndRefresh() {
        WebDriverRunner.clearBrowserCache();
        WebDriverRunner.getWebDriver().manage().deleteAllCookies();
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
        Selenide.refresh();
    }
}
