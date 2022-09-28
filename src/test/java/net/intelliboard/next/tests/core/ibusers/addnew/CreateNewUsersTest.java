package net.intelliboard.next.tests.core.ibusers.addnew;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Feature;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.IBNextURLs;
import net.intelliboard.next.services.helpers.DataGenerator;
import net.intelliboard.next.services.pages.IBUsers.CreateIBUsersFormFieldTypeEnum;
import net.intelliboard.next.services.pages.IBUsers.CreateIBUsersFormRolesTypeEnum;
import net.intelliboard.next.services.pages.IBUsers.IBUsersPage;
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
public class CreateNewUsersTest extends IBNextAbstractTest {

    @ParameterizedTest
    @EnumSource(value = CreateIBUsersFormRolesTypeEnum.class)
    @Tags(value = {@Tag("smoke"), @Tag("normal"), @Tag("SP-T111")})
    @DisplayName("SP-T111: Adding new IB user with button \"Create one\"")
    public void testCreateNewIBUser(CreateIBUsersFormRolesTypeEnum roles) {

        HeaderObject header = HeaderObject.init();

        String firstName = DataGenerator.getRandomString();
        String lastName = DataGenerator.getRandomString();

        header
                .openDropDownMenu()
                .openMyIBUsersPage()
                .openIBUserCreatePage()
                .selectRole(roles)
                .fillInField(CreateIBUsersFormFieldTypeEnum.EMAIL, DataGenerator.getRandomValidEmail())
                .fillInField(CreateIBUsersFormFieldTypeEnum.FIRST_NAME, firstName)
                .fillInField(CreateIBUsersFormFieldTypeEnum.LAST_NAME, lastName)
                .fillInField(CreateIBUsersFormFieldTypeEnum.JOB_TITLE, DataGenerator.getRandomString())
                .fillInField(CreateIBUsersFormFieldTypeEnum.PASSWORD, DataGenerator.getRandomValidPassword())
//                .selectConnection()
                .submitUserCreateForm();


        // Assertion
        assertThat(IBUsersPage.init().changeScalingUsersPerPage(200).isUserPresents(firstName + " " + lastName))
                .isTrue()
                .as(String.format("User with name %s is not existed", firstName));

        //Clean Up
        IBUsersPage.init().deleteUser(firstName);
    }

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T112")})
    @DisplayName("SP-T112: Adding new IB user with button \"Create one\" without required field - EMAIL")
    public void testCreateIBUserInvalidField_EMAIL() {

        HeaderObject.init()
                .openDropDownMenu()
                .openMyIBUsersPage()
                .openIBUserCreatePage()
                .selectRole(CreateIBUsersFormRolesTypeEnum.MANAGER)
                .fillInField(CreateIBUsersFormFieldTypeEnum.FIRST_NAME, DataGenerator.getRandomString())
                .fillInField(CreateIBUsersFormFieldTypeEnum.LAST_NAME, DataGenerator.getRandomString())
                .fillInField(CreateIBUsersFormFieldTypeEnum.PASSWORD, DataGenerator.getRandomValidPassword())
                .selectConnection()
                .submitUserCreateForm();

        waitForPageLoaded();
        assertThat(IBNextURLs.USERS_PAGE.equals(WebDriverRunner.getWebDriver().getCurrentUrl()))
                .isFalse().as("User has been created with a missed mandatory field - EMAIL");
    }

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T112")})
    @DisplayName("SP-T112: Adding new IB user with button \"Create one\" without required field - FIRST_NAME")
    public void testCreateIBUserInvalidField_FIRST_NAME() {

        HeaderObject.init()
                .openDropDownMenu()
                .openMyIBUsersPage()
                .openIBUserCreatePage()
                .selectRole(CreateIBUsersFormRolesTypeEnum.MANAGER)
                .fillInField(CreateIBUsersFormFieldTypeEnum.EMAIL, DataGenerator.getRandomValidEmail())
                .fillInField(CreateIBUsersFormFieldTypeEnum.LAST_NAME, DataGenerator.getRandomString())
                .fillInField(CreateIBUsersFormFieldTypeEnum.PASSWORD, DataGenerator.getRandomValidPassword())
                .selectConnection()
                .submitUserCreateForm();

        waitForPageLoaded();
        assertThat(IBNextURLs.USERS_PAGE.equals(WebDriverRunner.getWebDriver().getCurrentUrl()))
                .isFalse().as("User has been created with a missed mandatory field - FIRST_NAME");
    }

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T112")})
    @DisplayName("SP-T112: Adding new IB user with button \"Create one\" without required field - LAST_NAME")
    public void testCreateIBUserInvalidField_LAST_NAME() {

        HeaderObject.init()
                .openDropDownMenu()
                .openMyIBUsersPage()
                .openIBUserCreatePage()
                .selectRole(CreateIBUsersFormRolesTypeEnum.MANAGER)
                .fillInField(CreateIBUsersFormFieldTypeEnum.EMAIL, DataGenerator.getRandomValidEmail())
                .fillInField(CreateIBUsersFormFieldTypeEnum.FIRST_NAME, DataGenerator.getRandomString())
                .fillInField(CreateIBUsersFormFieldTypeEnum.PASSWORD, DataGenerator.getRandomValidPassword())
                .selectConnection()
                .submitUserCreateForm();

        waitForPageLoaded();
        assertThat(IBNextURLs.USERS_PAGE.equals(WebDriverRunner.getWebDriver().getCurrentUrl()))
                .isFalse().as("User has been created with a missed mandatory field - LAST_NAME");
    }

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T112")})
    @DisplayName("SP-T112: Adding new IB user with button \"Create one\" without required field - PASSWORD")
    public void testCreateIBUserInvalidField_PASSWORD() {

        HeaderObject.init()
                .openDropDownMenu()
                .openMyIBUsersPage()
                .openIBUserCreatePage()
                .selectRole(CreateIBUsersFormRolesTypeEnum.MANAGER)
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
                .isFalse().as("User has been created with a missed mandatory field - LAST_NAME");
    }

