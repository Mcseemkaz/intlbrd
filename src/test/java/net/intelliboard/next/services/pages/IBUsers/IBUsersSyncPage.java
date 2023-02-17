package net.intelliboard.next.services.pages.IBUsers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.IBNextURLs;
import net.intelliboard.next.services.pages.elements.DropdownElement;
import org.apache.commons.lang3.StringUtils;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class IBUsersSyncPage {

    private final SelenideElement LMSUserDropdown =
            $x("//div[contains(@class,'card-body')]//div[@name='lms_users_ids']//button[@class='tree-choice']");
    private final SelenideElement RolesDropdown =
            $x("//div[contains(@class,'card-body')]//div[@name='role_id']//div[contains (@class,'intelli-dropdown')]");
    private final SelenideElement ConnectionRolesDropdown =
            $x("//div[contains(@class,'card-body')]//div[@name='connection_roles']//div[contains (@class,'intelli-dropdown')]");
    private final SelenideElement PageHeader = $x("//div[contains(@class,'content-header')]//h1");

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

        ConnectionRolesDropdown.click();
        DropdownElement
                .init("Connection Role", 1)
                .selectOption("name");
        return this;
    }

    @Step("Select LMS Role for User - Default Learner Roles (Student)")
    public IBUsersSyncPage selectLMSRole() {

        // TODO [MO] Need to be remove when context will be the same for each envs
        String role = "";

        if (System.getProperty("TestEnvironment").contains("stage") || System.getProperty("TestEnvironment").contains("dev")) {
            role = "Learner Roles (Student, Teacher)";
        } else if (System.getProperty("TestEnvironment").contains("prod")) {
            role = "Learner Roles (Student)";
        }

        ConnectionRolesDropdown.click();
        DropdownElement
                .init("Connection Role", 1)
                .selectOption(role);
        return this;
    }

    public IBUsersSyncPage selectRole(IBUsersRolesTypeEnum role) {

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
        PageHeader
                .click();
        return this;
    }

    public IBUsersSyncPage selectAllLMSUser() {

        LMSUserDropdown
                .click();
        $x("//div[contains(@class,'card-body')]//div[@name='lms_users_ids']//div[contains (@class,'select-all')]")
                .click();
        PageHeader.click();
        return this;
    }

    public String getNameSelectedLMSUser() {
        Selenide.sleep(3000);
        String fullName = $x("//div[contains(@class,'card-body')]//div[@name='lms_users_ids']//button[@class='tree-choice']//span")
                .getText();

        return StringUtils.substringBefore(fullName, " (");
    }

    public IBUsersSyncPage syncUsers() {
        $x("//div[contains(@class,'button-set')]//button")
                .click();
        $x("//div[contains(@class,'progress-tracker')]//strong[text()='success']")
                .should(Condition.visible, Duration.ofMinutes(15));
        return this;
    }

    public boolean isRolesPresents(String roleName) {
        ConnectionRolesDropdown.click();
        return $x("//div[@name='connection_roles']//li//strong[contains (text(),'" + roleName + "')]")
                .exists();
    }
}
