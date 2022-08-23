package net.intelliboard.next.services.pages.connections;

import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import net.intelliboard.next.IBNextAbstractTest;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class LoginOauth2ConfirmPage {

    static IBNextAbstractTest ibNextAbstractTest = new IBNextAbstractTest();

    private SelenideElement getButtonAuthorize() {
        return $x("//input[@type='submit' and (contains(@value,'Authorize'))]");
    }
//    private SelenideElement buttonAuthorize = $x("//input[@type='submit' and (contains(@value,'Authorize'))]");
//    private SelenideElement buttonWaiting = $x("//input[@type='submit' and (contains(@value,'Please wait...'))]");

    public static LoginOauth2ConfirmPage init() {

        ibNextAbstractTest.waitForPageLoaded();
        $x("//div[contains (@class,'ic-Login-confirmation__content')]").shouldBe(Condition.visible,
                Duration.ofSeconds(100));
        return new LoginOauth2ConfirmPage();
    }

    public LmsFilterSettingPage confirmAuthorize() {
        ibNextAbstractTest.waitForPageLoaded();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        getButtonAuthorize().click(ClickOptions.withTimeout(Duration.ofSeconds(120)));
        return LmsFilterSettingPage.init();
    }
}
