package net.intelliboard.next.services.pages.header;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$x;

public class SuccessBarModal {
    public static SuccessBarModal init() {
        $x("//div[contains (@class,'dropdown-menu')][.//h2[contains (text(),'Success Bar')]]")
                .shouldBe(Condition.visible);
        return new SuccessBarModal();
    }

    public ReleaseNotesModal openNewFeatures() {
        $x("//div[contains (@class,'dropdown-menu')][.//h2[contains (text(),'Success Bar')]]//li[.//h3[contains (text(),'New Features!')]]")
                .click();
        return ReleaseNotesModal.init();
    }
}
