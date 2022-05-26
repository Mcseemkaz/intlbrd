package net.intelliboard.next.services.login;

import lombok.Getter;
import lombok.Setter;
import net.intelliboard.next.services.IBNextURLs;
import net.intelliboard.next.services.webdriver.CookieManager;
import org.openqa.selenium.Cookie;

import java.util.Set;

import static com.codeborne.selenide.Selenide.open;

public class LoginCookieHandler {

    private static boolean isCookieSet;
    private static Set<Cookie> cookies;

    public static void run() {

        CookieManager cookieManager = new CookieManager();

        if (isCookieSet) {
            open(IBNextURLs.MAIN_URL);
            setAllCookies();
            //TODO [MO] need to debug why cookies are not applied without refresh
            open(IBNextURLs.MAIN_URL);
        } else {
            LoginService.
                    loginAppUI(LoginService.getUSER_LOGIN(), LoginService.getUSER_PASS());
            getAndSaveAllCookies();
            isCookieSet = true;
        }
    }

    public static void getAndSaveAllCookies(){
        CookieManager cookieManager = new CookieManager();
        cookies = cookieManager.getAllCookies();
    }

    public static void setAllCookies(){
        CookieManager cookieManager = new CookieManager();
        cookies.stream().forEach(cookieManager::setCookie);
    }

    public static void setIsCookieSet(boolean bool){
        isCookieSet = bool;
    }
}
