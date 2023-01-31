package net.intelliboard.next.tests.core.ibusers.assignment;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.IBNextURLs;
import net.intelliboard.next.services.helpers.DataGenerator;
import net.intelliboard.next.services.pages.IBUsers.CreateIBUsersFormFieldTypeEnum;
import net.intelliboard.next.services.pages.IBUsers.IBUserAssignmentsPage;
import net.intelliboard.next.services.pages.IBUsers.IBUsersPage;
import net.intelliboard.next.services.pages.IBUsers.IBUsersRolesTypeEnum;
import net.intelliboard.next.services.pages.header.HeaderObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static net.intelliboard.next.services.pages.connections.ConnectionsTypeEnum.*;
import static org.assertj.core.api.Assertions.assertThat;

@Tag("Assignment_IBUser")
@Feature("Assignment IBUser")
class AssignmentIBUsersTest extends IBNextAbstractTest {

    @Test
    @Tags(value = {@Tag("smoke"), @Tag("high"), @Tag("SP-T837"), @Tag("smoke_core")})
    @DisplayName("SP-T837: Adding new assignment for user CANVAS")
    void testAssignedIBUsersCANVAS() {

        //Create User
        String firstName = "SP-T837_" + DataGenerator.getRandomString();
        String lastName = DataGenerator.getRandomString();
        String connectionName = CANVAS.defaultName;

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
                .selectConnection(connectionName)
                .submitUserCreateForm();

        // Assertion
        assertThat(
                IBUsersPage
                        .init()
                        .searchUserByName(firstName)
                        .isUserPresents(firstName + " " + lastName))
                .withFailMessage("User with name %s is not existed", firstName)
                .isTrue();

        //Assign to connection CANVAS
        IBUsersPage
                .init()
                .searchUserByName(firstName)
                .assignmentsUser(firstName)
                .dropdownConnectionSelect
                .selectOption(connectionName);

        //Assign first item for each block + validate by popup
        IBUserAssignmentsPage
                .init()
                .addFirstElementInBlock("Users")
                .addFirstElementInBlock("Courses")
                .addFirstElementInBlock("Terms")
                .addFirstElementInBlock("Course Categories");

        //Clean Up
        open(IBNextURLs.USERS_PAGE);
        IBUsersPage
                .init()
                .searchUserByName(firstName)
                .deleteUser(firstName);
    }

    @Test
    @Tags(value = {@Tag("smoke"), @Tag("high"), @Tag("SP-T838"), @Tag("smoke_core")})
    @DisplayName("SP-T838: Adding new assignment for user D2L")
    void testAssignedIBUsersD2L() {

        //Create User
        String firstName = "SP-T838_" + DataGenerator.getRandomString();
        String lastName = DataGenerator.getRandomString();
        String connectionName = D2L.defaultName;

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
                .selectConnection(connectionName)
                .submitUserCreateForm();

        // Assertion
        assertThat(
                IBUsersPage
                        .init()
                        .searchUserByName(firstName)
                        .isUserPresents(firstName + " " + lastName))
                .withFailMessage("User with name %s is not existed", firstName)
                .isTrue();

        //Assign to connection CANVAS
        IBUsersPage
                .init()
                .searchUserByName(firstName)
                .assignmentsUser(firstName)
                .dropdownConnectionSelect
                .selectOption(connectionName);

        //Assign first item for each block + validate by popup
        IBUserAssignmentsPage
                .init()
                .addFirstElementInBlock("Users")
                .addFirstElementInBlock("Courses")
                .addFirstElementInBlock("Terms")
                .addFirstElementInBlock("Org Units");

        //Clean Up
        open(IBNextURLs.USERS_PAGE);
        IBUsersPage
                .init()
                .searchUserByName(firstName)
                .deleteUser(firstName);
    }

    @Test
    @Tags(value = {@Tag("smoke"), @Tag("high"), @Tag("SP-T837")})
    @DisplayName("SP-T837: Adding new assignment for user TOTARA")
    void testAssignedIBUsersTOTARA() {

        //Create User
        String firstName = "SP-T837_" + DataGenerator.getRandomString();
        String lastName = DataGenerator.getRandomString();
        String connectionName = TOTARA.defaultName;

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
                .selectConnection(connectionName)
                .submitUserCreateForm();

        // Assertion
        assertThat(
                IBUsersPage
                        .init()
                        .searchUserByName(firstName)
                        .isUserPresents(firstName + " " + lastName))
                .withFailMessage("User with name %s is not existed", firstName)
                .isTrue();

        //Assign to connection CANVAS
        IBUsersPage
                .init()
                .searchUserByName(firstName)
                .assignmentsUser(firstName)
                .dropdownConnectionSelect
                .selectOption(connectionName);

        //Assign first item for each block + validate by popup
        IBUserAssignmentsPage
                .init()
                .addFirstElementInBlock("Users")
                .addFirstElementInBlock("Courses")
                .addFirstElementInBlock("Course Categories")
                .addFirstElementInBlock("Cohorts")
                .addFirstElementInBlock("Parent");

        //Clean Up
        open(IBNextURLs.USERS_PAGE);
        IBUsersPage
                .init()
                .searchUserByName(firstName)
                .deleteUser(firstName);
    }

