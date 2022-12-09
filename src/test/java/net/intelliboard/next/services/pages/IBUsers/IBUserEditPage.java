package net.intelliboard.next.services.pages.IBUsers;

import com.codeborne.selenide.Condition;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class IBUserEditPage {

    public static IBUserEditPage init() {
        $x("//form[contains (@action, '/profile')]").shouldBe(Condition.visible, Duration.ofSeconds(90));
        return new IBUserEditPage();
    }

    public IBUserEditPage setFirstName(String firstName) {
        $x("//input[@id='first_name']").setValue(firstName);
        return this;
    }

    public IBUserPage submitForm() {
        $x("//button[@type='submit' and contains (text(),'Save')]").click();
        return IBUserPage.init();
    }
}
