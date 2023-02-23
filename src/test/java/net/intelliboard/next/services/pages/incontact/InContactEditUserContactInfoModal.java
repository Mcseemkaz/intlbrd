package net.intelliboard.next.services.pages.incontact;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import net.intelliboard.next.services.pages.elements.spinners.PageSpinner;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class InContactEditUserContactInfoModal {

    private final SelenideElement buttonSubmit = $x("//div[@class='user-data-list']//button[@class='primary']");
    private final SelenideElement inputKey = $x("//input[@placeholder='Field Name']");
    private final SelenideElement inputValue = $x("//input[@placeholder='Value']");
    private final SelenideElement addRowButton = $x("//div[@class='user-data-list-form-actions']/ion-icon[@name='add']");

    public static InContactEditUserContactInfoModal init() {
        $x("//div[@class='user-data-list']").should(Condition.visible);
        return new InContactEditUserContactInfoModal();
    }

    public InContactMainPage addData(String key, String value) {
        inputKey.setValue(key);
        inputValue.setValue(value);
        addRowButton.click();
        buttonSubmit.click();
        PageSpinner.waitSpinner();
        return InContactMainPage.init();
    }

    public InContactMainPage deleteAllData() {

        int size = $$x("//ion-icon[@name='trash']").size();

        for (int i = 0; i < size; i++) {
            Selenide.sleep(1000);
            $x("//ion-icon[@name='trash']").click();
        }

        buttonSubmit.click();
        PageSpinner.waitSpinner();
        return InContactMainPage.init();
    }
}
