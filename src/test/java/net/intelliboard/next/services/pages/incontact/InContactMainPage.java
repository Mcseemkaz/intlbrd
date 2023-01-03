package net.intelliboard.next.services.pages.incontact;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
                .setAttemped()
                .submitForm();

        return this;
    }

    public boolean checkEventExist(String eventName, LocalDateTime date) {
        openEventPopup(date);
        return $x("//td[.//p[contains (text(),'" +
                date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) +
                "')]]//p[contains (@class, 'events-text') and contains (text(),'" + eventName + "')]").exists();
    }

    public InContactMainPage deleteEvent(String eventName, LocalDateTime date) {
        openEventPopup(date);
        $x("//div[contains (@class, 'all-event-day') and  .//p[contains (@class, 'all-events-text') and contains (text(),'" +
                eventName +
                "')]]//button[@class='delete']")
                .click();

        Selenide
                .switchTo()
                .alert()
                .accept();
        return this;
    }

    private void openEventPopup(LocalDateTime date) {

        SelenideElement moreLink = $x("//td[.//p[contains (text(),'" +
                date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) +
                "')]]//a[contains (text(), 'more')]");
        if ($x("//td[.//p[contains (text(),'" + date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "')]]")
                .exists()) {

            if (moreLink.exists()) {
                moreLink.click();
            } else {
                $x("//td[.//p[contains (text(),'" +
                        date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) +
                        "')]]//div[contains (@class, 'all-event-day')]")
                        .click();
            }
        }
    }

    private void openAction(String userName, InContactActionEnum type) {
        $x("//li [.//div[@class='name-container' and contains (@title,'" + userName + "')]]//div[contains (@class, 'block-dots')]")
                .click();

        $x("//li [.//div[@class='name-container' and contains (@title,'" + userName + "')]]//a//*[contains (text(),'" + type.value + "')]")
                .click();
    }
}
