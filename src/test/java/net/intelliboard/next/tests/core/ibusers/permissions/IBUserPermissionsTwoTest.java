package net.intelliboard.next.tests.core.ibusers.permissions;

import io.qameta.allure.Feature;
import io.qameta.allure.Flaky;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.IBNextURLs;
import net.intelliboard.next.services.helpers.DataGenerator;
import net.intelliboard.next.services.pages.IBUsers.CreateIBUsersFormFieldTypeEnum;
import net.intelliboard.next.services.pages.IBUsers.IBUserPage;
import net.intelliboard.next.services.pages.IBUsers.IBUserPolicyEnum;
import net.intelliboard.next.services.pages.IBUsers.IBUsersRolesTypeEnum;
import net.intelliboard.next.services.pages.header.HeaderObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static net.intelliboard.next.services.IBNextURLs.MY_INTELLIBOARD_PAGE;


@Feature("IBUser")
@Tag("IBUser")
public class IBUserPermissionsTwoTest extends IBNextAbstractTest {
    @Flaky
    @Test
    @Tags(value = {@Tag("high"), @Tag("SP-T614")})
    @DisplayName("SP-T614: Library permission work correctly when Report Builder permission is turn off")
    public void testLibraryPermissionOnWhenBuilderPermissionOff() {

        //Create new user
        String firstName = "SP-T614_" + DataGenerator.getRandomString();
        String lastName = DataGenerator.getRandomString();

        open(MY_INTELLIBOARD_PAGE);

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
                .logInSelectedUsers(firstName);

        IBUserPage
                .init()
                .acceptPolicy(IBUserPolicyEnum.PRIVACY_POLICY)
                .acceptPolicy(IBUserPolicyEnum.TERMS_OF_USE);

        open(IBNextURLs.LIBRARY_MAIN);

        //TODO [MO] after fix bug with empty Library page should add validate of default reporting availability
        waitForPageLoaded();
    }
}
