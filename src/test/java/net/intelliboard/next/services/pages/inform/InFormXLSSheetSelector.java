package net.intelliboard.next.services.pages.inform;

import com.codeborne.selenide.Condition;
import net.intelliboard.next.services.pages.elements.spinners.PageSpinner;

import static com.codeborne.selenide.Selenide.$x;

public class InFormXLSSheetSelector {
    public static InFormXLSSheetSelector init() {
        PageSpinner.waitSpinner();
        $x("//select[@id='sheetName']")
                .should(Condition.visible);
        return new InFormXLSSheetSelector();
    }

    public InFormImportConfiguration saveSheetConfiguration() {
        $x("//button[@type='submit' and contains (@class,'primary')]")
                .click();
        return InFormImportConfiguration.init();
    }
}
