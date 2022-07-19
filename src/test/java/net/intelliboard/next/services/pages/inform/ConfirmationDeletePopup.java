package net.intelliboard.next.services.pages.inform;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class ConfirmationDeletePopup {
    private static SelenideElement confirmationPopup = $x("//div[contains (@class,'in-form-table-delete-popup')]");
    private static SelenideElement buttonSubmit = $x("//button[@aria-label='Yes']");

    public static ConfirmationDeletePopup init() {
        confirmationPopup.shouldBe(Condition.visible);
        return new ConfirmationDeletePopup();
    }

    public void submitDeletion() {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        buttonSubmit.click();
        confirmationPopup.shouldNotBe(Condition.visible);
    }
}