    @Test
    @Tags(value = {@Tag("smoke"), @Tag("high"), @Tag("SP-T831"), @Tag("smoke_core")})
    @DisplayName("SP-T831: Adding new assignment for user MOODLE")
    void testAssignedIBUsersMOODLE() {

        //Create User
        String firstName = "SP-T831_" + DataGenerator.getRandomString();
        String lastName = DataGenerator.getRandomString();
        String connectionName = MOODLE.defaultName;

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
                .selectConnection(connectionName)
                .submitUserCreateForm();

        // Assertion
        assertThat(
                IBUsersPage
                        .init()
                        .searchUserByName(firstName)
                        .isUserPresents(firstName + " " + lastName))
                .withFailMessage("User with name %s is not existed", firstName)
                .isTrue();

        //Assign to connection CANVAS
        IBUsersPage
                .init()
                .searchUserByName(firstName)
                .assignmentsUser(firstName)
                .dropdownConnectionSelect
                .selectOption(connectionName);

        //Assign first item for each block + validate by popup
        IBUserAssignmentsPage
                .init()
                .addFirstElementInBlock("Users")
                .addFirstElementInBlock("Courses")
                .addFirstElementInBlock("Course Categories")
                .addFirstElementInBlock("Cohorts")
                .addFirstElementInBlock("Parent");

        //Clean Up
        open(IBNextURLs.USERS_PAGE);
        IBUsersPage
                .init()
                .searchUserByName(firstName)
                .deleteUser(firstName);
    }

    @Test
    @Tags(value = {@Tag("smoke"), @Tag("high"), @Tag("SP-T832"), @Tag("smoke_core@")})
    @DisplayName("SP-T832: Adding new assignment for user BlackBoard")
    void testAssignedIBUsersBLACKBOARD() {

        //Create User
        String firstName = "SP-T832_" + DataGenerator.getRandomString();
        String lastName = DataGenerator.getRandomString();
        String connectionName = BLACKBOARD.defaultName;

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
                .selectConnection(connectionName)
                .submitUserCreateForm();

        // Assertion
        assertThat(
                IBUsersPage
                        .init()
                        .searchUserByName(firstName)
                        .isUserPresents(firstName + " " + lastName))
                .withFailMessage("User with name %s is not existed", firstName)
                .isTrue();

        //Assign to connection BlackBoard
        IBUsersPage
                .init()
                .searchUserByName(firstName)
                .assignmentsUser(firstName)
                .dropdownConnectionSelect
                .selectOption(connectionName);

        //Assign first item for each block + validate by popup
        IBUserAssignmentsPage
                .init()
                .addFirstElementInBlock("Users")
                .addFirstElementInBlock("Courses")
                .addFirstElementInBlock("Terms")
                .addFirstElementInBlock("Nodes");

        //Clean Up
        open(IBNextURLs.USERS_PAGE);
        IBUsersPage
                .init()
                .searchUserByName(firstName)
                .deleteUser(firstName);
    }

    @Test
    @Tags(value = {@Tag("smoke"), @Tag("normal"), @Tag("SP-T1062"), @Tag("smoke_core")})
    @DisplayName("SP-T1062: Adding new assignment for user on ilias")
    @Description("Verify that any assignment can be added on ilias")
    void testAssignedIBUsersILIAS() {

        //Create User
        String firstName = "SP-T1062_" + DataGenerator.getRandomString();
        String lastName = DataGenerator.getRandomString();
        String connectionName = ILIAS.defaultName;

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
                .selectConnection(connectionName)
                .submitUserCreateForm();

        // Assertion
        assertThat(
                IBUsersPage
                        .init()
                        .searchUserByName(firstName)
                        .isUserPresents(firstName + " " + lastName))
                .withFailMessage("User with name %s is not existed", firstName)
                .isTrue();

        //Assign to connection BlackBoard
        IBUsersPage
                .init()
                .searchUserByName(firstName)
                .assignmentsUser(firstName)
                .dropdownConnectionSelect
                .selectOption(connectionName);

        //Assign first item for each block + validate by popup
        IBUserAssignmentsPage
                .init()
                .addFirstElementInBlock("Users")
                .addFirstElementInBlock("Courses")
                .addFirstElementInBlock("Course Categories");

        //Clean Up
        open(IBNextURLs.USERS_PAGE);
        IBUsersPage
                .init()
                .searchUserByName(firstName)
                .deleteUser(firstName);
    }
}
