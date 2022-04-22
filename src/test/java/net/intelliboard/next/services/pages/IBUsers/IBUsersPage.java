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

    private SelenideElement paginationBlock = $x("//div[contains (@class,'pagination-wrapper')]//ul[@class='pagination']");
    private SelenideElement userActionDropdownDeleteOption = $x("//ul[contains (@class, 'dropdown-menu')]/li[4]//a");
    private SelenideElement firstUserRow = $x("(//div[contains (@class, 'sub-accounts')]//tbody//tr)[1]");


    public static IBUsersPage init() {
        IBNextAbstractTest ibNextAbstractTest = new IBNextAbstractTest();
        ibNextAbstractTest.waitForPageLoaded();
        $x("//div[@class='content-body']").shouldBe(Condition.visible);
        ibNextAbstractTest.checkPageURL(IBNextURLs.USERS_PAGE);
        return new IBUsersPage();
    }

    public IBUsersCreatePage openIBUserCreatePage() {
        $x("//button[@type=\'submit\']").click();
        $x("//li//a[contains (@href,'" + IBNextURLs.USERS_CREATE_PAGE + "')]").shouldBe(Condition.visible).click();
        return IBUsersCreatePage.init();
    }

    public boolean getUserByName(String nameIBUser) {
        return $x("//span[contains (text(),'" + nameIBUser + "')]").is(Condition.visible);
    }

    public IBUsersPage deleteUser(String userFirstName) {
        $x("//td[ ./span[contains(text(),'" + userFirstName + "')]]/following-sibling::td//button[contains(@class,'dropdown-toggle')]").click();
        userActionDropdownDeleteOption.click();
        deleteSelectedUserPromtModal(true);
        $x("//span[contains(text(),'\"+userFirstName+\"')]").shouldNotBe(Condition.visible);
        return this;
    }

    public IBUsersPage deleteUser() {
        $x("(//td/following-sibling::td//button[contains(@class,'dropdown-toggle')])[1]").click();
        userActionDropdownDeleteOption.click();
        deleteSelectedUserPromtModal(true);
        return this;
    }

    public IBUsersPage checkedUserByName(String firstUserName) {
        $x("//td[ ./span[contains(text(),'" + firstUserName + "')]]/preceding-sibling::td").click();
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
            $x("//div[@class='modal-content']//a[contains(@class, 'error')]").shouldBe(Condition.visible).click();
        } else {
            $x("//div[@class='modal-content']//button[contains (@class,'default')]").shouldBe(Condition.visible).click();
        }
        $x("//div[@class='modal-content']").shouldBe(Condition.disappear);
        return this;
    }

    //TODO MO - add Enum for attributes scaling
    public IBUsersPage changeScalingUsersPerPage(int usersPerPage) {
        $x("//div[contains (@class,'pagination-wrapper')]//div[contains(@class,'intelli-dropdown')]").click();
        $x("//div[contains (@class,'pagination-wrapper')]//div[contains(@class,'intelli-dropdown')]//ul//label/*[text()='" + usersPerPage + "']").click();
        return IBUsersPage.init();
    }

    //TODO MO - add Enum for pagination
    public IBUsersPage changePaginationPage(String nextOrPrev) {
        paginationBlock.shouldBe(Condition.visible);
        $x("//div[contains (@class,'pagination-wrapper')]//ul[@class='pagination']//a[@rel='" + nextOrPrev + "']").click();
        return IBUsersPage.init();
    }

    public boolean isPaginationPresented() {
        return paginationBlock.isDisplayed();
    }

    public boolean isFirstUserPresented() {
        return firstUserRow.isDisplayed();
    }
}
