package net.intelliboard.next.tests.core.audit;

import io.qameta.allure.Feature;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.IBNextURLs;
import net.intelliboard.next.services.helpers.DataGenerator;
import net.intelliboard.next.services.pages.IBUsers.CreateIBUsersFormFieldTypeEnum;
import net.intelliboard.next.services.pages.IBUsers.IBUsersRolesTypeEnum;
import net.intelliboard.next.services.pages.IBUsers.IBUsersPage;
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

        open(IBNextURLs.USERS_PAGE);

        HeaderObject
                .init().openDropDownMenu()
                .openMyAccountProfilePage()
                .openAuditLogs();
    }

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

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T1363")})
    @DisplayName("SP-T1363: Search logs by field")
    public void testUserProfileAuditPageSearchByField() {

        open(IBNextURLs.USERS_PAGE);

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

        open(IBNextURLs.USERS_PAGE);

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
    public void testUserProfileAuditPageSearchByDate() {

        open(IBNextURLs.USERS_PAGE);

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

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T1367")})
    @DisplayName("SP-T1367: Displaying new log of IB user after the log in of ib user")
    public void testIBUserLoginDisplayingInProfileAuditLog() {

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
        String selectedLMSUser = StringUtils.substringBefore(ibUsersSyncPage.getNameSelectedLMSUser(), "(");

        ibUsersSyncPage.syncUsers();

        open(IBNextURLs.USERS_PAGE);
        IBUsersPage.init()
                .changeScalingUsersPerPage(200)
                .logInSelectedUsers(selectedLMSUser);

        IBUserLoginNotificationAlertElement
                .init()
                .logOut();

        header
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
                .deleteUser(selectedLMSUser.substring(0, 5));
    }

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T1368")})
    @DisplayName("SP-T1368: Removing logs of user after deleting him")
    public void testIBUserAuditLogRemovingWhenHisDeleted() {

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
                .changeScalingUsersPerPage(200)
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
        IBUsersPage.init().deleteUser(firstName);

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

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T1371")})
    @DisplayName("SP-T1371: Sorting columns by clicking on the column's name")
    public void testAuditLogsSortingByColumns() {

        open(IBNextURLs.USERS_PAGE);

        HeaderObject
                .init().openDropDownMenu()
                .openMyAccountProfilePage()
                .openAuditLogs()
                .sortByColumn(UserProfileAuditTableColumnEnum.USER)
                .sortByColumn(UserProfileAuditTableColumnEnum.TIME)
                .sortByColumn(UserProfileAuditTableColumnEnum.EVENT_TYPE_PAGE)
                .sortByColumn(UserProfileAuditTableColumnEnum.IP)
                .sortByColumn(UserProfileAuditTableColumnEnum.BROWSER)
                .sortByColumn(UserProfileAuditTableColumnEnum.OS);
    }
}
