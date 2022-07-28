package net.intelliboard.next.services.pages.connections;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.IBNextURLs;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;
import static org.assertj.core.api.Assertions.assertThat;

public class LoginOauth2ConfirmPage {
    private SelenideElement inputAuthorizeField = $x("//input[@type=\"submit\"]");

    public static LoginOauth2ConfirmPage init() {
        IBNextAbstractTest ibNextAbstractTest = new IBNextAbstractTest();
        ibNextAbstractTest.waitForPageLoaded();
        $x("//div[contains (@class,'ic-Login-confirmation__content')]").shouldBe(Condition.visible,
                Duration.ofSeconds(100));
        String currentURL = WebDriverRunner.getWebDriver().getCurrentUrl();
        assertThat(currentURL).isEqualTo(IBNextURLs.LOGIN_OAUTH2_CONFIRM_PAGE);
        return new LoginOauth2ConfirmPage();
    }

    public LmsFilterSettingPage confirmAuthorize() {
        inputAuthorizeField.click();
        return LmsFilterSettingPage.init();
    }
}
