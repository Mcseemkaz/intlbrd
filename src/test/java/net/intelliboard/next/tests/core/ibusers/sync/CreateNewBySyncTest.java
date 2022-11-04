package net.intelliboard.next.tests.core.ibusers.sync;

import io.qameta.allure.Feature;
import io.qameta.allure.Flaky;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.IBNextURLs;
import net.intelliboard.next.services.pages.IBUsers.IBUsersRolesTypeEnum;
import net.intelliboard.next.services.pages.IBUsers.IBUsersPage;
import net.intelliboard.next.services.pages.IBUsers.IBUsersSyncPage;
import net.intelliboard.next.services.pages.header.HeaderObject;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Feature("IBUser")
@Tag("IBUser")
public class CreateNewBySyncTest extends IBNextAbstractTest {

    @ParameterizedTest
    @EnumSource(value = IBUsersRolesTypeEnum.class)
    @Tags(value = {@Tag("smoke"), @Tag("critical"), @Tag("SP-T113")})
    @DisplayName("SP-T113: Adding new IB user via syncing")
    public void testAddingIBUserBySync(IBUsersRolesTypeEnum roles) {

        HeaderObject header = HeaderObject.init();

        header
                .openDropDownMenu()
                .openMyIBUsersPage()
                .openIBUserSyncPage()
                .selectLMSRole()
                .selectRole(roles)
                .selectFirstLMSUser();

        IBUsersSyncPage ibUsersSyncPage = IBUsersSyncPage.init();
        String selectedLMSUser =
                ibUsersSyncPage.getNameSelectedLMSUser().substring(0, 5);
        ibUsersSyncPage.syncUsers();

        open(IBNextURLs.USERS_PAGE);
        assertThat(IBUsersPage.init()
                .isUserPresents(selectedLMSUser))
                .isTrue();

        IBUsersPage.init()
                .changeScalingUsersPerPage(200)
                .checkedAllUsers()
                .deleteUser();
    }
    @Flaky
    @Test
    @Tags(value = {@Tag("smoke"), @Tag("high"), @Tag("SP-T121"), @Tag("health")})
    @DisplayName("SP-T121: Deleting synced user")
    public void testDeleteIBUserSynced() {

        HeaderObject header = HeaderObject.init();

        header
                .openDropDownMenu()
                .openMyIBUsersPage()
                .openIBUserSyncPage()
                .selectLMSRole()
                .selectRole(IBUsersRolesTypeEnum.ALL_ACCESS)
                .selectFirstLMSUser();

        IBUsersSyncPage ibUsersSyncPage = IBUsersSyncPage.init();
        String selectedLMSUser = ibUsersSyncPage.getNameSelectedLMSUser().substring(0, 5);
        ibUsersSyncPage.syncUsers();

        open(IBNextURLs.USERS_PAGE);
        IBUsersPage
                .init()
                .deleteUser(selectedLMSUser);

        assertThat(IBUsersPage.init()
                .isUserPresents(selectedLMSUser))
                .withFailMessage("User %s is not deleted", selectedLMSUser)
                .isFalse();
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Tags(value = {@Tag("smoke"), @Tag("high"), @Tag("SP-T122")})
    @DisplayName("SP-T122: Deleting several synced users")
    public void testDeleteSeveralIBUserSynced() {

        List<String> users = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            HeaderObject header = HeaderObject.init();

            header
                    .openDropDownMenu()
                    .openMyIBUsersPage()
                    .openIBUserSyncPage()
                    .selectLMSRole()
                    .selectRole(IBUsersRolesTypeEnum.ALL_ACCESS)
                    .selectFirstLMSUser();

            IBUsersSyncPage ibUsersSyncPage = IBUsersSyncPage.init();
            users.add(ibUsersSyncPage.getNameSelectedLMSUser());
            ibUsersSyncPage.syncUsers();
        }

        open(IBNextURLs.USERS_PAGE);
        IBUsersPage ibUsersPage = IBUsersPage.init();
        ibUsersPage.changeScalingUsersPerPage(200);
        users.forEach(ibUsersPage::checkedUserByName);
        IBUsersPage
                .init()
                .deleteSelectedUsersByActionDropdown();

        waitForPageLoaded();

        SoftAssertions softly = new SoftAssertions();

        for (String u : users) {
            softly.assertThat(IBUsersPage.init().isUserPresents(u))
                    .withFailMessage(String.format("User %s is not deleted", u))
                    .isFalse();
        }

        softly.assertAll();
    }

    @Test
    @Tags(value = {@Tag("smoke"), @Tag("high"), @Tag("SP-T384")})
    @DisplayName("SP-T384: Adding all available users by syncing")
    public void testAddAndDeleteAllIBUserSynced() {

        HeaderObject header = HeaderObject.init();

        header
                .openDropDownMenu()
                .openMyIBUsersPage()
                .openIBUserSyncPage()
                .selectLMSRole()
                .selectRole(IBUsersRolesTypeEnum.ALL_ACCESS)
                .selectAllLMSUser()
                .syncUsers();

        open(IBNextURLs.USERS_PAGE);
        IBUsersPage.init()
                .changeScalingUsersPerPage(200)
                .checkedAllUsers()
                .deleteSelectedUsersByActionDropdown();

        assertThat(IBUsersPage.init().areUsersPresents())
                .withFailMessage("Some users are presented in the table")
                .isTrue();
    }
}