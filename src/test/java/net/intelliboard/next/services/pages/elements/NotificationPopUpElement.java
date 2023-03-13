package net.intelliboard.next.services.pages.elements;

import com.codeborne.selenide.Condition;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class NotificationPopUpElement {

    public static NotificationPopUpElement init() {
        $x("//div[contains (@class, 'info-block')]")
                .should(Condition.visible, Duration.ofSeconds(30));
        return new NotificationPopUpElement();
    }

    public String getPopUpText() {
        return $x("//div[contains (@class, 'info-block')]//p")
                .getText();
    }
}
