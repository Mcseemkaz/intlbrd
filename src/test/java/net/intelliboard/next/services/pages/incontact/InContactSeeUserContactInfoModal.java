package net.intelliboard.next.services.pages.incontact;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class InContactSeeUserContactInfoModal {

    static SelenideElement mainForm = $x("//div[contains (@class,'user-data-list')]");

    public static InContactSeeUserContactInfoModal init() {
        mainForm.should(Condition.visible);
        return new InContactSeeUserContactInfoModal();
    }

    public boolean userDataExist(String key, String value) {
        return $x("//div[@class='row'][.//label[@title='" + key + "']][.//label[@title='" + value + "']]")
                .exists();
    }

    public InContactMainPage closeModal() {
        $x("//div[contains (@class,'user-data-list')]//ion-icon[contains (@class,'close')]")
                .click();
        mainForm.should(Condition.hidden);
        return InContactMainPage.init();
    }
}
