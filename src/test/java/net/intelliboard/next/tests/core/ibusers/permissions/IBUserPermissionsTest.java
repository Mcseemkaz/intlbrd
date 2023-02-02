package net.intelliboard.next.tests.core.ibusers.permissions;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Flaky;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.helpers.DataGenerator;
import net.intelliboard.next.services.pages.IBUsers.*;
import net.intelliboard.next.services.pages.connections.ConnectionsListPage;
import net.intelliboard.next.services.pages.connections.CreateConnectionPage;
import net.intelliboard.next.services.pages.connections.connection.ConnectionRolesBlockEnum;
import net.intelliboard.next.services.pages.connections.connection.ConnectionRolesMainEnum;
import net.intelliboard.next.services.pages.connections.connection.ConnectionRolesSettingsMainPage;
import net.intelliboard.next.services.pages.connections.connection.ConnectionTabsEnum;
import net.intelliboard.next.services.pages.header.HeaderObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static com.codeborne.selenide.Selenide.open;
import static net.intelliboard.next.services.IBNextURLs.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Feature("IBUser")
@Tag("IBUser")
class IBUserPermissionsTest extends IBNextAbstractTest {

    @Flaky
    @Test
    @Tags(value = {@Tag("smoke"), @Tag("high"), @Tag("SP-T612")})
    @DisplayName("SP-T612: Disappearing of buttons when admin permissions for IB user turn off")
    void testDisappearingButtonsAdminPermissionOff() {

        String firstName = "SP-T612_" + DataGenerator.getRandomString();
        String lastName = DataGenerator.getRandomString();

        open(USERS_PAGE);

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
                .submitUserCreateForm()
                .changeScalingUsersPerPage(200)
                .searchUserByName(firstName)
                .logInSelectedUsers(firstName);

        waitForPageLoaded();
    }

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T1404")})
    @DisplayName("SP-T1404: Adding Admin role to the Connection Role drop-down")
    void testAddingAdminRoleToConnectionRoleDropDown() throws InterruptedException {

        //Create a connection
        String connectionName = "SP-T1404_" + DataGenerator.getRandomString();
        open(CREATE_D2L_CONNECTION);
        CreateConnectionPage
                .init()
                .createD2LConnection(
                        connectionName,
                        CreateConnectionPage.D2L_URL,
                        CreateConnectionPage.D2L_CLIENT_ID,
                        CreateConnectionPage.D2L_CLIENT_SECRET,
                        CreateConnectionPage.D2L_USER_LOGIN,
                        CreateConnectionPage.D2L_USER_PASS);

        ConnectionsListPage connectionsListPage = ConnectionsListPage.init();

        //Process connection
        connectionsListPage
                .setActiveConnection(connectionName, true)
                .editConnection(connectionName)
                .processData()
                .waitingProcessingComplete()
                .backToConnectionMainPage()
                .openSettingsTab(ConnectionTabsEnum.ROLES_SETTINGS);

        // Add admin role
        ConnectionRolesSettingsMainPage
                .init()
                .setRoles(ConnectionRolesMainEnum.ALL, ConnectionRolesBlockEnum.ADMIN_ROLES)
                .saveConnectionSettings();

        // Check AdminRoles in IBUser Sync Page
        HeaderObject
                .init()
                .openDropDownMenu()
                .openMyIBUsersPage()
                .openIBUserSyncPage()
                .selectConnection(connectionName);

        assertThat(
                IBUsersSyncPage
                        .init()
                        .isRolesPresents("Admin Roles"))
                .withFailMessage("Admin Roles is not exist")
                .isTrue();
        // Delete connection
        open(ALL_CONNECTIONS);
        connectionsListPage.checkLastProcessing(connectionName, LocalDateTime.now());
        connectionsListPage.deleteConnection(connectionName);
    }
}
