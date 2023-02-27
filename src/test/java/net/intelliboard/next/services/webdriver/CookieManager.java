package net.intelliboard.next.services.webdriver;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.openqa.selenium.Cookie;

import java.util.Set;

public class CookieManager {

    public Cookie getCookie(String cookieName) {
        return WebDriverRunner.getWebDriver().manage().getCookieNamed(cookieName);
    }

    @Step("Get All Cookies")
    public Set<Cookie> getAllCookies() {
        return WebDriverRunner.getWebDriver().manage().getCookies();
    }

    @Step("Cookies set")
    public void setCookie(Cookie cook) {
        WebDriverRunner.getWebDriver().manage().addCookie(cook);
    }

    @Step("Delete All Cookies")
    public void deleteAllCookies() {
        WebDriverRunner.getWebDriver().manage().deleteAllCookies();
    }
}
