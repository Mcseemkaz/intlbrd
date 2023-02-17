package net.intelliboard.next.services.pages.inform;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import net.intelliboard.next.IBNextAbstractTest;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class InFormFormCreatePage extends IBNextAbstractTest {
    public static InFormFormCreatePage init() {
        $x("//main[contains (@class, 'in-form-forms-builder')]")
                .shouldBe(Condition.visible);
        return new InFormFormCreatePage();
    }

    public InFormFormCreatePage selectTable(String tableName) {
        $x("//div[@class='table-selector']//div[@class='tree-select']")
                .click();
        $x("//div[@class='table-selector']//div[@class='tree-select']//strong[contains (text(),'" + tableName + "')]")
                .click();
        $x("//div[@class='table-selector']//div[@class='tree-select']//span[@class='dropdown-trigger']//span[contains (text(),'" + tableName + "')]")
                .shouldBe(Condition.visible);
        return this;
    }

    public InFormFormCreatePage setInFormName(String formName) {
        $x("//input[@id='formName']").setValue(formName);
        return this;
    }

    public InFormFormCreatePage addColumn(String columnName) {
        Selenide.sleep(SLEEP_TIMEOUT_SHORT);

        SelenideElement column = $x("//li[contains (@class,'in-form-table-column-draggable-item')][contains (text(), '" + columnName + "')]");
        SelenideElement columnsWrapper = $x("//div[@class='draggable-wrapper'][./span[text()='Columns']]/div");
        executeJavaScript("arguments[0].setAttribute(arguments[1], arguments[2]);", column, "draggable", "true");
        executeJavaScript("arguments[0].setAttribute(arguments[1], arguments[2]);", column, "class", "in-form-table-column-draggable-item sortable-chosen sortable-ghost");

        Selenide
                .actions()
                .click(column)
                .clickAndHold(column)
                .moveToElement(columnsWrapper)
                .release()
                .perform();
        return this;
    }

    public void saveForm() {
        Selenide.sleep(SLEEP_TIMEOUT_SHORT);
        $x("//div[@class='actions']//button[@type='submit']")
                .click();
    }
}
