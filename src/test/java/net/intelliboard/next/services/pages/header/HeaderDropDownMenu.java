package net.intelliboard.next.services.pages.header;

import com.codeborne.selenide.Condition;
import net.intelliboard.next.services.IBNextURLs;
import net.intelliboard.next.services.pages.IBUsers.IBUsersPage;
import net.intelliboard.next.services.pages.login.LoginPage;

import static com.codeborne.selenide.Selenide.$x;

public class HeaderDropDownMenu {

    public static HeaderDropDownMenu init() {
        $x("//div[contains (@class, 'dropdown-menu') and contains(@class, 'active')]")
                .shouldBe(Condition.visible);
        return new HeaderDropDownMenu();
    }

    public IBUsersPage openMyIBUsersPage() {
        $x("//li//a[contains (@href,'" + IBNextURLs.USERS_PAGE + "')]")
                .shouldBe(Condition.visible).click();
        return IBUsersPage.init();
    }

    public LoginPage logOut(){
        $x("//a[contains (@href,'/logout')]")
                .click();
        return LoginPage.init();
    }
}
