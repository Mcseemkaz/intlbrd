package net.intelliboard.next.tests.core.ibusers;

import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.helpers.DataGenerator;
import net.intelliboard.next.services.pages.IBUsers.CreateIBUsersFormFieldTypeEnum;
import net.intelliboard.next.services.pages.IBUsers.CreateIBUsersFormRolesTypeEnum;
import net.intelliboard.next.services.pages.IBUsers.IBUsersPage;
import net.intelliboard.next.services.pages.header.HeaderObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;


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
        assertThat(IBUsersPage.init().getUserByName(firstName+" "+lastName))
                .isTrue()
                .as(String.format("User with name %s is not existed", firstName));

        //Clean Up
        IBUsersPage.init().deleteUser(firstName);
    }
}
