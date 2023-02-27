package net.intelliboard.next.services.pages.incontact;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import net.intelliboard.next.services.pages.elements.DropdownElement;

import static com.codeborne.selenide.Selenide.$x;
import static net.intelliboard.next.AbstractTest.SLEEP_TIMEOUT_SHORT;

public class InContactMultiSelectModal extends InContactMenuModalElement {

    private static final SelenideElement mainForm = $x("//div[contains (@class,'multi-select-calendar')]");

    public static InContactMultiSelectModal init() {
        mainForm.should(Condition.visible);
        return new InContactMultiSelectModal();
    }

    public InContactMultiSelectModal setStudent(String studentName) {
        openDropdown("Students");
        searchValue("Students", studentName);
        selectValue("Students", studentName);
        submitSelection("Students");
        Selenide.sleep(SLEEP_TIMEOUT_SHORT);
        return this;

    }

    public InContactMultiSelectModal setCourse(String courseName) {
        openDropdown("Courses");
        searchValue("Courses", courseName);
        selectValue("Courses", courseName);
        Selenide.sleep(SLEEP_TIMEOUT_SHORT);
        return this;
    }

    public InContactMultiSelectModal setOption(String dropdownName, String optionValue) {
        DropdownElement
                .init(dropdownName, 1)
                .selectOption(optionValue);
        return this;
    }

    public void submitForm() {
        $x("//button[contains (@class, 'sbmt')]")
                .click();
        mainForm.shouldBe(Condition.hidden);
    }

    private void selectValue(String dropdownLabel, String value) {
        $x("//label[contains (text(), '" +
                dropdownLabel + "')]/following-sibling::div//div[contains (@class,'tree-select-option')]//mark[contains (text(),'" +
                value + "')]").click();
        Selenide.sleep(SLEEP_TIMEOUT_SHORT);
    }

    private void submitSelection(String dropdownLabel) {
        $x("//label[contains (text(), '" + dropdownLabel + "')]/following-sibling::div//div[contains (@class,'tree-action')]//button")
                .click();
    }

    private void openDropdown(String dropdownLabel) {
        Selenide.sleep(SLEEP_TIMEOUT_SHORT);
        $x("//label[contains (text(), '" + dropdownLabel + "')]/following-sibling::div//div[contains (@class,'intelli-dropdown')]")
                .click();
    }

    private void searchValue(String dropdownLabel, String searchValue) {
        $x("//label[contains (text(), '" + dropdownLabel + "')]/following-sibling::div//div[@class='tree-search']/input")
                .setValue(searchValue);
    }
}
