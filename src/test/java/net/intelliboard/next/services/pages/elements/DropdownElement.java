package net.intelliboard.next.services.pages.elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class DropdownElement {

    static String dropdownLabel;
    static int numberOfElementOnPage;

    DropdownElement(String dropdownLabel, int numberOfElementOnPage) {

        DropdownElement.dropdownLabel = dropdownLabel;
        DropdownElement.numberOfElementOnPage = numberOfElementOnPage;

    }

    public static DropdownElement init(String dropdownLabel, int numberOfElementOnPage) {
        $x("(//div[@class='tree-select'][./preceding-sibling::label[contains (text(), '" + dropdownLabel + "')]])[" + numberOfElementOnPage + "]")
                .shouldBe(Condition.visible);
        return new DropdownElement(dropdownLabel, numberOfElementOnPage);
    }

    public DropdownElement selectOption(String value) {

        if (!$x("(//div[@class='tree-select'][./preceding-sibling::label[contains (text(), '" +
                dropdownLabel + "')]])[" +
                numberOfElementOnPage + "]//span[text()='" + value + "']")
                .exists()) {
            openDropdown();
            $x("(//div[@class='tree-select'][./preceding-sibling::label[contains (text(), '" +
                    dropdownLabel + "')]])[" +
                    numberOfElementOnPage + "]//label[./strong[text()='" + value + "']]")
                    .click();
        }

        // confirm selection
        $x("(//div[@class='tree-select'][./preceding-sibling::label[contains (text(), '" +
                dropdownLabel + "')]])[" +
                numberOfElementOnPage + "]")
                .click();
        return this;
    }

    private void openDropdown() {
        SelenideElement chevronDownElement = $x("(//div[@class='tree-select'][./preceding-sibling::label[contains (text(), '" +
                dropdownLabel + "')]])[" +
                numberOfElementOnPage + "]//ion-icon[@name='chevron-down-outline']");
        if (chevronDownElement.is(Condition.visible)) {
            chevronDownElement.click();
            $x("(//div[@class='tree-select'][./preceding-sibling::label[contains (text(), '" +
                    dropdownLabel + "')]])[" +
                    numberOfElementOnPage + "]//div[@class='tree-drop']")
                    .shouldBe(Condition.visible);
        }
    }
}