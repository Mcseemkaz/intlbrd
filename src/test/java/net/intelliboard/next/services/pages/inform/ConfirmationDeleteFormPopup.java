package net.intelliboard.next.services.pages.inform;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

class ConfirmationDeleteFormPopup {
    private static SelenideElement confirmationPopup = $x("//div[contains (@class,'in-form-table-delete-popup')]//div[@class='modal-header' and //*[contains (text(),'Forms')]]");
    private static SelenideElement buttonSubmit = $x("//button[contains (@class, 'success')]");

    public static ConfirmationDeleteFormPopup init() {
        confirmationPopup.shouldBe(Condition.visible);
        return new ConfirmationDeleteFormPopup();
    }

    public boolean isConfirmationDeleteFormPopupExist() {
        return confirmationPopup.isDisplayed();
    }

    public void submitFormDeletion() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        buttonSubmit.click();
        confirmationPopup.shouldNotBe(Condition.visible);
    }
}
