package net.intelliboard.next.tests.core.ibusers.assignment;

import io.qameta.allure.Feature;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.helpers.DataGenerator;
import net.intelliboard.next.services.pages.IBUsers.CreateIBUsersFormFieldTypeEnum;
import net.intelliboard.next.services.pages.IBUsers.IBUsersPage;
import net.intelliboard.next.services.pages.IBUsers.IBUsersRolesTypeEnum;
import net.intelliboard.next.services.pages.header.HeaderObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("Assignment_IBUser")
@Feature("Assignment IBUser")
public class AssignmentIBUsersTest extends IBNextAbstractTest {

    @Test
    @Tags(value = {@Tag("smoke"), @Tag("high"), @Tag("SP-T837")})
    @DisplayName("SP-T837: Adding new assignment for user on Canvas")
    public void testAssignedIBUsers() {

        //Create User
        String firstName = "SP-T837_" + DataGenerator.getRandomString();
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
                .selectConnection("Automation D2L")
                .submitUserCreateForm();

        // Assertion
        assertThat(
                IBUsersPage
                        .init()
                        .searchUserByName(firstName)
                        .isUserPresents(firstName + " " + lastName))
                .withFailMessage("User with name %s is not existed", firstName)
                .isTrue();

        IBUsersPage
                .init()
                        .

        //Assign to connection CANVAS
        //Assign first item for each block + validate by popup

        //delete IBUser
        //Clean Up
        IBUsersPage
                .init()
                .deleteUser(firstName);

    }
}
