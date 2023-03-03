package net.intelliboard.next.services.pages.incontact;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import net.intelliboard.next.services.pages.elements.spinners.PageSpinner;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static net.intelliboard.next.AbstractTest.SLEEP_TIMEOUT_SHORT;

public class InContactMainPage {

    public static InContactMainPage init() {
        $x("//div[@id='in-contact']")
                .shouldBe(Condition.visible, Duration.ofSeconds(60));
        PageSpinner.waitSpinner();
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

    public InContactMainPage addUserContactInformation(
            String userName,
            String key,
            String value
    ) {
        openAction(userName, InContactActionEnum.EDIT_MENU);
        InContactEditUserContactInfoModal
                .init()
                .addData(key, value);
        return this;
    }

    public InContactMainPage deleteAllUserContactInformation(String userName) {
        openAction(userName, InContactActionEnum.EDIT_MENU);
        InContactEditUserContactInfoModal
                .init()
                .deleteAllData();
        return this;
    }

    public boolean checkEventExist(String eventName, LocalDateTime date) {
        Selenide.sleep(SLEEP_TIMEOUT_SHORT);
        openEventPopup(date);
        return $x("//td[.//p[contains (text(),'" +
                date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) +
                "')]]//p[contains (@class, 'events-text') and contains (text(),'" + eventName + "')]")
                .exists();
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

        PageSpinner.waitSpinner();
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

    public boolean checkAuthorOfEvent(String eventName, String authorName, LocalDateTime date) {
        openEventPopup(date);
        return $x("//div[contains (@class, 'all-event-day') and  .//p[contains (@class, 'all-events-text') and contains (text(),'" +
                eventName + "')]]//span[./ion-icon[@name='school']]").getText().contains(authorName);
    }

    public boolean checkUserOfEvent(String eventName, String userName, LocalDateTime date) {
        openEventPopup(date);
        return $x("//div[contains (@class, 'all-event-day') and  .//p[contains (@class, 'all-events-text') and contains (text(),'" +
                eventName + "')]]//h2[./ion-icon[@name='person']]").getText().contains(userName);
    }

    public boolean checkDateOfEvent(String eventName, LocalDateTime dateOfEvent, LocalDateTime date) {
        openEventPopup(date);
        return $x("//div[contains (@class, 'all-event-day') and  .//p[contains (@class, 'all-events-text') and contains (text(),'" +
                eventName + "')]]//p[contains (@class, 'events-metadata')]")
                .getText()
                .contains(dateOfEvent.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }

    public InContactFilterModalElement openFilter() {
        $x("//button[@class='top-filters__button']")
                .click();
        return InContactFilterModalElement.init();
    }

    public int getNumberOfContacts() {
        return $$x("//ul[contains (@class,'students')]/li[contains (@class,'row')]")
                .size();
    }

    public boolean isUserDataExist(String userName, String key, String value) {
        openAction(userName, InContactActionEnum.SEE_INFORMATION_MENU);
        return InContactSeeUserContactInfoModal
                .init()
                .userDataExist(key, value);
    }

    public void switchMode(InContactViewOptionEnum viewOptionEnum) {
        $x("//button[ ./ion-icon[@name='" + viewOptionEnum.value + "']]")
                .click();
    }
}
