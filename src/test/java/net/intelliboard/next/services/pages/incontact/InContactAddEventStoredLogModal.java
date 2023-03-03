package net.intelliboard.next.services.pages.incontact;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import net.intelliboard.next.services.pages.elements.DropdownElement;

import static com.codeborne.selenide.Selenide.$x;
import static net.intelliboard.next.AbstractTest.SLEEP_TIMEOUT_SHORT;

public class InContactAddEventStoredLogModal extends InContactMenuModalElement {

    static SelenideElement mainForm =  $x("//div[contains (@class, 'bucket-container')]");
    public static InContactAddEventStoredLogModal init() {
        mainForm.should(Condition.visible);
        return new InContactAddEventStoredLogModal();
    }

    public InContactAddEventStoredLogModal setSisIDs(String id) {
        $x("//input[@name='users_sis_ids[]']")
                .setValue(id);
        return this;
    }

    public InContactAddEventStoredLogModal setCourse(String courseName) {
        openDropdown("Courses");
        searchValue("Courses", courseName);
        selectValue("Courses", courseName);
        Selenide.sleep(SLEEP_TIMEOUT_SHORT);
        return this;
    }

    public InContactAddEventStoredLogModal setOption(String dropdownName, String optionValue) {
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
                dropdownLabel + "')]/following-sibling::div[@name='course_id']//label[.//mark[contains (text(),'" + value + "')] and ./input]")
                .click();
        Selenide.sleep(SLEEP_TIMEOUT_SHORT);
    }

    private void openDropdown(String dropdownLabel) {
        Selenide.sleep(SLEEP_TIMEOUT_SHORT);
        $x("//label[contains (text(), '" + dropdownLabel + "')]/following-sibling::div//div[contains (@class,'intelli-dropdown')]")
                .click();
    }

    private void searchValue(String dropdownLabel, String searchValue) {
        $x("//label[contains (text(), '" + dropdownLabel + "')]/following-sibling::div//div[@class='tree-search']/input")
                .setValue(searchValue);
        Selenide.sleep(SLEEP_TIMEOUT_SHORT);
    }
}
