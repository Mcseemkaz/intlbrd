package net.intelliboard.next.tests.builder.library;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Feature;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.IBNextURLs;
import net.intelliboard.next.services.helpers.DataGenerator;
import net.intelliboard.next.services.pages.IBUsers.*;
import net.intelliboard.next.services.pages.connections.ConnectionsTypeEnum;
import net.intelliboard.next.services.pages.elements.IBUserLoginNotificationAlertElement;
import net.intelliboard.next.services.pages.header.HeaderObject;
import net.intelliboard.next.services.pages.library.LibraryItemTypeEnum;
import net.intelliboard.next.services.pages.library.LibraryMainPage;
import net.intelliboard.next.services.pages.library.LibraryOrderTypeEnum;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static net.intelliboard.next.services.IBNextURLs.LIBRARY_MAIN;
import static net.intelliboard.next.services.IBNextURLs.USERS_PAGE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Feature("Library")
@Tag("Library")
public class LibraryMainTest extends IBNextAbstractTest {

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T428")})
    @DisplayName("SP-T428: Search on Library page - Name")
    void testSearchByNameLibraryPage() {

        String libraryItemName = "Needs Grading";

        open(LIBRARY_MAIN);

        LibraryMainPage libraryMainPage = LibraryMainPage.init();

        libraryMainPage.searchLibraryItem(libraryItemName);

        assertThat(libraryMainPage.getLibraryItemsNumberByType(LibraryItemTypeEnum.REPORTS) == 3)
                .withFailMessage("Number of found reports is not 3")
                .isTrue();
    }

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T428")})
    @DisplayName("SP-T428: Search on Library page - Tag")
    void testSearchByTagLibraryPage() {

        String libraryItemName = "Grading";

        open(LIBRARY_MAIN);

        LibraryMainPage libraryMainPage = LibraryMainPage.init();

        libraryMainPage.searchLibraryItem(libraryItemName);

        assertThat(libraryMainPage.getLibraryItemsNumberByType(LibraryItemTypeEnum.REPORTS) == 4)
                .withFailMessage("Number of found reports is not 3")
                .isTrue();
    }

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T444")})
    @DisplayName("SP-T444: Like in report")
    void testLikeReportLibraryPage() {
        String libraryItemName = "Grading";
        int likesBefore;
        int likesAfter;

        open(LIBRARY_MAIN);

        LibraryMainPage libraryMainPage = LibraryMainPage.init();

        libraryMainPage.searchLibraryItem(libraryItemName);

        likesBefore = libraryMainPage.getNumberOfLikesItem(libraryItemName);
        libraryMainPage.likeItem(libraryItemName);
        likesAfter = libraryMainPage.getNumberOfLikesItem(libraryItemName);

        assertThat(likesAfter == likesBefore + 1)
                .withFailMessage("Number of likes do not equal")
                .isTrue();

        libraryMainPage.unlikeItem(libraryItemName);

        likesAfter = libraryMainPage.getNumberOfLikesItem(libraryItemName);

        assertThat(likesAfter == likesBefore)
                .withFailMessage("Number of likes do not equal")
                .isTrue();
    }

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T438")})
    @DisplayName("SP-T438:  Order By in Library")
    void testOrderByLibraryPage() {

        open(LIBRARY_MAIN);
        String itemName;
        SoftAssertions softly = new SoftAssertions();
        LibraryMainPage libraryMainPage = LibraryMainPage.init();

        libraryMainPage.orderItemsBy(LibraryOrderTypeEnum.NAME);
        itemName = libraryMainPage.getLibraryItemName(LibraryItemTypeEnum.REPORTS, 1);
        softly.assertThat(itemName.startsWith("A"))
                .withFailMessage("Items has wrong order - selected item is %s", itemName)
                .isTrue();

        libraryMainPage.orderItemsBy(LibraryOrderTypeEnum.CREATION_DATE);
        itemName = libraryMainPage.getLibraryItemName(LibraryItemTypeEnum.REPORTS, 1);
        softly.assertThat(itemName.startsWith("C"))
                .withFailMessage("Items has wrong order - selected item is %s", itemName)
                .isTrue();

        libraryMainPage.orderItemsBy(LibraryOrderTypeEnum.LAST_UPDATE);
        itemName = libraryMainPage.getLibraryItemName(LibraryItemTypeEnum.REPORTS, 1);
        softly.assertThat(itemName.startsWith("I"))
                .withFailMessage("Items has wrong order - selected item is %s", itemName)
                .isTrue();

        libraryMainPage.orderItemsBy(LibraryOrderTypeEnum.MOST_VISITED);
        itemName = libraryMainPage.getLibraryItemName(LibraryItemTypeEnum.REPORTS, 1);
        softly.assertThat(itemName.startsWith("P"))
                .withFailMessage("Items has wrong order - selected item is %s", itemName)
                .isTrue();

        softly.assertAll();
    }

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T434")})
    @DisplayName("SP-T434:  Availability in 'View Active reports' in Library - CANVAS")
    void testAvailabilityItemsByConnectionTypeLibraryPageCANVAS() {
        open(LIBRARY_MAIN);
        LibraryMainPage libraryMainPage = LibraryMainPage.init();

        libraryMainPage.setActiveReportsForConnection(ConnectionsTypeEnum.CANVAS);


        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(libraryMainPage.getLibraryItemsNumberByType(LibraryItemTypeEnum.REPORTS) == 32)
                .withFailMessage("Reports size is mismatch %s %s", LibraryItemTypeEnum.REPORTS.value, 32)
                .isTrue();

        softly.assertThat(libraryMainPage.getLibraryItemsNumberByType(LibraryItemTypeEnum.DASHBOARDS) == 15)
                .withFailMessage("Reports size is mismatch %s %s", LibraryItemTypeEnum.DASHBOARDS.value, 15)
                .isTrue();

        softly.assertAll();
    }

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T434")})
    @DisplayName("SP-T434:  Availability in 'View Active reports' in Library - D2L")
    void testAvailabilityItemsByConnectionTypeLibraryPageD2L() {
        open(LIBRARY_MAIN);
        LibraryMainPage libraryMainPage = LibraryMainPage.init();

        libraryMainPage.setActiveReportsForConnection(ConnectionsTypeEnum.D2L);
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(libraryMainPage.getLibraryItemsNumberByType(LibraryItemTypeEnum.REPORTS) == 29)
                .withFailMessage("Reports size is mismatch %s %s", LibraryItemTypeEnum.REPORTS.value, 29)
                .isTrue();

        softly.assertThat(libraryMainPage.getLibraryItemsNumberByType(LibraryItemTypeEnum.DASHBOARDS) == 15)
                .withFailMessage("Reports size is mismatch %s %s", LibraryItemTypeEnum.DASHBOARDS.value, 15)
                .isTrue();

        softly.assertAll();
    }

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T434")})
    @DisplayName("SP-T434:  Availability in 'View Active reports' in Library - Moodle")
    void testAvailabilityItemsByConnectionTypeLibraryPageMoodle() {
        open(LIBRARY_MAIN);
        LibraryMainPage libraryMainPage = LibraryMainPage.init();

        libraryMainPage.setActiveReportsForConnection(ConnectionsTypeEnum.MOODLE);
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(libraryMainPage.getLibraryItemsNumberByType(LibraryItemTypeEnum.REPORTS) == 31)
                .withFailMessage("Reports size is mismatch %s %s", LibraryItemTypeEnum.REPORTS.value, 31)
                .isTrue();

        softly.assertThat(libraryMainPage.getLibraryItemsNumberByType(LibraryItemTypeEnum.DASHBOARDS) == 15)
                .withFailMessage("Reports size is mismatch %s %s", LibraryItemTypeEnum.DASHBOARDS.value, 15)
                .isTrue();

        softly.assertAll();
    }

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T430")})
    @DisplayName("SP-T430:  Search using tags in Library")
    void testSearchByTagLibrary() {

        String tagName = "Time";

        open(LIBRARY_MAIN);
        LibraryMainPage libraryMainPage = LibraryMainPage.init();
        libraryMainPage.searhByTag(tagName);

        assertThat(libraryMainPage.checkTagPresentsInItem(LibraryItemTypeEnum.REPORTS, 1, tagName))
                .isTrue();
    }

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T828")})
    @DisplayName("SP-T828:  The IB User does not see anything in the Library until the main account has assigned them some reports.")
    void testIBUserHasNoReportInLibraryInitially() {

        //Create IBUser
        String firstName = "SP-T828 " + DataGenerator.getRandomString();
        String lastName = DataGenerator.getRandomString();
        String reportsItem = "Page Views By Tools";

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
                .selectConnection()
                .selectDeselectAllItemFromLibraryByType(LibraryItemTypeEnum.REPORTS, IBUsersAssignedItemsAllOrNoneEnum.NONE)
                .selectDeselectAllItemFromLibraryByType(LibraryItemTypeEnum.DASHBOARDS, IBUsersAssignedItemsAllOrNoneEnum.NONE)
                .submitUserCreateForm()
                .changeScalingUsersPerPage(200)
                .logInSelectedUsers(firstName);

        IBUserPage
                .init()
                .acceptPolicy(IBUserPolicyEnum.PRIVACY_POLICY)
                .acceptPolicy(IBUserPolicyEnum.DATA_PROCESSING_ADDENDUM)
                .acceptPolicy(IBUserPolicyEnum.TERMS_OF_USE);

        //LogIn check that Library is empty
        open(LIBRARY_MAIN);

        assertThat(LibraryMainPage.init().getLibraryItemsNumberByType(LibraryItemTypeEnum.REPORTS) == 0)
                .withFailMessage("%s is not empty")
                .isTrue();

        assertThat(LibraryMainPage.init().getLibraryItemsNumberByType(LibraryItemTypeEnum.DASHBOARDS) == 0)
                .withFailMessage("%s is not empty")
                .isTrue();

        //Log back and save item for a IB user
        IBUserLoginNotificationAlertElement
                .init()
                .logOut();

        open(USERS_PAGE);

        IBUsersPage
                .init()
                .editSelectedUser(firstName)
                .selectItem(LibraryItemTypeEnum.REPORTS, reportsItem)
                .submitUserCreateForm();

        //Login IBUser and check that one item is presents
        IBUsersPage
                .init()
                .logInSelectedUsers(firstName);

        open(LIBRARY_MAIN);

        Selenide.sleep(2000);

        assertThat(LibraryMainPage.init().getLibraryItemsNumberByType(LibraryItemTypeEnum.REPORTS) == 1)
                .withFailMessage("%s is empty")
                .isTrue();

        //Log back and save item for a IB user
        IBUserLoginNotificationAlertElement
                .init()
                .logOut();

        //Clean Up
        open(IBNextURLs.USERS_PAGE);
        IBUsersPage
                .init()
                .deleteUser(firstName);
    }
}