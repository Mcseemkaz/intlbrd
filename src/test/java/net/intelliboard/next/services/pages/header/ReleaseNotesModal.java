package net.intelliboard.next.services.pages.header;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import net.intelliboard.next.AbstractTest;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class ReleaseNotesModal {

    public static SelenideElement releaseModal =
            $x("//div[contains (@class,'release-notes-modal')]");

    public static ReleaseNotesModal init() {
        Selenide.sleep(AbstractTest.SLEEP_TIMEOUT_SHORT);
        releaseModal
                .shouldBe(Condition.visible);
        return new ReleaseNotesModal();
    }

    public boolean isReleaseNoteCardsExisted() {
        return !$$x("//div[contains (@class,'release-notes-modal')]//div[@class='card']")
                .isEmpty();
    }

    @Step("Close Release Modal")
    public void closeReleaseModal() {
        $x("//div[@class='release-notes-modal']//button[@aria-label='Close']/ion-icon")
                .click();
        releaseModal
                .should(Condition.not(Condition.visible));
    }
}
