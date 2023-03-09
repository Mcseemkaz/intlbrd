package net.intelliboard.next.services.pages.inform;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import net.intelliboard.next.services.pages.elements.spinners.PageSpinner;

import static com.codeborne.selenide.Selenide.$x;

public class InFormImportConfiguration {

    private final static SelenideElement form = $x("//form[@class='in-form-import-table-configuration']");

    public static InFormImportConfiguration init() {
        PageSpinner.waitSpinner();
        form.should(Condition.visible);
        return new InFormImportConfiguration();
    }

    @Step("Save Imported InForm")
    public InFormImportProcessingPage saveInform() {
        $x("//form[@class='in-form-import-table-configuration']//button[@type='submit' and contains (@class,'primary')]").
                click();
        form.should(Condition.not(Condition.visible));
        PageSpinner.waitPreloader();
        return InFormImportProcessingPage.init();
    }
}
