package net.intelliboard.next.tests.core.ibusers.addnew;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Flaky;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.IBNextURLs;
import net.intelliboard.next.services.helpers.DataGenerator;
import net.intelliboard.next.services.pages.IBUsers.CreateIBUsersFormFieldTypeEnum;
import net.intelliboard.next.services.pages.IBUsers.IBUsersRolesTypeEnum;
import net.intelliboard.next.services.pages.IBUsers.IBUsersPage;
import net.intelliboard.next.services.pages.connections.ConnectionsTypeEnum;
import net.intelliboard.next.services.pages.header.HeaderObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.assertThat;

@Feature("IBUser")
@Tag("IBUser")
class CreateNewUsersTest extends IBNextAbstractTest {

    @ParameterizedTest
    @EnumSource(value = IBUsersRolesTypeEnum.class)
    @Tags(value = {@Tag("smoke"), @Tag("normal"), @Tag("SP-T111"), @Tag("SP-T986"), @Tag("smoke_core")})
    @DisplayName("SP-T111 SP-T986: Adding new IB user with button \"Create one\"")
    void testCreateNewIBUser(IBUsersRolesTypeEnum roles) {

        String firstName = DataGenerator.getRandomString();
        String lastName = DataGenerator.getRandomString();

        HeaderObject.init()
                .openDropDownMenu()
                .openMyIBUsersPage()
                .openIBUserCreatePage()
                .selectRole(roles)
                .fillInField(CreateIBUsersFormFieldTypeEnum.EMAIL, DataGenerator.getRandomValidEmail())
                .fillInField(CreateIBUsersFormFieldTypeEnum.FIRST_NAME, firstName)
                .fillInField(CreateIBUsersFormFieldTypeEnum.LAST_NAME, lastName)
                .fillInField(CreateIBUsersFormFieldTypeEnum.JOB_TITLE, DataGenerator.getRandomString())
                .fillInField(CreateIBUsersFormFieldTypeEnum.PASSWORD, DataGenerator.getRandomValidPassword())
                .selectConnection()
                .submitUserCreateForm();

        // Assertion
        assertThat(IBUsersPage.init().changeScalingUsersPerPage(200).isUserPresents(firstName + " " + lastName))
                .withFailMessage("User with name %s is not existed", firstName)
                .isTrue();

        //Clean Up
        IBUsersPage.init().deleteUser(firstName);
    }

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T112")})
    @DisplayName("SP-T112: Adding new IB user with button \"Create one\" without required field - EMAIL")
    void testCreateIBUserInvalidField_EMAIL() {

        HeaderObject.init()
                .openDropDownMenu()
                .openMyIBUsersPage()
                .openIBUserCreatePage()
                .selectRole(IBUsersRolesTypeEnum.MANAGER)
                .fillInField(CreateIBUsersFormFieldTypeEnum.FIRST_NAME, DataGenerator.getRandomString())
                .fillInField(CreateIBUsersFormFieldTypeEnum.LAST_NAME, DataGenerator.getRandomString())
                .fillInField(CreateIBUsersFormFieldTypeEnum.PASSWORD, DataGenerator.getRandomValidPassword())
                .selectConnection()
                .submitUserCreateForm();

        waitForPageLoaded();
        assertThat(IBNextURLs.USERS_PAGE.equals(WebDriverRunner.getWebDriver().getCurrentUrl()))
                .withFailMessage("User has been created with a missed mandatory field - EMAIL")
                .isFalse();
    }

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T112")})
    @DisplayName("SP-T112: Adding new IB user with button \"Create one\" without required field - FIRST_NAME")
    void testCreateIBUserInvalidField_FIRST_NAME() {

        HeaderObject.init()
                .openDropDownMenu()
                .openMyIBUsersPage()
                .openIBUserCreatePage()
                .selectRole(IBUsersRolesTypeEnum.MANAGER)
                .fillInField(CreateIBUsersFormFieldTypeEnum.EMAIL, DataGenerator.getRandomValidEmail())
                .fillInField(CreateIBUsersFormFieldTypeEnum.LAST_NAME, DataGenerator.getRandomString())
                .fillInField(CreateIBUsersFormFieldTypeEnum.PASSWORD, DataGenerator.getRandomValidPassword())
                .selectConnection()
                .submitUserCreateForm();

        waitForPageLoaded();
        assertThat(IBNextURLs.USERS_PAGE.equals(WebDriverRunner.getWebDriver().getCurrentUrl()))
                .withFailMessage("User has been created with a missed mandatory field - FIRST_NAME")
                .isFalse();
    }

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T112")})
    @DisplayName("SP-T112: Adding new IB user with button \"Create one\" without required field - LAST_NAME")
    void testCreateIBUserInvalidField_LAST_NAME() {

        HeaderObject.init()
                .openDropDownMenu()
                .openMyIBUsersPage()
                .openIBUserCreatePage()
                .selectRole(IBUsersRolesTypeEnum.MANAGER)
                .fillInField(CreateIBUsersFormFieldTypeEnum.EMAIL, DataGenerator.getRandomValidEmail())
                .fillInField(CreateIBUsersFormFieldTypeEnum.FIRST_NAME, DataGenerator.getRandomString())
                .fillInField(CreateIBUsersFormFieldTypeEnum.PASSWORD, DataGenerator.getRandomValidPassword())
                .selectConnection()
                .submitUserCreateForm();

        waitForPageLoaded();
        assertThat(IBNextURLs.USERS_PAGE.equals(WebDriverRunner.getWebDriver().getCurrentUrl()))
                .withFailMessage("User has been created with a missed mandatory field - LAST_NAME")
                .isFalse();
    }

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T112")})
    @DisplayName("SP-T112: Adding new IB user with button \"Create one\" without required field - PASSWORD")
    void testCreateIBUserInvalidField_PASSWORD() {

        HeaderObject.init()
                .openDropDownMenu()
                .openMyIBUsersPage()
                .openIBUserCreatePage()
                .selectRole(IBUsersRolesTypeEnum.MANAGER)
                .fillInField(CreateIBUsersFormFieldTypeEnum.EMAIL, DataGenerator.getRandomValidEmail())
                .fillInField(CreateIBUsersFormFieldTypeEnum.FIRST_NAME, DataGenerator.getRandomString())
                .fillInField(CreateIBUsersFormFieldTypeEnum.PASSWORD, " ")
                .fillInField(CreateIBUsersFormFieldTypeEnum.LAST_NAME, DataGenerator.getRandomString())
                .selectConnection()
                .submitUserCreateForm();

        IBUsersPage.init()
                .getPasswordErrors()
                .shouldBe(CollectionCondition.size(1));

        waitForPageLoaded();
        assertThat(IBNextURLs.USERS_PAGE.equals(WebDriverRunner.getWebDriver().getCurrentUrl()))
                .withFailMessage("User has been created with a missed mandatory field - LAST_NAME")
                .isFalse();
    }

