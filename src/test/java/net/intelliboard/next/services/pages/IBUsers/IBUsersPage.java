package net.intelliboard.next.services.pages.IBUsers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.IBNextURLs;
import net.intelliboard.next.services.pages.elements.spinners.PageSpinner;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class IBUsersPage {

    //Page Elements
    @Getter
    public ElementsCollection passwordErrors = $$x("//span[contains(@class,'help-block-error')]");
    @Getter
    public SelenideElement emailError = $x("//div[contains(@class,'has-error')]//span[@class='help-block ']");

    private final SelenideElement userActionDropdownMenu = $x("(//div[@class='card']//div[@class='intelli-dropdown dropdown'])[2]");
    private final SelenideElement userActionLogInOption = $x("//ul[contains (@class, 'dropdown-menu')]//a[contains (text(), 'Log In As User')]");
    private final SelenideElement userActionEditOption = $x("//ul[contains (@class, 'dropdown-menu')]//a[contains (text(), 'Edit')]");
    private final SelenideElement userActionAssignmentsOption = $x("//ul[contains (@class, 'dropdown-menu')]//a[contains (text(), 'Assignments')]");
    private final SelenideElement userActionDropdownDeleteOption = $x("//ul[contains (@class, 'dropdown-menu')]//a[contains (text(), 'Delete')]");
    private final SelenideElement firstUserRow = $x("(//div[contains (@class, 'sub-accounts')]//tbody//tr)[1]");
    private final SelenideElement paginationBlock = $x("//div[contains (@class,'pagination-wrapper')]//ul[@class='pagination']");
    private final SelenideElement addIBUserButton = $x("//button[@type='submit']");

    public static IBUsersPage init() {
        PageSpinner.waitPreloader();
        PageSpinner.waitSpinner();
        IBNextAbstractTest ibNextAbstractTest = new IBNextAbstractTest();
        ibNextAbstractTest.waitForPageLoaded();
        $x("//div[@class='content-body']").shouldBe(Condition.visible);
        ibNextAbstractTest.checkPageURL(IBNextURLs.USERS_PAGE);
        return new IBUsersPage();
    }

    public IBUserCreatePage openIBUserCreatePage() {
        addIBUserButton.click();
        PageSpinner.waitPreloader();
        $x("//li//a[contains (@href,'" + IBNextURLs.USERS_CREATE_PAGE + "')]")
                .click();
        return IBUserCreatePage.init();
    }

    public IBUsersSyncPage openIBUserSyncPage() {
        addIBUserButton.click();
        $x("//li//a[contains (@href,'users/sync')]")
                .click();
        return IBUsersSyncPage.init();
    }

    public IBUserImportPage openIBUserImportPage() {
        addIBUserButton.click();
        $x("//li//a[contains (@href,'" + IBNextURLs.USERS_IMPORT_PAGE + "')]")
                .click();
        return IBUserImportPage.init();
    }

    public boolean isUserPresents(String nameIBUser) {
        return $x("//span[contains (text(),'" + nameIBUser + "')]").is(Condition.visible);
    }

    public boolean areUsersPresents() {
        return $x("//div[@class='table-empty']")
                .isDisplayed();
    }

    public IBUsersPage deleteUser(String userFirstName) {
        getThreeDotMenuUser(userFirstName)
                .click();
        userActionDropdownDeleteOption
                .click();
        deleteSelectedUserPromtModal(true);
        return this;
    }

    public IBUsersPage deleteUser() {
        $x("(//td/following-sibling::td//button[contains(@class,'dropdown-toggle')])[1]").click();
        userActionDropdownDeleteOption.click();
        deleteSelectedUserPromtModal(true);
        return this;
    }

    public void logInSelectedUsers(String userFirstName) {
        getThreeDotMenuUser(userFirstName)
                .click();
        userActionLogInOption
                .click();
    }

    public IBUserCreatePage editSelectedUser(String userFirstName) {
        getThreeDotMenuUser(userFirstName)
                .click();
        userActionEditOption
                .click();
        return IBUserCreatePage.init();
    }

    public IBUserAssignmentsPage assignmentsUser(String userFirstName) {
        getThreeDotMenuUser(userFirstName)
                .click();
        userActionAssignmentsOption
                .click();
        return IBUserAssignmentsPage.init();
    }

    private SelenideElement getThreeDotMenuUser(String userFirstName) {
        return $x("//td[ ./span[contains(text(),'" + userFirstName + "')]]/following-sibling::td//button[contains(@class,'dropdown-toggle')]");
    }

    public IBUsersPage checkedUserByName(String firstUserName) {
        $x("//td[ ./span[contains(text(),'" + firstUserName + "')]]/preceding-sibling::td").click();
        return this;
    }

    public IBUsersPage checkedAllUsers() {
        $x("//table//thead//th[contains (@class,'table-checkbox')]//div").click();
        return this;
    }

    public IBUsersPage deleteSelectedUsersByActionDropdown() {
        userActionDropdownMenu.click();
        $x("((//div[@class='card']//div[@class='intelli-dropdown dropdown'])[2]//a)[2]").click();
        deleteSelectedUserPromtModal(true);
        return this;
    }

    public IBUsersPage deleteSelectedUserPromtModal(boolean yesORno) {
        $x("//div[@class='modal-content']").shouldBe(Condition.visible);
        if (yesORno) {

            /*
             There some different modals for delete prompt for deleting from context menu and action menu.
             "Yes" button has two selectors.
             */
            if ($x("//div[@class='modal-content']//a[contains(@class, 'error')]").exists()) {
                $x("//div[@class='modal-content']//a[contains(@class, 'error')]")
                        .click();
            } else {
                $x("//div[@class='modal-content']//button[@type='submit' and contains(@class, 'error')]")
                        .click();
            }
        } else {
            $x("//div[@class='modal-content']//button[contains (@class,'default')]")
                    .shouldBe(Condition.visible)
                    .click();
        }
        $x("//div[@class='modal-content']")
                .shouldBe(Condition.disappear, Duration.ofMinutes(10));
        return this;
    }

    //TODO MO - add Enum for attributes scaling
    public IBUsersPage changeScalingUsersPerPage(int usersPerPage) {

        if (!$x("//div[@aria-placeholder='Per Page' and @class='tree-select']//button/span")
                .getText().equals(String.valueOf(usersPerPage))) {
            $x("//div[contains (@class,'pagination-wrapper')]//div[contains(@class,'intelli-dropdown')]")
                    .click();
            $x("//div[contains (@class,'pagination-wrapper')]//div[contains(@class,'intelli-dropdown')]//ul//label/*[text()='" + usersPerPage + "']")
                    .click();
        }
        return IBUsersPage.init();
    }

    //TODO MO - add Enum for pagination
    // "next" "prev"
    public IBUsersPage changePaginationPage(String nextOrPrev) {
        paginationBlock.shouldBe(Condition.visible);
        $x("//div[contains (@class,'pagination-wrapper')]//ul[@class='pagination']//a[@rel='" + nextOrPrev + "']").click();
        return IBUsersPage.init();
    }

    public IBUsersPage searchUserByName(String userName) {
        $x("//input[contains (@aria-label, 'Search User')]")
                .setValue(userName)
                .sendKeys(Keys.ENTER);
        return this;
    }

    public OrgRolesMainPage openOrgRolesPage() {
        $x("//a[contains (@href,'/roles')]").click();
        return OrgRolesMainPage.init();

    }

    public boolean isPaginationPresented() {
        return paginationBlock.isDisplayed();
    }

    public boolean isFirstUserPresented() {
        return firstUserRow.isDisplayed();
    }

}
