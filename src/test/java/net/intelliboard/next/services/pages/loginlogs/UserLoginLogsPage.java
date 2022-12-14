package net.intelliboard.next.services.pages.loginlogs;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class UserLoginLogsPage {

    public static UserLoginLogsPage init() {
        $x("//div[contains (@class, 'audit-table')]")
                .should(Condition.visible, Duration.ofSeconds(90));
        return new UserLoginLogsPage();
    }

    public boolean isUserLoggedByDate(String userFirstName, LocalDateTime date) {
        return $x("//td[contains (text(),'" + date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "')][./following-sibling::td[contains (text(),'" + userFirstName + "')]  or ./preceding-sibling::td[contains (text(),'" + userFirstName + "')]]")
                .exists();
    }

    private int getIndexTableColumn(String tableColumnName) {
        ElementsCollection elements = $$x("//th");
        int i = 1;
        for (SelenideElement el : elements) {
            if (!el.getText().contains(tableColumnName)) {
                i++;
            }
        }
        return i;
    }
}
