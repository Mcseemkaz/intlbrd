package net.intelliboard.next.services.pages.inform;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import net.intelliboard.next.services.ProjectFilesEnum;
import net.intelliboard.next.services.pages.elements.spinners.PageSpinner;

import java.io.File;

import static com.codeborne.selenide.Selenide.$x;

public class ImportInformModal {
    public static ImportInformModal init() {
        $x("//form[not (@id='logout-form')]")
                .should(Condition.visible);
        return new ImportInformModal();
    }

    @Step("Select InForm Table")
    public ImportInformModal selectTable(String tableName) {
        $x("//select[@id='inFormTable']").selectOption(tableName);
        return this;
    }

    @Step("Fill in Table name")
    public ImportInformModal fillInTableName(String tableName) {
        $x("//input[@id='table_title']")
                .setValue(tableName);
        return this;
    }

    @Step("Upload file for importing")
    public ImportInformModal uploadFile(ProjectFilesEnum filePath) {
        File file = new File(filePath.path);
        $x("//input[@id='importfile']").uploadFile(file);
        return this;
    }

    @Step("Proceed to next step")
    public InFormImportConfiguration proceedNext() {
        $x("//button[@type='submit']")
                .click();

        PageSpinner.waitSpinner();
        PageSpinner.waitPreloader();
        return InFormImportConfiguration.init();
    }
}