    @Test
    @Tags(value = {@Tag("smoke"), @Tag("normal"), @Tag("SP-T118"), @Tag("health"), @Tag("smoke_core"), @Tag("SP-T987")})
    @DisplayName("SP-T118 SP-T987: Deleting created user")
    void testDeleteCreatedIBUser() {

        HeaderObject header = HeaderObject.init();

        String firstName = DataGenerator.getRandomString();
        String lastName = DataGenerator.getRandomString();

        header
                .openDropDownMenu()
                .openMyIBUsersPage()
                .openIBUserCreatePage()
                .selectRole(IBUsersRolesTypeEnum.MANAGER)
                .fillInField(CreateIBUsersFormFieldTypeEnum.EMAIL, DataGenerator.getRandomValidEmail())
                .fillInField(CreateIBUsersFormFieldTypeEnum.FIRST_NAME, firstName)
                .fillInField(CreateIBUsersFormFieldTypeEnum.LAST_NAME, lastName)
                .fillInField(CreateIBUsersFormFieldTypeEnum.JOB_TITLE, DataGenerator.getRandomString())
                .fillInField(CreateIBUsersFormFieldTypeEnum.PASSWORD, DataGenerator.getRandomValidPassword())
                .selectConnection()
                .submitUserCreateForm();

        //Delete user
        IBUsersPage
                .init()
                .deleteUser(firstName);

        // Assertion
        assertThat(
                IBUsersPage
                        .init()
                        .changeScalingUsersPerPage(200)
                        .isUserPresents(firstName + " " + lastName))
                .withFailMessage("User with name %s is existed and isn't deleted", firstName)
                .isFalse();
    }

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T119")})
    @DisplayName("SP-T119: Deleting several created users")
    void testDeleteFewCreatedIBUsers() {

        HeaderObject header = HeaderObject.init();

        String firstName1 = DataGenerator.getRandomString();
        String lastName1 = DataGenerator.getRandomString();

        String firstName2 = DataGenerator.getRandomString();
        String lastName2 = DataGenerator.getRandomString();

        //Create user #1
        header
                .openDropDownMenu()
                .openMyIBUsersPage()
                .openIBUserCreatePage()
                .selectRole(IBUsersRolesTypeEnum.MANAGER)
                .fillInField(CreateIBUsersFormFieldTypeEnum.EMAIL, DataGenerator.getRandomValidEmail())
                .fillInField(CreateIBUsersFormFieldTypeEnum.FIRST_NAME, firstName1)
                .fillInField(CreateIBUsersFormFieldTypeEnum.LAST_NAME, lastName1)
                .fillInField(CreateIBUsersFormFieldTypeEnum.JOB_TITLE, DataGenerator.getRandomString())
                .fillInField(CreateIBUsersFormFieldTypeEnum.PASSWORD, DataGenerator.getRandomValidPassword())
                .selectConnection()
                .submitUserCreateForm();
        //Create user #1
        header
                .openDropDownMenu()
                .openMyIBUsersPage()
                .openIBUserCreatePage()
                .selectRole(IBUsersRolesTypeEnum.MANAGER)
                .fillInField(CreateIBUsersFormFieldTypeEnum.EMAIL, DataGenerator.getRandomValidEmail())
                .fillInField(CreateIBUsersFormFieldTypeEnum.FIRST_NAME, firstName2)
                .fillInField(CreateIBUsersFormFieldTypeEnum.LAST_NAME, lastName2)
                .fillInField(CreateIBUsersFormFieldTypeEnum.JOB_TITLE, DataGenerator.getRandomString())
                .fillInField(CreateIBUsersFormFieldTypeEnum.PASSWORD, DataGenerator.getRandomValidPassword())
                .selectConnection()
                .submitUserCreateForm();

        //Delete Users
        IBUsersPage.init()
                .checkedUserByName(firstName1)
                .checkedUserByName(firstName2)
                .deleteSelectedUsersByActionDropdown();

        //Verify deleting User#1
        assertThat(IBUsersPage
                .init()
                .changeScalingUsersPerPage(200)
                .isUserPresents(firstName1))
                .withFailMessage("IB User with name %s has not been deleted", firstName1)
                .isFalse();

        //Verify deleting User#2
        assertThat(IBUsersPage
                .init()
                .changeScalingUsersPerPage(200)
                .isUserPresents(firstName2)).withFailMessage("IB User with name %s has not been deleted", firstName2)
                .isFalse();
    }

