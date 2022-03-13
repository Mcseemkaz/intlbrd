package net.intelliboard.next.services.pages.IBUsers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.IBNextURLs;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class IBUsersPage {

    //Page Elements
    @Getter
    public ElementsCollection passwordErrors = $$x("//span[contains(@class,'help-block-error')]");
    @Getter
    public SelenideElement emailError = $x("//div[contains(@class,'has-error')]//span[@class='help-block ']");

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
        deleteSelectedUserPromtModal(true);
        $x("//span[contains(text(),'\"+userFirstName+\"')]").shouldNotBe(Condition.visible);
        return this;
    }

    public IBUsersPage checkedUserByName(String firstUserName) {
        $x("//td[ ./span[contains(text(),'" + firstUserName + "')]]/preceding-sibling::td")
                .click();
        return this;
    }

    public IBUsersPage deleteSelectedUsersByActionDropdown() {
        $x("(//div[@class='card']//div[@class='intelli-dropdown dropdown'])[2]").click();
        $x("((//div[@class='card']//div[@class='intelli-dropdown dropdown'])[2]//a)[2]").click();
        deleteSelectedUserPromtModal(true);
        return this;
    }

    public IBUsersPage deleteSelectedUserPromtModal(boolean yesORno) {
        $x("//div[@class='modal-content']").shouldBe(Condition.visible);
        if (yesORno) {
            $x("//div[@class='modal-content']//button[contains(@class, 'error')]").shouldBe(Condition.visible)
                    .click();
        } else {
            $x("//div[@class='modal-content']//button[contains (@class,'default')]").shouldBe(Condition.visible)
                    .click();
        }
        $x("//div[@class='modal-content']").shouldBe(Condition.disappear);
        return this;
    }

}
