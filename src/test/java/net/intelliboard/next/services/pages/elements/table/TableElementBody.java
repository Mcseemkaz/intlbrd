package net.intelliboard.next.services.pages.elements.table;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class TableElementBody {

    @Step("Table init")
    public static TableElementBody init() {
        $x("//div[contains (@class,'table-container')]")
                .shouldBe(Condition.visible);
        return new TableElementBody();
    }

    @Step("Is record exist")
    public boolean isRecordExist(String recordItem) {
        return $x("//tr[ ./td/span[contains (text(), '" + recordItem + "')]]")
                .exists();
    }

    @Step("Check Row in Table")
    public void checkRow(String recordItem) {
        $x("//tr[ ./td/span[contains (text(), '" + recordItem + "')]]/td/div[contains (@class,'checkbox')]")
                .click();
    }
}
