package net.intelliboard.next.services.pages.elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class DropdownElement {

    static String dropdownLabel;

    DropdownElement(String dropdownLabel) {
        DropdownElement.dropdownLabel = dropdownLabel;
    }

    public DropdownElement init(String dropdownLabel) {
        $x("//div[@class='tree-select'][./preceding-sibling::label[contains (text(), '" + dropdownLabel + "')]]")
                .shouldBe(Condition.visible);
        return new DropdownElement(dropdownLabel);
    }

    public DropdownElement selectOption(String value) {
        openDropdown();

        return this;
    }

    private void openDropdown() {
        SelenideElement chevronDownElement = $x("//div[@class='tree-select'][./preceding-sibling::label[contains (text(), '" + dropdownLabel + "')]]//ion-icon[@name='chevron-down-outline']");
        if (chevronDownElement.is(Condition.visible)) {
            chevronDownElement.click();
            $x("//div[@class='tree-select'][./preceding-sibling::label[contains (text(), '" + dropdownLabel + "')]]//div[@class='tree-drop']")
                    .shouldBe(Condition.visible);
        }
    }


}
