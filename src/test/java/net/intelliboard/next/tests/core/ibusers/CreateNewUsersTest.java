package net.intelliboard.next.tests.core.ibusers;

import com.codeborne.selenide.Selenide;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.helpers.DataGenerator;
import net.intelliboard.next.services.pages.IBUsers.CreateIBUsersFormFieldTypeEnum;
import net.intelliboard.next.services.pages.IBUsers.CreateIBUsersFormRolesTypeEnum;
import net.intelliboard.next.services.pages.IBUsers.IBUsersCreatePage;
import net.intelliboard.next.services.pages.header.HeaderObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

public class CreateNewUsersTest extends IBNextAbstractTest {

    @Test
    @Tags(value = {@Tag("smoke"), @Tag("normal"), @Tag("SP-T111")})
    @DisplayName("SP-T111: Adding new IB user with button \"Create one\"")
    public void testCreateNewIBUser() {

        loginAppUI(USER_LOGIN, USER_PASS);
        HeaderObject header = HeaderObject.init();

        String userName = DataGenerator.getRandomString();
        header
                .openDropDownMenu()
                .openMyIBUsersPage()
                .openIBUserCreatePage()
                .selectRole(CreateIBUsersFormRolesTypeEnum.TEACHER)
                .fillInField(CreateIBUsersFormFieldTypeEnum.EMAIL, DataGenerator.getRandomValidEmail())
                .fillInField(CreateIBUsersFormFieldTypeEnum.FIRST_NAME, userName)
                .fillInField(CreateIBUsersFormFieldTypeEnum.LAST_NAME, DataGenerator.getRandomString())
                .fillInField(CreateIBUsersFormFieldTypeEnum.JOB_TITLE, DataGenerator.getRandomString())
                .fillInField(CreateIBUsersFormFieldTypeEnum.PASSWORD, DataGenerator.getRandomValidPassword())
                .submitUserCreateForm();
    }
}
