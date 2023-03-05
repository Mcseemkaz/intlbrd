package net.intelliboard.next.services.pages.elements.table;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$x;

public class TableElementBody {

    public static TableElementBody init() {
        $x("//div[contains (@class,'table-container')]")
                .shouldBe(Condition.visible);
        return new TableElementBody();
    }
}
