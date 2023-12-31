package net.intelliboard.next.services.pages.header;

import com.codeborne.selenide.Condition;
import net.intelliboard.next.services.IBNextURLs;
import net.intelliboard.next.services.pages.IBUsers.IBUserPage;
import net.intelliboard.next.services.pages.IBUsers.IBUsersPage;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class HeaderDropDownMenu {

    public static HeaderDropDownMenu init() {
        $x("//div[contains (@class, 'dropdown-menu') and contains(@class, 'active')]")
                .shouldBe(Condition.visible);
        return new HeaderDropDownMenu();
    }

    public IBUsersPage openMyIBUsersPage() {
        $x("//a[contains (@href,'" + IBNextURLs.USERS_PAGE + "')]")
                .shouldBe(Condition.visible).click();
        return IBUsersPage.init();
    }

    public void logOut(){
        $x("//a[contains (@href,'/logout')]")
                .click();
        $x("//h1[contains (text(), 'Upcoming Events')]")
                .shouldBe(Condition.visible, Duration.ofSeconds(90));
    }

    public IBUserPage openMyAccountProfilePage() {
        $x("//a[@class='manage-account']")
                .click();
        return IBUserPage.init();
    }
}
