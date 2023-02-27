package net.intelliboard.next.tests.core.audit.userpofile;

import io.qameta.allure.Feature;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.IBNextURLs;
import net.intelliboard.next.services.pages.IBUsers.IBUsersPage;
import net.intelliboard.next.services.pages.IBUsers.IBUsersRolesTypeEnum;
import net.intelliboard.next.services.pages.IBUsers.IBUsersSyncPage;
import net.intelliboard.next.services.pages.auditlogs.UserAuditLogsPage;
import net.intelliboard.next.services.pages.auditlogs.UserProfileAuditTableColumnEnum;
import net.intelliboard.next.services.pages.elements.IBUserLoginNotificationAlertElement;
import net.intelliboard.next.services.pages.header.HeaderObject;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Tag("User_Audit")
@Feature("User Profile Audit")
class UserProfileAuditSixTest extends IBNextAbstractTest {

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T1367")})
    @DisplayName("SP-T1367: Displaying new log of IB user after the log in of ib user")
    void testIBUserLoginDisplayingInProfileAuditLog() {

        open(IBNextURLs.USERS_PAGE);

        HeaderObject.init()
                .openDropDownMenu()
                .openMyIBUsersPage()
                .openIBUserSyncPage()
                .selectLMSRole()
                .selectRole(IBUsersRolesTypeEnum.ALL_ACCESS)
                .selectFirstLMSUser();

        String selectedLMSUser = StringUtils.substringBefore(IBUsersSyncPage.init().getNameSelectedLMSUser(), "(");

        IBUsersSyncPage
                .init()
                .syncUsers();

        open(IBNextURLs.USERS_PAGE);
        IBUsersPage.init()
                .searchUserByName(selectedLMSUser.substring(0, 5))
                .logInSelectedUsers(selectedLMSUser);

        IBUserLoginNotificationAlertElement
                .init()
                .logOut();

        HeaderObject
                .init()
                .openDropDownMenu()
                .openMyAccountProfilePage()
                .openAuditLogs()
                .searchByUser(selectedLMSUser);

        assertThat(
                UserAuditLogsPage
                        .init()
                        .getValueCellByRowNumber(UserProfileAuditTableColumnEnum.USER, 1)
                        .contains(selectedLMSUser))
                .withFailMessage(String.format("User %s not found in the list", selectedLMSUser))
                .isTrue();

        //Clean up
        open(IBNextURLs.USERS_PAGE);
        IBUsersPage.init()
                .searchUserByName(selectedLMSUser.substring(0, 5))
                .deleteUser(selectedLMSUser.substring(0, 5));
    }
}
