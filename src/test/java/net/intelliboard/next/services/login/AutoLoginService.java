package net.intelliboard.next.services.login;

import com.codeborne.selenide.Selenide;
import net.intelliboard.next.services.IBNextURLs;
import net.intelliboard.next.services.PropertiesGetValue;
import net.intelliboard.next.services.pages.elements.spinners.PageSpinner;

import java.io.IOException;

public class AutoLoginService {

    private static final String AUTO_LOGIN_TOKEN;
    private static final String AUTO_LOGIN_EMAIL;

    static {
        try {
            AUTO_LOGIN_TOKEN = new PropertiesGetValue().getPropertyValue("user_token");
            AUTO_LOGIN_EMAIL = new PropertiesGetValue().getPropertyValue("user_login");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void autoLogin() {
        Selenide.open(String.format(IBNextURLs.AUTO_LOGIN, AUTO_LOGIN_EMAIL, AUTO_LOGIN_TOKEN));
        Selenide.open(IBNextURLs.MY_INTELLIBOARD_PAGE);
        PageSpinner.waitPreloader();
        PageSpinner.waitSpinner();
    }
}
