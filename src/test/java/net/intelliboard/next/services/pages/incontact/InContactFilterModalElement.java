package net.intelliboard.next.services.pages.incontact;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selenide.$x;

public class InContactFilterModalElement {

    int sleepTimeLocal = 1000;

    public static InContactFilterModalElement init() {
        $x("//div[contains (@class, 'top-filters__popup')]")
                .shouldBe(Condition.visible);
        return new InContactFilterModalElement();
    }

    public InContactFilterModalElement setCourse(String courseName) {
        openDropdown("Courses");
        searchValue("Courses", courseName);
        selectValue("Courses", courseName);
        submitSelection("Courses");
        Selenide.sleep(sleepTimeLocal);
        return this;
    }

    private void openDropdown(String dropdownLabel) {
        Selenide.sleep(sleepTimeLocal);
        $x("//label[contains (text(), '" + dropdownLabel + "')]/following-sibling::div//div[@class='tree-select']")
                .click();
    }

    private void searchValue(String dropdownLabel, String searchValue) {
        $x("//label[contains (text(), '" + dropdownLabel + "')]/following-sibling::div//div[@class='tree-search']/input")
                .setValue(searchValue);
    }

    private void selectValue(String dropdownLabel, String value) {
        $x("//label[contains (text(), '" +
                dropdownLabel + "')]/following-sibling::div//div[contains (@class,'tree-select-option')]//mark[contains (text(),'" +
                value + "')]").click();
        Selenide.sleep(sleepTimeLocal);
    }

    private void submitSelection(String dropdownLabel){
        $x("//label[contains (text(), '" + dropdownLabel + "')]/following-sibling::div//div[contains (@class,'tree-action')]//button")
                .click();
    }

    public InContactMainPage closeFilterModal(){
        $x("//button[contains (@class,'closed_button')]")
                .click();
        return InContactMainPage.init();
    }
}
