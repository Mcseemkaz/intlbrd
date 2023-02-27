package net.intelliboard.next.services.login;

import com.codeborne.selenide.WebDriverRunner;
import net.intelliboard.next.services.IBNextURLs;
import net.intelliboard.next.services.webdriver.CookieManager;
import org.openqa.selenium.Cookie;

import java.time.Duration;
import java.util.Set;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class LoginCookieHandler {

    private static boolean isCookieSet;
    private static Set<Cookie> cookies;

    public static void run() {

        if (isCookieSet) {
            open(IBNextURLs.MAIN_URL);
            WebDriverRunner.getWebDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(180));
            setAllCookies();

            //TODO [MO] need to debug why cookies are not applied without refresh
/*
Due to some glitch with browser sometimes cookie set does not work and test appears on the login page again so that a bypass for that cases
 */
            open(IBNextURLs.MAIN_URL);
            if ($x("//form[@class='auth-form']").isDisplayed()) {
                LoginService.
                        loginAppUI(LoginService.getUSER_LOGIN(), LoginService.getUSER_PASS());
            }
        } else {
            LoginService.
                    loginAppUI(LoginService.getUSER_LOGIN(), LoginService.getUSER_PASS());
            getAndSaveAllCookies();
            isCookieSet = true;
        }
    }

    public static void getAndSaveAllCookies() {
        CookieManager cookieManager = new CookieManager();
        cookies = cookieManager.getAllCookies();
    }

    public static void setAllCookies() {
        CookieManager cookieManager = new CookieManager();
        cookies.forEach(System.out::print);
        cookies.forEach(cookieManager::setCookie);
    }

    public static void setIsCookieSet(boolean bool) {
        isCookieSet = bool;
    }
}
