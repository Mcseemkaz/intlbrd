package net.intelliboard.next.services.pages.export;

import com.codeborne.selenide.Condition;

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
    private void openActionMenu(String itemName, LocalDateTime itemCreatedDate) {
        $x("//tr[./td[contains (text(),'" +
                itemName + "')]  and ./td[contains (text(), '" +
                itemCreatedDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) +
                "')]][last()]//td[contains (@class, 'actions-cell')]").click();
    }

    public File downloadItem(String itemName, LocalDateTime itemCreatedDate) throws FileNotFoundException {
        openActionMenu(itemName, itemCreatedDate);
        return $x("//ul[contains (@class,'dropdown-menu')]//a[contains (text(),'Download')]")
                .download();
    }
}
