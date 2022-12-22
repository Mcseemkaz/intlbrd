package net.intelliboard.next.services.pages.incontact;

import com.codeborne.selenide.Condition;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class InContactMainPage {

    public static InContactMainPage init() {
        $x("//div[@id='in-contact']")
                .shouldBe(Condition.visible, Duration.ofSeconds(30));
        return new InContactMainPage();
    }

}
