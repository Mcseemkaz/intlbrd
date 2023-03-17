package net.intelliboard.next.services.pages.elements.table;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import net.intelliboard.next.services.pages.elements.spinners.PageSpinner;

import static com.codeborne.selenide.Selenide.$x;

public class TableElementHeader {

    @Step("Table Header init")
    public static TableElementHeader init() {
        $x("//div[contains (@class, 'table-panel')]").shouldBe(Condition.visible);
        return new TableElementHeader();
    }

    @Step("Delete by Action")
    public void deleteSelectedRowsByAction() {
        openActionMenu();
        $x("//a[contains (text(),'Delete Selected')]")
                .click();
        Selenide.confirm();
        PageSpinner.waitPreloader();
        PageSpinner.waitSpinner();
    }

    @Step("Delete All Rows")
    public void deleteAllRowsByAction() {
        openActionMenu();
        $x("//a[contains (text(),'Delete All')]")
                .click();
        Selenide.confirm();
        PageSpinner.waitPreloader();
        PageSpinner.waitSpinner();
    }


    private void openActionMenu() {
        $x("//div[contains (@class, 'table-panel')]//strong[contains (text(),'Action')]")
                .click();
    }
}
