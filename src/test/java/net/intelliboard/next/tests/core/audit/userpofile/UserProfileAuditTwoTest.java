package net.intelliboard.next.tests.core.audit.userpofile;

import io.qameta.allure.Feature;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.IBNextURLs;
import net.intelliboard.next.services.pages.IBUsers.IBUsersPage;
import net.intelliboard.next.services.pages.IBUsers.IBUsersRolesTypeEnum;
import net.intelliboard.next.services.pages.IBUsers.IBUsersSyncPage;
import net.intelliboard.next.services.pages.header.HeaderObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Tag("User_Audit")
@Feature("User Profile Audit")
public class UserProfileAuditTwoTest extends IBNextAbstractTest {

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T1362")})
    @DisplayName("SP-T1362: Displaying the 'Audit Logs' button only for the main user account")
    public void testAuditLogsButtonNotAllowedForSubUser() {

        open(IBNextURLs.USERS_PAGE);

        HeaderObject header = HeaderObject.init();

        header
                .openDropDownMenu()
                .openMyIBUsersPage()
                .openIBUserSyncPage()
                .selectLMSRole()
                .selectRole(IBUsersRolesTypeEnum.ALL_ACCESS)
                .selectFirstLMSUser();

        IBUsersSyncPage ibUsersSyncPage = IBUsersSyncPage.init();
        String selectedLMSUser =
                ibUsersSyncPage.getNameSelectedLMSUser().substring(0, 5);
        ibUsersSyncPage.syncUsers();

        open(IBNextURLs.USERS_PAGE);
        IBUsersPage.init()
                .changeScalingUsersPerPage(200)
                .logInSelectedUsers(selectedLMSUser);

        assertThat(
                HeaderObject
                        .init().openDropDownMenu()
                        .openMyAccountProfilePage()
                        .isAuditLogsButtonExist()
        )
                .withFailMessage("Audit Logs Button is exist")
                .isFalse();
    }
}
