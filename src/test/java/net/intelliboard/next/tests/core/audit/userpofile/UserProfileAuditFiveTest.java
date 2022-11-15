package net.intelliboard.next.tests.core.audit.userpofile;

import io.qameta.allure.Feature;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.IBNextURLs;
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
public class UserProfileAuditFiveTest extends IBNextAbstractTest {

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T1365")})
    @DisplayName("SP-T1365: Search logs by date")
    public void testUserProfileAuditPageSearchByDate() {

        open(IBNextURLs.USERS_PAGE);

        LocalDateTime yesterday = LocalDateTime.now().minusDays(1);
        LocalDateTime dayBeforeYesterday = LocalDateTime.now().minusDays(2);
        DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d MMMM yyyy");

        HeaderObject
                .init()
                .openDropDownMenu()
                .openMyAccountProfilePage()
                .openAuditLogs()
                .searchByDate(dayBeforeYesterday, yesterday);

        for (int i = 1; i < 4; i++) {

            String day = UserAuditLogsPage
                    .init()
                    .getValueCellByRowNumber(UserProfileAuditTableColumnEnum.TIME, i);

            String checkDay = yesterday.format(FORMATTER);
            assertThat(
                    day.contains(checkDay)
            )
                    .withFailMessage(String.format("Row # %s is not contain %s", i, yesterday.format(FORMATTER)))
                    .isTrue();
        }
    }
}
