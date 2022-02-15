package net.intelliboard.next.services.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import net.intelliboard.next.services.IBNextURLs;

import static com.codeborne.selenide.Selenide.$x;
import static org.assertj.core.api.Assertions.assertThat;

public class LoginOauth2ConfirmPage {
    private SelenideElement inputAuthorizeField = $x("//input[@type=\"submit\"]");

    public static LoginOauth2ConfirmPage init() {
        $x("//div[contains (@class,'ic-Login-confirmation__content')]").shouldBe(Condition.visible);
        String currentURL = WebDriverRunner.getWebDriver().getCurrentUrl();
        assertThat(currentURL).isEqualTo(IBNextURLs.LOGIN_OAUTH2_CONFIRM_PAGE);
        return new LoginOauth2ConfirmPage();
    }

    public LmsFilterSettingPage confirmAuthorize() {
        inputAuthorizeField.click();
        return LmsFilterSettingPage.init();
    }
}
