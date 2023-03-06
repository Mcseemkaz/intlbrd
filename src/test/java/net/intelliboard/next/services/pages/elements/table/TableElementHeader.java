package net.intelliboard.next.services.pages.elements.table;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$x;

public class TableElementHeader {

    public static TableElementHeader init() {
        $x("//div[contains (@class, 'table-panel']").shouldBe(Condition.visible);
        return new TableElementHeader();
    }
}
