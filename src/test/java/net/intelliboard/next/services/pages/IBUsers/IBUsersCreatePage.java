package net.intelliboard.next.services.pages.IBUsers;

import com.codeborne.selenide.Condition;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.IBNextURLs;

import static com.codeborne.selenide.Selenide.$x;

public class IBUsersCreatePage {

    public static IBUsersCreatePage init() {
        IBNextAbstractTest ibNextAbstractTest = new IBNextAbstractTest();
        ibNextAbstractTest.waitForPageLoaded();
        $x("//div[@class='content-body']//form").shouldBe(Condition.visible);
        ibNextAbstractTest.checkPageURL(IBNextURLs.USERS_CREATE_PAGE);
        return new IBUsersCreatePage();
    }

    public IBUsersCreatePage fillInField(CreateIBUsersFormFieldTypeEnum fieldType, String value) {
        $x(fieldType.value).setValue(value);
        return this;
    }

    public IBUsersCreatePage selectRole(CreateIBUsersFormRolesTypeEnum role) {
        $x("//div[@name='role']//button").click();
        $x("//strong[text()='" + role.value + "']")
                .click();
        return this;
    }

    public IBUsersCreatePage selectConnection() {
        $x("//input[contains (@id, 'connections') and following-sibling::label[@class='label-text']]")
                .click();
        return this;
    }

    public IBUsersCreatePage selectConnection(String connectionName) {
        $x("//input[contains (@id, 'connections') and following-sibling::label[text()='" + connectionName + "']]")
                .click();
        return this;
    }

    public IBUsersPage submitUserCreateForm() {
        $x("//button[@type='submit']").click();
        return IBUsersPage.init();
    }
}