    @Test
    @Tags(value = {@Tag("normal")})
    @DisplayName("SP-T112: Create User with already registered email")
    void testCreateUserWithAlreadyRegisteredEmail() {

        HeaderObject header = HeaderObject.init();

        String firstName1 = DataGenerator.getRandomString();
        String lastName1 = DataGenerator.getRandomString();

        String firstName2 = DataGenerator.getRandomString();
        String lastName2 = DataGenerator.getRandomString();

        String email = DataGenerator.getRandomValidEmail();

        //Create user #1
        header
                .openDropDownMenu()
                .openMyIBUsersPage()
                .openIBUserCreatePage()
                .selectRole(IBUsersRolesTypeEnum.MANAGER)
                .fillInField(CreateIBUsersFormFieldTypeEnum.EMAIL, email)
                .fillInField(CreateIBUsersFormFieldTypeEnum.FIRST_NAME, firstName1)
                .fillInField(CreateIBUsersFormFieldTypeEnum.LAST_NAME, lastName1)
                .fillInField(CreateIBUsersFormFieldTypeEnum.PASSWORD, DataGenerator.getRandomValidPassword())
                .selectConnection()
                .submitUserCreateForm();
        //Create user #1
        header
                .openDropDownMenu()
                .openMyIBUsersPage()
                .openIBUserCreatePage()
                .selectRole(IBUsersRolesTypeEnum.MANAGER)
                .fillInField(CreateIBUsersFormFieldTypeEnum.EMAIL, email)
                .fillInField(CreateIBUsersFormFieldTypeEnum.FIRST_NAME, firstName2)
                .fillInField(CreateIBUsersFormFieldTypeEnum.LAST_NAME, lastName2)
                .fillInField(CreateIBUsersFormFieldTypeEnum.PASSWORD, DataGenerator.getRandomValidPassword())
                .selectConnection()
                .submitUserCreateForm();

        waitForPageLoaded();

        IBUsersPage
                .init()
                .getEmailError()
                .shouldBe(Condition.visible);

        assertThat(IBNextURLs.USERS_PAGE.equals(WebDriverRunner.getWebDriver().getCurrentUrl()))
                .withFailMessage("User has been created with has already registered EMAIL")
                .isFalse();
    }

