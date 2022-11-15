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

import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Tag("User_Audit")
@Feature("User Profile Audit")
public class UserProfileAuditThreeTest extends IBNextAbstractTest {

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
}
