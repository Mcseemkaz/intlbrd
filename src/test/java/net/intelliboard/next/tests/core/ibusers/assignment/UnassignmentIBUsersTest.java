package net.intelliboard.next.tests.core.ibusers.assignment;

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

@Tag("Assignment_IBUser")
@Feature("Assignment IBUser")
public class UnassignmentIBUsersTest extends IBNextAbstractTest {

    @Test
    @DisplayName("SP-T845 Removing assignments from IB user on Blackboard")
    @Tags(value = {@Tag("smoke"), @Tag("high"), @Tag("SP-T845")})
    void testUnassignmentsIBUserBlackboard() {

        //Create IBUser
        String firstName = "SP-T845_" + DataGenerator.getRandomString();
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
        //Go to users -> Unassignments
        open(IBNextURLs.USERS_PAGE);

        IBUsersPage
                .init()
                .searchUserByName(firstName)
                .assignmentsUser(firstName)
                .dropdownConnectionSelect
                .selectOption(connectionName);

        IBUserAssignmentsPage
                .init()
                .removeFirstElementInBlock("Users")
                .removeFirstElementInBlock("Courses")
                .removeFirstElementInBlock("Terms")
                .removeFirstElementInBlock("Nodes");

        //Clean Up
        open(IBNextURLs.USERS_PAGE);
        IBUsersPage
                .init()
                .searchUserByName(firstName)
                .deleteUser(firstName);

    }

    @Test
    @DisplayName("SP-T846 Removing assignments from IB user on Canvas")
    @Tags(value = {@Tag("smoke"), @Tag("high"), @Tag("SP-T846")})
    void testUnassignmentsIBUserCanvas() {

        //Create IBUser
        String firstName = "SP-T846_" + DataGenerator.getRandomString();
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
                .addFirstElementInBlock("Course Categories");
        //Go to users -> Unassignments
        open(IBNextURLs.USERS_PAGE);

        IBUsersPage
                .init()
                .searchUserByName(firstName)
                .assignmentsUser(firstName)
                .dropdownConnectionSelect
                .selectOption(connectionName);

        IBUserAssignmentsPage
                .init()
                .removeFirstElementInBlock("Users")
                .removeFirstElementInBlock("Courses")
                .removeFirstElementInBlock("Terms")
                .removeFirstElementInBlock("Course Categories");

        //Clean Up
        open(IBNextURLs.USERS_PAGE);
        IBUsersPage
                .init()
                .searchUserByName(firstName)
                .deleteUser(firstName);

    }

    @Test
    @DisplayName("SP-T847 Removing assignments from IB user on Moodle")
    @Tags(value = {@Tag("smoke"), @Tag("high"), @Tag("SP-T847")})
    void testUnassignmentsIBUserMoodle() {

        //Create IBUser
        String firstName = "SP-T847_" + DataGenerator.getRandomString();
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
                .addFirstElementInBlock("Course Categories")
                .addFirstElementInBlock("Cohorts")
                .addFirstElementInBlock("Parent");

        //Go to users -> Unassignments
        open(IBNextURLs.USERS_PAGE);
        IBUsersPage
                .init()
                .searchUserByName(firstName)
                .assignmentsUser(firstName)
                .dropdownConnectionSelect
                .selectOption(connectionName);

        IBUserAssignmentsPage
                .init()
                .removeFirstElementInBlock("Users")
                .removeFirstElementInBlock("Courses")
                .removeFirstElementInBlock("Cohorts")
                .removeFirstElementInBlock("Parent")
                .removeFirstElementInBlock("Course Categories");

        //Clean Up
        open(IBNextURLs.USERS_PAGE);
        IBUsersPage
                .init()
                .searchUserByName(firstName)
                .deleteUser(firstName);
    }

    @Test
    @DisplayName("SP-T848 Removing assignments from IB user on D2L")
    @Tags(value = {@Tag("smoke"), @Tag("high"), @Tag("SP-T848")})
    void testUnassignmentsIBUserD2L() {

        //Create IBUser
        String firstName = "SP-T848_" + DataGenerator.getRandomString();
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
        //Assignments
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
                .addFirstElementInBlock("Org Units");

        //Go to users -> Unassignments
        open(IBNextURLs.USERS_PAGE);
        IBUsersPage
                .init()
                .searchUserByName(firstName)
                .assignmentsUser(firstName)
                .dropdownConnectionSelect
                .selectOption(connectionName);

        IBUserAssignmentsPage
                .init()
                .removeFirstElementInBlock("Users")
                .removeFirstElementInBlock("Courses")
                .removeFirstElementInBlock("Terms")
                .removeFirstElementInBlock("Org Units");

        //Clean Up
        open(IBNextURLs.USERS_PAGE);
        IBUsersPage
                .init()
                .searchUserByName(firstName)
                .deleteUser(firstName);
    }
}