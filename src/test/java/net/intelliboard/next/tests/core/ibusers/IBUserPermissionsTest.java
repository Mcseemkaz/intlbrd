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
import org.junit.jupiter.api.Test;

public class IBUserPermissionsTest extends IBNextAbstractTest {

    @Test
    @Tags(value = {@Tag("smoke"), @Tag("high"), @Tag("SP-T612")})
    @DisplayName("SP-T612: Disappearing of buttons when admin permissions for IB user turn off")
    public void testDisappearingButtonsAdminPermissionOff() throws InterruptedException {

        loginAppUI(USER_LOGIN, USER_PASS);
        HeaderObject header = HeaderObject.init();

        String firstName = DataGenerator.getRandomString();
        String lastName = DataGenerator.getRandomString();

        header
                .openDropDownMenu()
                .openMyIBUsersPage()
                .openIBUserCreatePage()
                .selectRole(CreateIBUsersFormRolesTypeEnum.ALL_ACCESS)
                .fillInField(CreateIBUsersFormFieldTypeEnum.EMAIL, DataGenerator.getRandomValidEmail())
                .fillInField(CreateIBUsersFormFieldTypeEnum.FIRST_NAME, firstName)
                .fillInField(CreateIBUsersFormFieldTypeEnum.LAST_NAME, lastName)
                .fillInField(CreateIBUsersFormFieldTypeEnum.JOB_TITLE, DataGenerator.getRandomString())
                .fillInField(CreateIBUsersFormFieldTypeEnum.PASSWORD, DataGenerator.getRandomValidPassword())
                .selectConnection()
                .submitUserCreateForm();


        IBUsersPage.init()
                .logInSelectedUsers(firstName);



        Thread.sleep(5000);

        //Clean Up
//        IBUsersPage.init().deleteUser(firstName);
    }
}
