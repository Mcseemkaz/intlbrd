package net.intelliboard.next.tests.core.audit.userpofile;

import io.qameta.allure.Feature;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.IBNextURLs;
import net.intelliboard.next.services.pages.IBUsers.IBUserPage;
import net.intelliboard.next.services.pages.auditlogs.UserProfileAuditTableColumnEnum;
import net.intelliboard.next.services.pages.header.HeaderObject;
import net.intelliboard.next.services.pages.loginlogs.UserLoginLogsPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Tag("User_Audit")
@Feature("User Profile Audit")
public class UserProfileAuditEightTest extends IBNextAbstractTest {

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

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T1600")})
    @DisplayName("SP-T1600: New login is displayed in the Login Logs")
    public void testAuditLogsUserLogin() {

        open(IBNextURLs.USERS_PAGE);

        String userFirstName = HeaderObject
                .init()
                .openDropDownMenu()
                .openMyAccountProfilePage()
                .getFirstName();

        IBUserPage
                .init()
                .openLoginLogs();

        assertThat(
                UserLoginLogsPage
                        .init()
                        .isUserLoggedByDate(userFirstName, LocalDateTime.now().minusDays(1)))
                .withFailMessage(String.format("User %s has no info about login today", userFirstName))
                .isTrue();
    }
}