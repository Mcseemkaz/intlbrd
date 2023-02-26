package net.intelliboard.next.services.pages.signup;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import net.intelliboard.next.services.IBNextURLs;

import static com.codeborne.selenide.Selenide.$x;
import static org.assertj.core.api.Assertions.*;


public class SignUpInvitePage {

    public static SignUpInvitePage init() {
        $x("//div[contains (@class,'signup-page')]").shouldBe(Condition.visible);
        String currentURL = WebDriverRunner.getWebDriver().getCurrentUrl();
        assertThat(currentURL).isEqualTo(IBNextURLs.SIGNUP_INVITATION_PAGE);
        return new SignUpInvitePage();
    }

    @Step("Proceed Registration")
    public SignUpFormPage continueRegistration() {
        $x("//button[@type='submit']").click();
        return SignUpFormPage.init();
    }

    @Step("Fill in Invitation Code")
    public SignUpInvitePage fillInInviteCode(String inviteCode) {
        $x("//input[@id='invite_code']").setValue(inviteCode);
        return this;
    }
}
