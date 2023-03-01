package net.intelliboard.next.services.pages.IBUsers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.IBNextURLs;
import net.intelliboard.next.services.pages.elements.spinners.PageSpinner;
import net.intelliboard.next.services.pages.library.LibraryItemTypeEnum;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;
import static net.intelliboard.next.AbstractTest.SLEEP_TIMEOUT_LONG;

public class IBUserCreatePage {

    public static IBUserCreatePage init() {
        PageSpinner.waitPreloader();
        PageSpinner.waitSpinner();
        IBNextAbstractTest ibNextAbstractTest = new IBNextAbstractTest();
        ibNextAbstractTest.waitForPageLoaded();
        $x("//div[@class='content-body']//form").shouldBe(Condition.visible,
                Duration.ofSeconds(120));
        ibNextAbstractTest.checkPageURL(IBNextURLs.USERS_CREATE_PAGE);
        return new IBUserCreatePage();
    }

    @Step("Fill in field")
    public IBUserCreatePage fillInField(CreateIBUsersFormFieldTypeEnum fieldType, String value) {
        $x(fieldType.value).setValue(value);
        return this;
    }

    @Step("Select Role")
    public IBUserCreatePage selectRole(IBUsersRolesTypeEnum role) {
        $x("//div[@name='role']//button").click();
        $x("//strong[text()='" + role.value + "']")
                .click();
        return this;
    }

    @Step("Select Connection")
    public IBUserCreatePage selectConnection() {
        SelenideElement firstConnection =
                $x("(//input[contains (@id, 'connections')])[1]");

        if (!firstConnection.isSelected()) {
            firstConnection.click();
        }

        return this;
    }

    @Step("Select Connection")
    public IBUserCreatePage selectConnection(String connectionName) {
        SelenideElement connection =
                $x("//input[contains (@id, 'connections') and following-sibling::label[contains (text(),'" + connectionName + "')]]");
        if (!connection.isSelected()) {
            connection.click();
        }
        return this;
    }

    public IBUsersPage submitUserCreateForm() {
        Selenide.sleep(SLEEP_TIMEOUT_LONG);
        $x("//button[@type='submit']")
                .click();
        return IBUsersPage.init();
    }

    public IBUserCreatePage selectItem(LibraryItemTypeEnum libraryItemType, String itemName) {
        //deselect all items
        selectDeselectAllItemFromLibraryByType(libraryItemType, IBUsersAssignedItemsAllOrNoneEnum.NONE);

        $x("//div[contains (@class,'card') and .//div[contains (text(),'" +
                libraryItemType.value + "')]]//div[contains (@class,'checkbox-wrapper')  and ./label[contains (text(),'" +
                itemName + "')]]")
                .click();
        return this;
    }

    public IBUserCreatePage selectDeselectAllItemFromLibraryByType(LibraryItemTypeEnum libraryItemType, IBUsersAssignedItemsAllOrNoneEnum allOrNone) {

        SelenideElement radioButton = $x("//div[contains (@class,'card') and .//div[contains (text(),'" +
                libraryItemType.value + "')]]//span[contains (text(),'" +
                allOrNone.value + "')]");

        SelenideElement radioButtonCheck = $x("//div[contains (@class,'card') and .//div[contains (text(),'" +
                libraryItemType.value + "')]]//span[contains (text(),'" +
                allOrNone.value + "')]/span");

        if (!radioButtonCheck.has(Condition.checked)) {
            radioButton.click();
        }
        return this;
    }
}
