package net.intelliboard.next.services.pages.IBUsers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.IBNextURLs;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class IBUsersSyncPage {

    private SelenideElement LMSUserDropdown =
            $x("//div[contains(@class,'card-body')]//div[@name='lms_users_ids']//button[@class='tree-choice']");
    private SelenideElement RolesDropdown =
            $x("//div[contains(@class,'card-body')]//div[@name='role_id']//div[contains (@class,'intelli-dropdown')]");
    private SelenideElement LMSDropdown =
            $x("//div[contains(@class,'card-body')]//div[@name='lms_roles']//div[contains (@class,'intelli-dropdown')]");


    public static IBUsersSyncPage init() {
        IBNextAbstractTest ibNextAbstractTest = new IBNextAbstractTest();
        ibNextAbstractTest.waitForPageLoaded();
        $x("//div[@class='content-body']").shouldBe(Condition.visible);
        ibNextAbstractTest.checkPageURL(IBNextURLs.USERS_SYNC_PAGE);
        return new IBUsersSyncPage();
    }

    public IBUsersSyncPage selectConnection(String connectionName) {
        $x("//div[@name='connection_id']//div[contains(@class,'intelli-dropdown')]//button")
                .click();
        $x("//div[@name='connection_id']//div[contains(@class,'dropdown-body')]//li//strong[text()='" + connectionName + "']")
                .click();
        $x("//div[@name='connection_id']/label")
                .click();
        return this;
    }

    public IBUsersSyncPage selectConnection() {
        $x("//div[@name='connection_id']//div[contains(@class,'intelli-dropdown')]//button")
                .click();
        $x("(//div[@label='Connection']//ul//li[ not (@style='display: none;')])[1]")
                .click();
        $x("//div[@name='connection_id']/label")
                .click();
        return this;
    }

    public IBUsersSyncPage selectLMSRole(String name) {

        LMSDropdown.click();

        $x("//div[contains(@class,'card-body')]//div[@name='lms_roles']//div[contains (@class,'intelli-dropdown')]//li//strong[text()='" + name + "']")
                .click();
        $x("//button[@type='submit']")
                .click();
        return this;
    }

    public IBUsersSyncPage selectLMSRole() {
       LMSDropdown.click();

        $x("(//div[contains(@class,'card-body')]//div[@name='lms_roles']//div[contains (@class,'intelli-dropdown')]//li//strong)[1]")
                .click();
        $x("//button[@type='submit']")
                .click();
        return this;
    }

    public IBUsersSyncPage selectRole(CreateIBUsersFormRolesTypeEnum role) {

        RolesDropdown
                .click();
        $x("//div[contains(@class,'card-body')]//div[@name='role_id']//div[contains (@class,'intelli-dropdown')]//li//strong[text()='" + role.value + "']")
                .click();
        return this;
    }

    public IBUsersSyncPage selectFirstLMSUser() {

        LMSUserDropdown
                .click();
        $x("(//div[contains(@class,'card-body')]//div[@name='lms_users_ids']//li//div[contains (@class,'select')])[1]")
                .click();
        $x("//div[contains(@class,'content-header')]//h2")
                .click();
        return this;
    }

    public IBUsersSyncPage selectAllLMSUser() {

        LMSUserDropdown
                .click();
        $x("//div[contains(@class,'card-body')]//div[@name='lms_users_ids']//div[contains (@class,'select-all')]")
                .click();
        $x("//div[contains(@class,'content-header')]//h2")
                .click();
        return this;
    }

    public String getNameSelectedLMSUser() {
        return $x("//div[contains(@class,'card-body')]//div[@name='lms_users_ids']//button[@class='tree-choice']//span")
                .getText();
    }

    public IBUsersSyncPage syncUsers() {
        $x("//div[contains(@class,'button-set')]//button")
                .click();
        $x("//div[contains(@class,'progress-tracker')]//strong[text()='success']")
                .should(Condition.visible, Duration.ofSeconds(160));
        return this;
    }
}