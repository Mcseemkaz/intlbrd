package net.intelliboard.next.services.pages.inform;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import net.intelliboard.next.services.pages.elements.spinners.PageSpinner;
import net.intelliboard.next.services.pages.elements.table.TableElementBody;
import net.intelliboard.next.services.pages.elements.table.TableElementHeader;

import static com.codeborne.selenide.Selenide.$x;

public class InFormRecordsShowPage {

    @Step("InFormRecordsShow Page init")
    public static InFormRecordsShowPage init() {
        PageSpinner.waitPreloader();
        PageSpinner.waitSpinner();
        $x("//form[ .//input[contains (@value, '/in-form')]]")
                .should(Condition.visible);
        return new InFormRecordsShowPage();
    }

    @Step("Is record exist")
    public boolean isRecordExist(String recordItem) {
        return TableElementBody.init().isRecordExist(recordItem);
    }

    @Step("Delete record in InForm Table")
    public void deleteRow(String itemName) {
        TableElementBody
                .init()
                .checkRow(itemName);
        TableElementHeader
                .init()
                .deleteSelectedRowsByAction();
    }

    @Step("Delete all records in InForm Table")
    public InFormRecordsShowPage deleteAllRecords() {
        TableElementHeader
                .init()
                .deleteAllRowsByAction();
        return this;
    }
}
