package net.intelliboard.next.services.pages.inform;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class AddNewInFormTablePage {

    private SelenideElement saveColumnButton = $x("//div[@class='add-column-container']//button[@aria-label='Save Column']");
    private SelenideElement saveInFormTableButton = $x("//div[@class='in-form-table-builder-container']/button[@type='submit']");


    public static AddNewInFormTablePage init() {
        $x("//div[contains(@class, 'in-form-table')]")
                .shouldBe(Condition.visible, Duration.ofSeconds(30));
        return new AddNewInFormTablePage();
    }

    public AddNewInFormTablePage fillInTableTitle(String tableTitleName) {
        $x("//input[@id='tableTitle']")
                .setValue(tableTitleName);
        return this;
    }

    public AddNewInFormTablePage select(InFormEntriesManagingModes mode) {
        $x("//div[contains(@class, 'table-general')]//select[contains (@aria-label, 'Entries Managing Mode')]")
                .selectOptionContainingText(mode.value);
        return this;
    }

    public AddNewInFormTablePage addColumn(InFormColumnType type, String value) throws InterruptedException {
        $x("//select[contains (@aria-label, 'Column Editor Type')]")
                .selectOptionContainingText(type.value);
        $x("//input[contains (@class, 'editor')]").setValue(value);
        saveColumnButton.click();
        $x("//div[contains (@class, 'in-form-table-column-item') and .//button[contains (@aria-label,'" + value + "')]]")
                .shouldBe(Condition.visible);
        Thread.sleep(2000);
        return this;
    }

    public InFormPage saveInformTable() {
        saveInFormTableButton.click();
        return InFormPage.init();
    }
}
