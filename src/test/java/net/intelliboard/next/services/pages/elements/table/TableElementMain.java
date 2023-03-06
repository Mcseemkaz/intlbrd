package net.intelliboard.next.services.pages.elements.table;

import com.codeborne.selenide.Condition;
import net.intelliboard.next.services.pages.elements.spinners.PageSpinner;

import static com.codeborne.selenide.Selenide.$x;

public class TableElementMain {

    public static TableElementMain init() {
        PageSpinner.waitPreloader();
        PageSpinner.waitSpinner();
        $x("//div[contains (@class, 'table-wrapper')]")
                .shouldBe(Condition.visible);
        return new TableElementMain();
    }

}
