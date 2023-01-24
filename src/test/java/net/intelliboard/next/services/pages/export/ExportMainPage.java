package net.intelliboard.next.services.pages.export;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import net.intelliboard.next.services.pages.report.ReportExportFormat;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$x;

public class ExportMainPage {

    public static ExportMainPage init() {
        $x("//h1[contains (text(),'Export History')]").shouldBe(Condition.visible,
                Duration.ofSeconds(30));
        return new ExportMainPage();
    }

    //TODO [MO] Need to refactor to Table View element
    private void openActionMenu(String itemName, LocalDateTime itemCreatedDate, ReportExportFormat type) {
        String value = type.value.toLowerCase();

        $x("//tr[./td[contains (text(),'" +
                itemName + "')]  and ./td[contains (text(), '" +
                itemCreatedDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) +
                "')] and ./td[contains (text(),'" +
                value + "')]][last()]//td[contains (@class, 'actions-cell')]")
                .click();
    }

    public File downloadItem(String itemName, LocalDateTime itemCreatedDate, ReportExportFormat type) throws FileNotFoundException {
        openActionMenu(itemName, itemCreatedDate, type);
        return $x("//ul[contains (@class,'dropdown-menu')]//a[contains (text(),'Download')]")
                .download();
    }

    public ExportMainPage deleteItem(String itemName, LocalDateTime itemCreatedDate, ReportExportFormat type) throws FileNotFoundException {
        openActionMenu(itemName, itemCreatedDate, type);
        $x("//ul[contains (@class,'dropdown-menu')]//a[contains (text(),'Delete')]")
                .click();
        Selenide.confirm();
        return this;
    }
}
