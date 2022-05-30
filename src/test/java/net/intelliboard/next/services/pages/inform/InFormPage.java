package net.intelliboard.next.services.pages.inform;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import net.intelliboard.next.IBNextAbstractTest;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class InFormPage {

    private SelenideElement getTableElement(String tableName) {
        return $x("//tr[ ./td[text()='" + tableName + "']]");
    }

    public static InFormPage init() {
        $x("//div[@class='left']").shouldBe(Condition.visible, Duration.ofSeconds(30));
        $x("//div[@class='table-container']").shouldBe(Condition.visible, Duration.ofSeconds(30));
        return new InFormPage();
    }

    public AddNewInFormTablePage openAddTablePage() {
        $x("//a[contains (@href,'/in-form/create')]").click();
        return AddNewInFormTablePage.init();
    }

    public InFormPage searchInfoTable(String tableName) {
        $x("//div[@class='table-panel']//input[contains (@class, 'search-input')]")
                .setValue(tableName)
                .sendKeys(Keys.ENTER);
        IBNextAbstractTest ibNextAbstractTest = new IBNextAbstractTest();
        ibNextAbstractTest.waitForPageLoaded();
        return InFormPage.init();
    }

    private InFormPage openActionMenu(String tableName) {
        $x("//tr[ ./td[text()='" + tableName + "']]//td[contains (@class, 'actions-cell')]")
                .click();
//                $x("//td[contains (@class, 'actions-cell')]").click();
        return this;
    }

    public InFormPage deleteTable(String tableName) {
        openActionMenu(tableName);
        $x("//ul[contains (@class, 'dropdown-menu')]//a[contains (text(),'Delete')]")
                .click();
        confirmDelete();
        return this;
    }

    private InFormPage confirmDelete() {
        SelenideElement confirmationPopup = $x("//div[contains (@class,'in-form-table-delete-popup')]");
        confirmationPopup.shouldBe(Condition.visible);
        $x("//button[contains (@class, 'success')]").click();
        confirmationPopup.shouldNotBe(Condition.visible);
        return this;
    }

    public boolean isTableExist(String tableName) {
        return getTableElement(tableName).exists();
    }


}
