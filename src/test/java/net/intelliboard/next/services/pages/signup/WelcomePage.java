package net.intelliboard.next.services.pages.signup;

import com.codeborne.selenide.Condition;
import org.apache.commons.lang3.StringUtils;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class WelcomePage {
    public static WelcomePage init() {
        $x("//div[@class='welcome-wrapper']")
                .should(Condition.visible, Duration.ofSeconds(90));
        return new WelcomePage();
    }

    public boolean isWelcomeMessageIsExist(String userName) {
        String firstName = StringUtils.substringAfter(userName, ' ');

        return $x("//div[@class='welcome-wrapper']//h1[@class='welcome-title']")
                .getText()
                .contains(firstName);
    }
}
