package net.intelliboard.next.services.webdriver;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.Cookie;

import java.util.Set;

public class CookieManager {

    public Cookie getCookie(String cookieName){
        return WebDriverRunner.getWebDriver().manage().getCookieNamed(cookieName);
    }

    public Set<Cookie> getAllCookies(){
        return WebDriverRunner.getWebDriver().manage().getCookies();
    }

    public void setCookie(Cookie cook) {
        WebDriverRunner.getWebDriver().manage().addCookie(cook);
    }

    public void deleteAllCookies(){
        WebDriverRunner.getWebDriver().manage().deleteAllCookies();
    }
}
