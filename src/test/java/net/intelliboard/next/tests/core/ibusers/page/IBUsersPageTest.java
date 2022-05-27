package net.intelliboard.next.tests.core.ibusers.page;

import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.IBNextURLs;
import net.intelliboard.next.services.pages.IBUsers.CreateIBUsersFormRolesTypeEnum;
import net.intelliboard.next.services.pages.IBUsers.IBUsersPage;
import net.intelliboard.next.services.pages.header.HeaderObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class IBUsersPageTest extends IBNextAbstractTest {

    @Test
    @Tags(value = {@Tag("smoke"), @Tag("normal"), @Tag("SP-T116"), @Tag("health")})
    @DisplayName("SP-T116: Scaling the number of users")
    public void testScalingPerPageIBUsers() {

        HeaderObject header = HeaderObject.init();

        header
                .openDropDownMenu()
                .openMyIBUsersPage()
                .changeScalingUsersPerPage(25)
                .changeScalingUsersPerPage(50)
                .changeScalingUsersPerPage(100)
                .changeScalingUsersPerPage(200);
    }

    @Test
    @Tags(value = {@Tag("smoke"), @Tag("normal"), @Tag("SP-T115")})
    @DisplayName("SP-T116: Pagination at IB users page work correctly")
    public void testPaginationIBUsers() {

        HeaderObject header = HeaderObject.init();

        header
                .openDropDownMenu()
                .openMyIBUsersPage()
                .openIBUserSyncPage()
                .selectLMSRole()
                .selectRole(CreateIBUsersFormRolesTypeEnum.ALL_ACCESS)
                .selectAllLMSUser()
                .syncUsers();

        open(IBNextURLs.USERS_PAGE);
        IBUsersPage.init()
                .changePaginationPage("next")
                .changePaginationPage("prev")
                .changeScalingUsersPerPage(200)
                .checkedAllUsers()
                .deleteSelectedUsersByActionDropdown();

        assertThat(IBUsersPage.init().areUsersPresents())
                .isTrue()
                .as("Some users are presented in the table");
    }
}
