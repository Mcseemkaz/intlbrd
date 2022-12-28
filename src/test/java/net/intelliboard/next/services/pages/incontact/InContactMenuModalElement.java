package net.intelliboard.next.services.pages.incontact;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import net.intelliboard.next.services.pages.elements.DatePickerElement;
import net.intelliboard.next.services.pages.elements.DropdownElement;

import java.time.LocalDateTime;

import static com.codeborne.selenide.Selenide.$x;

public class InContactMenuModalElement {

    private static final SelenideElement mainForm = $x("//div[@class='log-form-popup']");

    public static InContactMenuModalElement init() {
        mainForm.shouldBe(Condition.visible);
        return new InContactMenuModalElement();
    }

    public InContactMenuModalElement setDate(LocalDateTime date) {
        $x("//input[contains (@class,'date-picker-input') and not (@name)]")
                .click();
        DatePickerElement
                .init()
                .setDayOfMonth(date);
        return this;
    }

    public InContactMenuModalElement setOption(String dropdownName, String optionValue) {
        DropdownElement
                .init(dropdownName, 2)
                .selectOption(optionValue);
        return this;
    }

    public InContactMenuModalElement setTextMessage(String textMessage) {
        $x("//div[@class='log-form-popup']//textarea")
                .sendKeys(textMessage);
        return this;
    }

    public void submitForm() {
        $x("//form//button[@type='submit' and not (@class)]").click();
        mainForm.shouldBe(Condition.hidden);
    }
}
