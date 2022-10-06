package net.intelliboard.next.tests.core.audit;

import io.qameta.allure.Feature;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.IBNextURLs;
import net.intelliboard.next.services.pages.IBUsers.CreateIBUsersFormRolesTypeEnum;
import net.intelliboard.next.services.pages.IBUsers.IBUsersPage;
import net.intelliboard.next.services.pages.IBUsers.IBUsersSyncPage;
import net.intelliboard.next.services.pages.auditlogs.UserAuditLogsPage;
import net.intelliboard.next.services.pages.auditlogs.UserProfileAuditTableColumnEnum;
import net.intelliboard.next.services.pages.header.HeaderObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Tag("User_Audit")
@Feature("User Profile Audit")
public class UserProfileAuditTest extends IBNextAbstractTest {

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T1361")})
    @DisplayName("SP-T1361: Displaying 'Audit Logs' page")
    public void testUserProfileAuditPageVerification() {

        HeaderObject
                .init().openDropDownMenu()
                .openMyAccountProfilePage()
                .openAuditLogs();
    }

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T1362")})
    @DisplayName("SP-T1362: Displaying the 'Audit Logs' button only for the main user account")
    public void testAuditLogsButtonNotAllowedForSubUser() {

        HeaderObject header = HeaderObject.init();

        header
                .openDropDownMenu()
                .openMyIBUsersPage()
                .openIBUserSyncPage()
                .selectLMSRole()
                .selectRole(CreateIBUsersFormRolesTypeEnum.ALL_ACCESS)
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

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T1363")})
    @DisplayName("SP-T1363: Search logs by field")
    public void testUserProfileAuditPageSearchByField() {

        String searchValue = "/data";

        HeaderObject
                .init().openDropDownMenu()
                .openMyAccountProfilePage()
                .openAuditLogs()
                .searchByField(searchValue);

        waitForPageLoaded();

        for (int i = 1; i < 4; i++) {
            assertThat(
                    UserAuditLogsPage
                            .init()
                            .getValueCellByRowNumber(UserProfileAuditTableColumnEnum.EVENT_TYPE_PAGE, i)
                            .contains(searchValue)
            )
                    .withFailMessage(String.format("Row # %s is not contain %s", i, UserProfileAuditTableColumnEnum.EVENT_TYPE_PAGE.value))
                    .isTrue();
        }

    }

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T1364")})
    @DisplayName("SP-T1364: Search logs by User")
    public void testUserProfileAuditPageSearchByUser() {

        String searchValue = "Automated Testing";

        HeaderObject
                .init().openDropDownMenu()
                .openMyAccountProfilePage()
                .openAuditLogs()
                .searchByUser(searchValue);

        for (int i = 1; i < 4; i++) {
            assertThat(
                    UserAuditLogsPage
                            .init()
                            .getValueCellByRowNumber(UserProfileAuditTableColumnEnum.USER, i)
                            .contains(searchValue)
            )
                    .withFailMessage(String.format("Row # %s is not contain %s", i, UserProfileAuditTableColumnEnum.USER.value))
                    .isTrue();
        }
    }

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T1365")})
    @DisplayName("SP-T1365: Search logs by date")
    public void testUserProfileAuditPageSearchByDate(){

        LocalDateTime yesterday = LocalDateTime.now().minusDays(1);
        DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d EEEE YYYY");

        HeaderObject
                .init().openDropDownMenu()
                .openMyAccountProfilePage()
                .openAuditLogs()
                .searchByDate(yesterday);

        for (int i = 1; i < 4; i++) {
            assertThat(
                    UserAuditLogsPage
                            .init()
                            .getValueCellByRowNumber(UserProfileAuditTableColumnEnum.TIME, i)
                            .contains(yesterday.format(FORMATTER))
            )
                    .withFailMessage(String.format("Row # %s is not contain %s", i, UserProfileAuditTableColumnEnum.USER.value))
                    .isTrue();
        }

    }
}
