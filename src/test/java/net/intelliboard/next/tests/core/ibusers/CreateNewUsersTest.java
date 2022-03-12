package net.intelliboard.next.tests.core.ibusers;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.WebDriverRunner;
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

import static com.codeborne.selenide.Selenide.$$x;
import static org.assertj.core.api.Assertions.assertThat;

public class CreateNewUsersTest extends IBNextAbstractTest {

    @ParameterizedTest
    @EnumSource(value = CreateIBUsersFormRolesTypeEnum.class)
    @Tags(value = {@Tag("smoke"), @Tag("normal"), @Tag("SP-T111")})
    @DisplayName("SP-T111: Adding new IB user with button \"Create one\"")
    public void testCreateNewIBUser(CreateIBUsersFormRolesTypeEnum roles) {

        loginAppUI(USER_LOGIN, USER_PASS);
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
                .selectConnection()
                .submitUserCreateForm();


        // Assertion
        assertThat(IBUsersPage.init().getUserByName(firstName + " " + lastName))
                .isTrue()
                .as(String.format("User with name %s is not existed", firstName));

        //Clean Up
        IBUsersPage.init().deleteUser(firstName);
    }

    @Test
    @Tags(value = {@Tag("smoke"), @Tag("normal"), @Tag("SP-T112")})
    @DisplayName("SP-T112: Adding new IB user with button \"Create one\" without required field - EMAIL")
    public void testCreateIBUserInvalidField_EMAIL() {

        loginAppUI(USER_LOGIN, USER_PASS);

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
    @Tags(value = {@Tag("smoke"), @Tag("normal"), @Tag("SP-T112")})
    @DisplayName("SP-T112: Adding new IB user with button \"Create one\" without required field - FIRST_NAME")
    public void testCreateIBUserInvalidField_FIRST_NAME() {

        loginAppUI(USER_LOGIN, USER_PASS);

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
    @Tags(value = {@Tag("smoke"), @Tag("normal"), @Tag("SP-T112")})
    @DisplayName("SP-T112: Adding new IB user with button \"Create one\" without required field - LAST_NAME")
    public void testCreateIBUserInvalidField_LAST_NAME() {

        loginAppUI(USER_LOGIN, USER_PASS);

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
    @Tags(value = {@Tag("smoke"), @Tag("normal"), @Tag("SP-T112")})
    @DisplayName("SP-T112: Adding new IB user with button \"Create one\" without required field - PASSWORD")
    public void testCreateIBUserInvalidField_PASSWORD() {

        loginAppUI(USER_LOGIN, USER_PASS);

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

        ElementsCollection passwordErrors = $$x("//span[contains(@class,'help-block-error')]");

        passwordErrors.shouldBe(CollectionCondition.size(1));

        waitForPageLoaded();
        assertThat(IBNextURLs.USERS_PAGE.equals(WebDriverRunner.getWebDriver().getCurrentUrl()))
                .isFalse().as("User has been created with a missed mandatory field - LAST_NAME");
    }
}
