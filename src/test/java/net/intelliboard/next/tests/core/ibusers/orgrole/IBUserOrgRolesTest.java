package net.intelliboard.next.tests.core.ibusers.orgrole;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.IBNextURLs;
import net.intelliboard.next.services.helpers.DataGenerator;
import net.intelliboard.next.services.pages.IBUsers.IBUsersPage;
import net.intelliboard.next.services.pages.IBUsers.OrgRoleStatusEnum;
import net.intelliboard.next.services.pages.connections.ConnectionsTypeEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

@Tag("Organizational_Roles")
@Feature("Organizational roles")
class IBUserOrgRolesTest extends IBNextAbstractTest {

    @Test
    @Tags(value = {@Tag("smoke"), @Tag("normal"), @Tag("SP-T1090")})
    @DisplayName("SP-T1090: Add a new Organizational roles")
    @Description("Verify that new Organizational roles are successfully created")
    void testAddOrganizationRole() {

        String orgRoleName = "SP-T1090_" + DataGenerator.getRandomString();

        open(IBNextURLs.USERS_PAGE);

        IBUsersPage
                .init()
                .openOrgRolesPage()
                .openAddRole()
                .fillInOrgRoleName(orgRoleName)
                .setStatus(OrgRoleStatusEnum.ACTIVE)
                .selectConnection(ConnectionsTypeEnum.CANVAS.defaultName)
                .submitUserCreateForm()
                .deleteOrgRole(orgRoleName);
    }
}