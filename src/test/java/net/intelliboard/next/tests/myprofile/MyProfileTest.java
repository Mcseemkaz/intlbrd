package net.intelliboard.next.tests.myprofile;

import io.qameta.allure.Feature;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.helpers.DataGenerator;
import net.intelliboard.next.services.pages.IBUsers.IBUserPage;
import net.intelliboard.next.services.pages.header.HeaderObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Feature("My Profile")
@Tag("My_Profile")
public class MyProfileTest extends IBNextAbstractTest {

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T1625")})
    @DisplayName("SP-T1625: Edit First Name of the Main Account")
    public void testEditFirstNameMyProfile() {
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
    public void testEditLastNameMyProfile() {
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
}
