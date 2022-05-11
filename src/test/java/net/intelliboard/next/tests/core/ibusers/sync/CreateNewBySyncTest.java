package net.intelliboard.next.tests.core.ibusers.sync;

import com.codeborne.selenide.Selenide;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.IBNextURLs;
import net.intelliboard.next.services.pages.IBUsers.CreateIBUsersFormRolesTypeEnum;
import net.intelliboard.next.services.pages.IBUsers.IBUsersPage;
import net.intelliboard.next.services.pages.IBUsers.IBUsersSyncPage;
import net.intelliboard.next.services.pages.header.HeaderObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static com.codeborne.selenide.Selenide.open;

public class CreateNewBySyncTest extends IBNextAbstractTest {


    @ParameterizedTest
    @EnumSource(value = CreateIBUsersFormRolesTypeEnum.class)
    @Tags(value = {@Tag("smoke"), @Tag("critical"), @Tag("SP-T113")})
    @DisplayName("SP-T113: Adding new IB user via syncing")
    public void testAddingIBUserBySync(CreateIBUsersFormRolesTypeEnum roles) {

        loginAppUI(USER_LOGIN, USER_PASS);
        HeaderObject header = HeaderObject.init();

        header
                .openDropDownMenu()
                .openMyIBUsersPage()
                .openIBUserSyncPage()
                .selectConnection("Automation Canvas")
                .selectLMSRole("Teacher")
                .selectRole(roles)
                .selectFirstLMSUser();

        IBUsersSyncPage ibUsersSyncPage = IBUsersSyncPage.init();
        String selectedLMSUser = ibUsersSyncPage.getNameSelectedLMSUser();
        ibUsersSyncPage.syncUsers();

        open(IBNextURLs.USERS_PAGE);
        IBUsersPage.init()
                .getUserByName(selectedLMSUser);

        Selenide.screenshot(System.currentTimeMillis() + "test.jpg");
    }
}
