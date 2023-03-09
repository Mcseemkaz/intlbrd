package net.intelliboard.next.services.pages.inform;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import net.intelliboard.next.services.pages.elements.spinners.PageSpinner;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class ImportInFormListPage {
    public static ImportInFormListPage init() {
        PageSpinner.waitPreloader();
        PageSpinner.waitSpinner();
        $x("//h1[contains (text(),'Import')]")
                .should(Condition.visible, Duration.ofSeconds(60));
        return new ImportInFormListPage();
    }

    @Step("Import File")
    public ImportInformModal importFile() {
        $x("//a[contains (@href,'/import/create')]").click();
        return ImportInformModal.init();
    }
}
