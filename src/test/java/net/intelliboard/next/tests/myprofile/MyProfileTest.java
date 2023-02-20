package net.intelliboard.next.tests.myprofile;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Feature;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.IBNextURLs;
import net.intelliboard.next.services.helpers.DataGenerator;
import net.intelliboard.next.services.helpers.UnitedStatesListEnum;
import net.intelliboard.next.services.pages.IBUsers.IBUserPage;
import net.intelliboard.next.services.pages.connections.ConnectionsListPage;
import net.intelliboard.next.services.pages.header.HeaderObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Feature("My Profile")
@Tag("My_Profile")
class MyProfileTest extends IBNextAbstractTest {

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T1625")})
    @DisplayName("SP-T1625: Edit First Name of the Main Account")
    void testEditFirstNameMyProfile() {
        HeaderObject
                .init()
                .openDropDownMenu()
                .openMyAccountProfilePage();

        IBUserPage ibUserPage = IBUserPage.init();

        String initialFirstName = ibUserPage.getFirstName();
        String changedFirstName = "SP-T1625" + DataGenerator.getRandomString();

        ibUserPage
                .openEditProfilePage()
                .setFirstName(changedFirstName)
                .submitForm();

        assertThat(
                changedFirstName).isEqualTo(ibUserPage.getFirstName());

        //Revert changes
        ibUserPage
                .openEditProfilePage()
                .setFirstName(initialFirstName)
                .submitForm();
    }

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T1626")})
    @DisplayName("SP-T1626: Edit Last Name of the Main Account")
    void testEditLastNameMyProfile() {
        HeaderObject
                .init()
                .openDropDownMenu()
                .openMyAccountProfilePage();

        IBUserPage ibUserPage = IBUserPage.init();

        String initialLastName = ibUserPage.getLastName();
        String changedLastName = "SP-T1626" + DataGenerator.getRandomString();

        ibUserPage
                .openEditProfilePage()
                .setLastName(changedLastName)
                .submitForm();

        assertThat(
                changedLastName).isEqualTo(ibUserPage.getLastName());

        //Revert changes
        ibUserPage
                .openEditProfilePage()
                .setLastName(initialLastName)
                .submitForm();
    }

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T1628")})
    @DisplayName("SP-T1628: Edit City of the Main Account")
    void testEditCityMyProfile() {
        HeaderObject
                .init()
                .openDropDownMenu()
                .openMyAccountProfilePage();

        IBUserPage ibUserPage = IBUserPage.init();

        String initialCity = ibUserPage.getCity();
        String changedCity = "SP-T1628" + DataGenerator.getRandomString();

        ibUserPage
                .openEditProfilePage()
                .setCity(changedCity)
                .submitForm();

        assertThat(
                changedCity).isEqualTo(ibUserPage.getCity());

        //Revert changes
        ibUserPage
                .openEditProfilePage()
                .setCity(initialCity)
                .submitForm();
    }

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T1629")})
    @DisplayName("SP-T1629: Edit ZIP code of the Main Account")
    void testEditZIPMyProfile() {
        HeaderObject
                .init()
                .openDropDownMenu()
                .openMyAccountProfilePage();

        IBUserPage ibUserPage = IBUserPage.init();

        String initialZIP = ibUserPage.getZIP();
        String changedZIP = "SP-T1629" + DataGenerator.getRandomNumber();

        ibUserPage
                .openEditProfilePage()
                .setZIP(changedZIP)
                .submitForm();

        assertThat(
                changedZIP).isEqualTo(ibUserPage.getZIP());

        //Revert changes
        ibUserPage
                .openEditProfilePage()
                .setZIP(initialZIP)
                .submitForm();
    }

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T1627")})
    @DisplayName("SP-T1627: Edit Address of the Main Account")
    void testEditAddressMyProfile() {
        HeaderObject
                .init()
                .openDropDownMenu()
                .openMyAccountProfilePage();

        IBUserPage ibUserPage = IBUserPage.init();

        String initialAddress = ibUserPage.getAddress();
        String changedAddress = "SP-T1627" + DataGenerator.getRandomNumber();

        ibUserPage
                .openEditProfilePage()
                .setAddress(changedAddress)
                .submitForm();

        assertThat(
                changedAddress).isEqualTo(ibUserPage.getAddress());

        //Revert changes
        ibUserPage
                .openEditProfilePage()
                .setAddress(initialAddress)
                .submitForm();
    }

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T1630")})
    @DisplayName("SP-T1630: Edit State of the Main Account")
    void testEditStateMyProfile() {
        HeaderObject
                .init()
                .openDropDownMenu()
                .openMyAccountProfilePage();

        IBUserPage ibUserPage = IBUserPage.init();

        UnitedStatesListEnum initialState = UnitedStatesListEnum.CA;
        UnitedStatesListEnum changedState = DataGenerator.getRandomUSState();

        ibUserPage
                .openEditProfilePage()
                .setState(changedState)
                .submitForm();

        Selenide.sleep(SLEEP_TIMEOUT_LONG);

        assertThat(changedState.shortName)
                .isEqualTo(ibUserPage.getState());

        //Revert changes
        ibUserPage
                .openEditProfilePage()
                .setState(initialState)
                .submitForm();
    }

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T1638")})
    @DisplayName("SP-T1638: Check if amount of connections on My Profile page match with amount of connections on Connection page")
    void testCheckAmountConnectionMyProfile() {

        open(IBNextURLs.ALL_CONNECTIONS + IBNextURLs.PER_PAGE_500);

        int numberConnections = ConnectionsListPage.init().getNumberConnections();

        HeaderObject
                .init()
                .openDropDownMenu()
                .openMyAccountProfilePage();

        IBUserPage ibUserPage = IBUserPage.init();

        assertThat(
                numberConnections).isEqualTo(ibUserPage.getConnectionsNumber());
    }
}
