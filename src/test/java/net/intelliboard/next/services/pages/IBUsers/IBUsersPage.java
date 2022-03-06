package net.intelliboard.next.services.pages.IBUsers;

import com.codeborne.selenide.Condition;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.IBNextURLs;

import static com.codeborne.selenide.Selenide.$x;

public class IBUsersPage {

    public static IBUsersPage init() {
        IBNextAbstractTest ibNextAbstractTest = new IBNextAbstractTest();
        ibNextAbstractTest.waitForPageLoaded();
        $x("//div[@class='content-body']").shouldBe(Condition.visible);
        ibNextAbstractTest.checkPageURL(IBNextURLs.USERS_PAGE);
        return new IBUsersPage();
    }

    public IBUsersCreatePage openIBUserCreatePage() {
        $x("//button[@type=\'submit\']").click();
        $x("//li//a[contains (@href,'" + IBNextURLs.USERS_CREATE_PAGE + "')]")
                .shouldBe(Condition.visible).click();
        return IBUsersCreatePage.init();
    }

    public boolean getUserByName(String nameIBUser) {
        return $x("//span[contains (text(),'" + nameIBUser + "')]").is(Condition.visible);
    }

    public IBUsersPage deleteUser(String userFirstName) {
        $x("//td[ ./span[contains(text(),'" + userFirstName + "')]]/following-sibling::td//button[contains(@class,'dropdown-toggle')]")
                .click();
        $x("//ul[contains (@class, 'dropdown-menu')]/li[4]//a")
                .click();
        $x("//div[@class='modal-content']//a[contains(@class, 'app-button')]").shouldBe(Condition.visible)
                .click();
        $x("//span[contains(text(),'\"+userFirstName+\"')]").shouldNotBe(Condition.visible);
        return this;
    }
}
