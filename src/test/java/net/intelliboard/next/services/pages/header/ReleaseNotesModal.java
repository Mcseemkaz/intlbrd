package net.intelliboard.next.services.pages.header;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class ReleaseNotesModal {
    public static ReleaseNotesModal init() {
        Selenide.sleep(2000);
        $x("//div[contains (@class,'release-notes-modal')]")
                .shouldBe(Condition.visible);
        return new ReleaseNotesModal();
    }

    public boolean isReleaseNoteCardsExisted(){
        return !$$x("//div[contains (@class,'release-notes-modal')]//div[@class='card']")
                .isEmpty();
    }
}
