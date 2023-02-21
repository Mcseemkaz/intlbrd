package net.intelliboard.next.services.pages.incontact;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import net.intelliboard.next.services.ProjectFilesEnum;

import java.io.File;

import static com.codeborne.selenide.Selenide.$x;
import static net.intelliboard.next.AbstractTest.SLEEP_TIMEOUT_SHORT;

public class InContactFilterModalElement {

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
        Selenide.sleep(SLEEP_TIMEOUT_SHORT);
        return this;
    }

    private void openDropdown(String dropdownLabel) {
        Selenide.sleep(SLEEP_TIMEOUT_SHORT);
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
        Selenide.sleep(SLEEP_TIMEOUT_SHORT);
    }

    private void submitSelection(String dropdownLabel) {
        $x("//label[contains (text(), '" + dropdownLabel + "')]/following-sibling::div//div[contains (@class,'tree-action')]//button")
                .click();
    }

    public InContactMainPage closeFilterModal() {
        $x("//button[contains (@class,'closed_button')]")
                .click();
        return InContactMainPage.init();
    }

    public void uploadCSVFile(ProjectFilesEnum filePath) {
        //open CSV Modal
        $x("//div[@class='buttons-filter']//button[contains (text(),'Upload Contact C')]")
                .click();

        File file = new File(filePath.path);
        SelenideElement chooseFile = $x("//input[@type='file']");
        chooseFile.uploadFile(file);
    }
}
