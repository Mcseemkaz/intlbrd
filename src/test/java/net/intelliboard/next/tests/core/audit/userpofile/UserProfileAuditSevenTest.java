package net.intelliboard.next.tests.core.audit.userpofile;

import io.qameta.allure.Feature;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.IBNextURLs;
import net.intelliboard.next.services.helpers.DataGenerator;
import net.intelliboard.next.services.pages.IBUsers.CreateIBUsersFormFieldTypeEnum;
import net.intelliboard.next.services.pages.IBUsers.IBUsersPage;
import net.intelliboard.next.services.pages.IBUsers.IBUsersRolesTypeEnum;
import net.intelliboard.next.services.pages.auditlogs.UserAuditLogsPage;
import net.intelliboard.next.services.pages.auditlogs.UserProfileAuditTableColumnEnum;
import net.intelliboard.next.services.pages.elements.IBUserLoginNotificationAlertElement;
import net.intelliboard.next.services.pages.header.HeaderObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Tag("User_Audit")
@Feature("User Profile Audit")
class UserProfileAuditSevenTest extends IBNextAbstractTest {

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T1368")})
    @DisplayName("SP-T1368: Removing logs of user after deleting him")
    void testIBUserAuditLogRemovingWhenHisDeleted() {

        open(IBNextURLs.USERS_PAGE);

        // Add IBUser

        String firstName = "SP-T1368_" + DataGenerator.getRandomString();
        String lastName = DataGenerator.getRandomString();
        String fullName = firstName + " " + lastName;

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
                .selectConnection()
                .submitUserCreateForm();

        // Login into IBUser + do some action + Logout
        open(IBNextURLs.USERS_PAGE);
        IBUsersPage.init()
                .searchUserByName(firstName)
                .logInSelectedUsers(fullName);

        IBUserLoginNotificationAlertElement
                .init()
                .logOut();

        // Check AuditLogs

        HeaderObject
                .init()
                .openDropDownMenu()
                .openMyAccountProfilePage()
                .openAuditLogs()
                .searchByUser(fullName);

        assertThat(
                UserAuditLogsPage
                        .init()
                        .getValueCellByRowNumber(UserProfileAuditTableColumnEnum.USER, 1)
                        .contains(fullName))
                .withFailMessage(String.format("User %s not found in the list", fullName))
                .isTrue();

        // Delete IB User
        open(IBNextURLs.USERS_PAGE);
        IBUsersPage
                .init()
                .searchUserByName(firstName)
                .deleteUser(firstName);

        // Check that Audit logs do not exist

        HeaderObject
                .init()
                .openDropDownMenu()
                .openMyAccountProfilePage()
                .openAuditLogs()
                .searchByField(fullName);

        assertThat(
                UserAuditLogsPage
                        .init()
                        .isTableEmpty())
                .withFailMessage(String.format("User %s is in the list", fullName))
                .isTrue();
    }
}
