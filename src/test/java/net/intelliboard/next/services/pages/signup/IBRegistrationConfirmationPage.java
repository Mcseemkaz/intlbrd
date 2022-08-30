package net.intelliboard.next.services.pages.signup;

import com.codeborne.selenide.Condition;
import net.intelliboard.next.services.pages.login.LoginPage;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class IBRegistrationConfirmationPage {
    public static IBRegistrationConfirmationPage init() {
        $x("//div[@id='content']").should(Condition.visible, Duration.ofSeconds(120));
        return new IBRegistrationConfirmationPage();
    }

    public LoginPage goToMyLogin() {
        $x("//a[contains (@href,'/login')]").click();
        return LoginPage.init();
    }
}
