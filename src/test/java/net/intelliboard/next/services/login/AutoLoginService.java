package net.intelliboard.next.services.login;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import net.intelliboard.next.AbstractTest;
import net.intelliboard.next.services.IBNextURLs;
import net.intelliboard.next.services.PropertiesGetValue;
import net.intelliboard.next.services.pages.elements.spinners.PageSpinner;
import net.intelliboard.next.services.pages.header.ReleaseNotesModal;

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

    @Step("Auto Login by Link")
    public static void autoLogin() {
        Selenide.open(String.format(IBNextURLs.AUTO_LOGIN, AUTO_LOGIN_EMAIL, AUTO_LOGIN_TOKEN));
        PageSpinner.waitPreloader();
        PageSpinner.waitSpinner();

        Selenide.sleep(AbstractTest.SLEEP_TIMEOUT_SHORT);
        if (ReleaseNotesModal.releaseModal.isDisplayed()) {
            ReleaseNotesModal.init().closeReleaseModal();
        }
    }
}
