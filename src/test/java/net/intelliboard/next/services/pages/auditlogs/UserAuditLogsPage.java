package net.intelliboard.next.services.pages.auditlogs;

import com.codeborne.selenide.Condition;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.pages.elements.DatePickerElement;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDateTime;

import static com.codeborne.selenide.Selenide.$x;

public class UserAuditLogsPage {

    public static UserAuditLogsPage init() {
        $x("//div[contains (@class, 'audit-table')]")
                .should(Condition.visible, Duration.ofSeconds(90));
        return new UserAuditLogsPage();
    }

    //TODO MO - Refactoring - extract to Table Element
    public UserAuditLogsPage searchByField(String searchValue) {
        $x("//input[@placeholder='Search' and contains (@class,'search-input')]")
                .setValue(searchValue)
                .sendKeys(Keys.ENTER);
        return this;
    }

    //TODO MO - Refactoring - extract to Table Element
    public String getValueCellByRowNumber(UserProfileAuditTableColumnEnum columnEnum, int numberRow) {
        return $x("//tbody//tr[" + numberRow + "]//td[" + columnEnum.numberColumn + "]")
                .getText();
    }

    public UserAuditLogsPage searchByUser(String userName) {
        $x("//div[@name='user_id']//div[contains (@class, 'intelli-dropdown')]//button")
                .click();
        $x("//label//strong[contains (text(),'" + userName + "')]")
                .click();
        $x("//div[@class='content-body']")
                .click();
        return this;
    }

    public UserAuditLogsPage searchByDate(LocalDateTime dateFrom, LocalDateTime dateTo) {
        $x("//input[@placeholder='Date Filter' and contains (@class, 'form-control')]")
                .click();
        DatePickerElement
                .init()
                .setDayOfMonth(dateFrom, dateTo);
        return this;
    }

    public boolean isTableEmpty() {
        return $x("//td[@class='table-empty'][contains (text(),'Table Empty')]")
                .exists();
    }

    public UserAuditLogsPage sortByColumn(UserProfileAuditTableColumnEnum columnEnum) {
        $x("//th/a[text()='" + columnEnum.value + "']")
                .should(Condition.visible, Duration.ofSeconds(120))
                .click();
        IBNextAbstractTest ibNextAbstractTest = new IBNextAbstractTest();
        ibNextAbstractTest.waitForPageLoaded();
        return UserAuditLogsPage.init();
    }
}