    @Test
    @Tags(value = {@Tag("SP-T116")})
    @DisplayName("SP-T116: Scaling the number of users")
    void testScalingNumberUsers() {

        //Create new users
        HeaderObject header = HeaderObject.init();
        IBUsersPage ibUsersPage = IBUsersPage.init();

        // Delete exist users
        while (ibUsersPage.isFirstUserPresented()) {
            ibUsersPage.deleteUser();
        }

        // Add users for checking pagination
        int users = 12;
        for (int i = 0; i < users; i++) {
            header
                    .openDropDownMenu()
                    .openMyIBUsersPage()
                    .openIBUserCreatePage()
                    .selectRole(IBUsersRolesTypeEnum.MANAGER)
                    .fillInField(CreateIBUsersFormFieldTypeEnum.EMAIL, DataGenerator.getRandomValidEmail())
                    .fillInField(CreateIBUsersFormFieldTypeEnum.FIRST_NAME, DataGenerator.getRandomString())
                    .fillInField(CreateIBUsersFormFieldTypeEnum.LAST_NAME, DataGenerator.getRandomString())
                    .fillInField(CreateIBUsersFormFieldTypeEnum.JOB_TITLE, DataGenerator.getRandomString())
                    .fillInField(CreateIBUsersFormFieldTypeEnum.PASSWORD, DataGenerator.getRandomValidPassword())
                    .selectConnection()
                    .submitUserCreateForm();
        }

        ibUsersPage.changePaginationPage("next");
        ibUsersPage.changeScalingUsersPerPage(25);

        assertThat(ibUsersPage.isPaginationPresented())
                .withFailMessage("Pagination is broken")
                .isFalse();

        while (ibUsersPage.isFirstUserPresented())
            ibUsersPage
                    .checkedAllUsers()
                    .deleteSelectedUsersByActionDropdown();
    }

    @Flaky
    @Test
    @Tags(value = {@Tag("smoke"), @Tag("normal"), @Tag("SP-T1070"), @Tag("smoke_core")})
    @DisplayName("SP-T1070: Log in as an IB User")
    @Description("Verify that login as an IB User is possible and the user has an access to the platform")
    void testLoginAsIBUser() {

        String firstName = "SP-T1070_" + DataGenerator.getRandomString();
        String lastName = DataGenerator.getRandomString();

        HeaderObject.init()
                .openDropDownMenu()
                .openMyIBUsersPage()
                .openIBUserCreatePage()
                .selectRole(IBUsersRolesTypeEnum.ALL_ACCESS)
                .fillInField(CreateIBUsersFormFieldTypeEnum.EMAIL, DataGenerator.getRandomValidEmail())
                .fillInField(CreateIBUsersFormFieldTypeEnum.FIRST_NAME, firstName)
                .fillInField(CreateIBUsersFormFieldTypeEnum.LAST_NAME, lastName)
                .fillInField(CreateIBUsersFormFieldTypeEnum.JOB_TITLE, DataGenerator.getRandomString())
                .fillInField(CreateIBUsersFormFieldTypeEnum.PASSWORD, DataGenerator.getRandomValidPassword())
                .selectConnection(ConnectionsTypeEnum.CANVAS.defaultName)
                .submitUserCreateForm()
                .changeScalingUsersPerPage(200)
                .logInSelectedUsers(firstName);

        waitForPageLoaded();
    }
}