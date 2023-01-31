package net.intelliboard.next.tests.core.ibusers.imports;

import io.qameta.allure.Feature;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.IBNextURLs;
import net.intelliboard.next.services.ProjectFilesEnum;
import net.intelliboard.next.services.pages.IBUsers.IBUsersRolesTypeEnum;
import net.intelliboard.next.services.pages.IBUsers.IBUsersPage;
import net.intelliboard.next.services.pages.connections.ConnectionsTypeEnum;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Tag("IBUser")
@Feature("IBUser")
class ImportIBUserTest extends IBNextAbstractTest {

    @ParameterizedTest
    @EnumSource(value = ConnectionsTypeEnum.class, names = {"CANVAS", "D2L", "TOTARA"})
    @Tags(value = {@Tag("smoke"), @Tag("normal"), @Tag("SP-T124"), @Tag("1047"), @Tag("smoke_core")})
    @DisplayName("SP-T124 SP-T1047: Adding new IB user with button \"Import\"")
    void testCreateIBUserByImport(ConnectionsTypeEnum connection) {

        String importedIBUser = "AQA Import";
        open(IBNextURLs.USERS_PAGE);

        IBUsersPage
                .init()
                .openIBUserImportPage()
                .selectLMS(connection)
                .selectRole(IBUsersRolesTypeEnum.ALL_ACCESS)
                .selectConnection(connection.defaultName)
                .uploadImportCSVFile(ProjectFilesEnum.IBUSERS_IMPORT_CSV)
                .submitForm();

        assertThat(IBUsersPage
                .init()
                .searchUserByName(importedIBUser)
                .isUserPresents(importedIBUser))
                .withFailMessage("User %s is not imported", importedIBUser)
                .isTrue();

        // Clean Up delete user
        IBUsersPage
                .init()
                .deleteUser(importedIBUser);

        // Assertion
        Assertions.assertThat(
                        IBUsersPage
                                .init()
                                .changeScalingUsersPerPage(200)
                                .isUserPresents(importedIBUser))
                .withFailMessage("User with name %s is existed and isn't deleted", importedIBUser)
                .isFalse();
    }
}