package net.intelliboard.next.services.pages.incontact;

import com.codeborne.selenide.Condition;

import java.time.Duration;
import java.time.LocalDateTime;

import static com.codeborne.selenide.Selenide.$x;

public class InContactMainPage {

    public static InContactMainPage init() {
        $x("//div[@id='in-contact']")
                .shouldBe(Condition.visible, Duration.ofSeconds(30));
        return new InContactMainPage();
    }

    public InContactMainPage addNewMenu(
            String userName,
            LocalDateTime date,
            String textMessage
    ) {
        openAction(userName, InContactActionEnum.ADD_NEW_MENU);
        InContactMenuModalElement
                .init()
                .setDate(date)
                .setOption("Communication", "[All Options]")
                .setOption("Behavior", "[All Options]")
                .setTextMessage(textMessage)
                .submitForm();

        return this;
    }


    private void openAction(String userName, InContactActionEnum type) {
        $x("//li [.//div[@class='name-container' and contains (@title,'" + userName + "')]]//div[contains (@class, 'block-dots')]")
                .click();

        $x("//li [.//div[@class='name-container' and contains (@title,'" + userName + "')]]//a//*[contains (text(),'" + type.value + "')]")
                .click();
    }

}
