package net.intelliboard.next.services.pages.auditlogs;

import com.codeborne.selenide.Condition;
import net.intelliboard.next.services.pages.elements.DatePicker;
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
        String value = $x("//tbody//tr[" + numberRow + "]//td[" + columnEnum.numberColumn + "]").getText();

        return value;
    }

    public UserAuditLogsPage searchByUser(String userName) {
        $x("//div[@name='user_id']//div[contains (@class, 'intelli-dropdown')]//button")
                .click();
        $x("//label//strong[text()='" + userName + "']")
                .click();
        $x("//div[@class='content-body']")
                .click();
        return this;
    }

    public UserAuditLogsPage searchByDate(LocalDateTime date) {

        DatePicker
                .init()
                .setDayOfMonth(date);
        return this;
    }
}

