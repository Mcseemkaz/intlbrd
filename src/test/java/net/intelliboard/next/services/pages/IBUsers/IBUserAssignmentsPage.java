package net.intelliboard.next.services.pages.IBUsers;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import net.intelliboard.next.services.pages.elements.DropdownElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class IBUserAssignmentsPage {

    public static IBUserAssignmentsPage init() {
        $x("//form[contains (@action ,'/assign')]")
                .shouldBe(Condition.visible, Duration.ofSeconds(30));
        return new IBUserAssignmentsPage();
    }

    public DropdownElement dropdownConnectionSelect =
            DropdownElement.init("Connection");

    private void loadData(String assignmentDataBlockName) {
        $x("//div[@class='card open' and ./div[contains (@class, 'card-header')]/div[contains (text(),'" + assignmentDataBlockName + "')]]//button[contains (@class,'select-load')]")
                .click();
        ElementsCollection options = $$x("(//div[@class='card open' and ./div[contains (@class, 'card-header')]/div[contains (text(),'" + assignmentDataBlockName + "')]]//select)[2]//option");
        options.should(CollectionCondition.sizeGreaterThan(0), Duration.ofSeconds(30));
    }

    public IBUserAssignmentsPage addFirstElementInBlock(String assignmentDataBlockName) {

        loadData(assignmentDataBlockName);

        //Select the First Element
        String elementValue;
        ElementsCollection options = $$x("(//div[@class='card open' and ./div[contains (@class, 'card-header')]/div[contains (text(),'" + assignmentDataBlockName + "')]]//select)[2]//option");
        options.first().click();
        elementValue = options.first().getText();

        //Add element to Block
        $x("//div[@class='card open' and ./div[contains (@class, 'card-header')]/div[contains (text(),'" + assignmentDataBlockName + "')]]//button[@aria-label='Add Selected']")
                .click();

        //Check that element has been added
        ElementsCollection optionsAdded = $$x("(//div[@class='card open' and ./div[contains (@class, 'card-header')]/div[contains (text(),'" + assignmentDataBlockName + "')]]//select)[1]//option");
        optionsAdded.should(CollectionCondition.sizeGreaterThan(0), Duration.ofSeconds(30));
        optionsAdded.should(CollectionCondition.itemWithText(elementValue));
        return this;
    }
}