    @Test
    @Tags(value = {@Tag("smoke"), @Tag("normal"), @Tag("SP-T118"), @Tag("health")})
    @DisplayName("SP-T118: Deleting created user")
    public void testDeleteCreatedIBUser() {

        HeaderObject header = HeaderObject.init();

        String firstName = DataGenerator.getRandomString();
        String lastName = DataGenerator.getRandomString();

        header
                .openDropDownMenu()
                .openMyIBUsersPage()
                .openIBUserCreatePage()
                .selectRole(CreateIBUsersFormRolesTypeEnum.MANAGER)
                .fillInField(CreateIBUsersFormFieldTypeEnum.EMAIL, DataGenerator.getRandomValidEmail())
                .fillInField(CreateIBUsersFormFieldTypeEnum.FIRST_NAME, firstName)
                .fillInField(CreateIBUsersFormFieldTypeEnum.LAST_NAME, lastName)
                .fillInField(CreateIBUsersFormFieldTypeEnum.JOB_TITLE, DataGenerator.getRandomString())
                .fillInField(CreateIBUsersFormFieldTypeEnum.PASSWORD, DataGenerator.getRandomValidPassword())
                .selectConnection()
                .submitUserCreateForm();

        //Delete user
        IBUsersPage.init().deleteUser(firstName);

        // Assertion
        assertThat(IBUsersPage.init().isUserPresents(firstName + " " + lastName))
                .isFalse()
                .as(String.format("User with name %s is existed and isn't deleted", firstName));
    }

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T119")})
    @DisplayName("SP-T119: Deleting several created users")
    public void testDeleteFewCreatedIBUsers() {

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
                .selectRole(CreateIBUsersFormRolesTypeEnum.MANAGER)
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
                .selectRole(CreateIBUsersFormRolesTypeEnum.MANAGER)
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
        assertThat(IBUsersPage.init().isUserPresents(firstName1))
                .isFalse()
                .as(String.format("IB User with name %s has not been deleted", firstName1));

        //Verify deleting User#2
        assertThat(IBUsersPage.init().isUserPresents(firstName2))
                .isFalse()
                .as(String.format("IB User with name %s has not been deleted", firstName2));
    }

    @Test
    @Tags(value = {@Tag("normal")})
    @DisplayName("SP-T112: Create User with already registered email")
    public void testCreateUserWithAlreadyReisteredEmail() {

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
                .selectRole(CreateIBUsersFormRolesTypeEnum.MANAGER)
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
                .selectRole(CreateIBUsersFormRolesTypeEnum.MANAGER)
                .fillInField(CreateIBUsersFormFieldTypeEnum.EMAIL, email)
                .fillInField(CreateIBUsersFormFieldTypeEnum.FIRST_NAME, firstName2)
                .fillInField(CreateIBUsersFormFieldTypeEnum.LAST_NAME, lastName2)
                .fillInField(CreateIBUsersFormFieldTypeEnum.PASSWORD, DataGenerator.getRandomValidPassword())
                .selectConnection()
                .submitUserCreateForm();

        waitForPageLoaded();

        IBUsersPage.init().getEmailError().shouldBe(Condition.visible);

        assertThat(IBNextURLs.USERS_PAGE.equals(WebDriverRunner.getWebDriver().getCurrentUrl()))
                .isFalse().as("User has been created with has already registered EMAIL");

    }

    @Test
    @Tags(value = {@Tag("SP-T116")})
    @DisplayName("SP-T116: Scaling the number of users")
    public void testScalingNumberUsers() {

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
                    .selectRole(CreateIBUsersFormRolesTypeEnum.MANAGER)
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
                .isFalse()
                .as("Pagination is broken");

        while (ibUsersPage.isFirstUserPresented())
            ibUsersPage
                    .checkedAllUsers()
                    .deleteSelectedUsersByActionDropdown();
    }
}

