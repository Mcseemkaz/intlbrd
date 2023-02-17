package net.intelliboard.next.tests.core.registration;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.IBNextURLs;
import net.intelliboard.next.services.api.connectors.MailService;
import net.intelliboard.next.services.api.connectors.MailServiceBuilder;
import net.intelliboard.next.services.helpers.DataGenerator;
import net.intelliboard.next.services.login.LoginService;
import net.intelliboard.next.services.pages.IBUsers.IBUserPage;
import net.intelliboard.next.services.pages.IBUsers.UserProfileSecuritySettings;
import net.intelliboard.next.services.pages.elements.enums.DateFormatEnum;
import net.intelliboard.next.services.pages.header.HeaderObject;
import net.intelliboard.next.services.pages.login.LoginPage;
import net.intelliboard.next.services.pages.signup.IBRegistrationConfirmationPage;
import net.intelliboard.next.services.pages.signup.SignUpFormFieldTypeEnum;
import net.intelliboard.next.services.pages.signup.WelcomePage;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.Duration;

import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Feature("Sign_Up")
@Tag("Sign_Up")
class UserRegistrationTest extends IBNextAbstractTest {

    private final String inviteCode = propertiesGetValue.getPropertyValue("invite_code");
    private final String prefixName = "AQA_";

    public UserRegistrationTest() throws IOException {
    }

    @Test
    @Tags(value = {@Tag("high"), @Tag("SP-T35"), @Tag("smoke"), @Tag("SP-T1316"), @Tag("smoke_core")})
    @DisplayName("SP-T35 SP-T1316: Successful user registration")
    void testUserSuccessRegistration() {

        String fullName = "SP-T35_" + DataGenerator.getRandomString();
        String email = DataGenerator.getRandomValidEmail();
        String password = DataGenerator.getRandomValidPassword();

        LoginService.clearCookiesAndRefresh();

        open(IBNextURLs.MAIN_URL);
        LoginPage.init()
                .goToRegistration()
                .fillInInviteCode(inviteCode)
                .continueRegistration()
                .fillInFormField(SignUpFormFieldTypeEnum.COUNTRY, "United States")
                .fillInFormField(SignUpFormFieldTypeEnum.FULL_NAME, fullName)
                .fillInFormField(SignUpFormFieldTypeEnum.EMAIL, email)
                .fillInFormField(SignUpFormFieldTypeEnum.PASSWORD, password)
                .fillInFormField(SignUpFormFieldTypeEnum.CONFIRM_PASSWORD, password)
                .fillInFormField(SignUpFormFieldTypeEnum.INSTITUTION, prefixName + DataGenerator.getRandomString())
                .fillInFormField(SignUpFormFieldTypeEnum.PHONE_NUMBER, DataGenerator.getRandomNumber())
                .agreeTermsPolicy()
                .submitForm();
    }

    @Test
    @Tags(value = {@Tag("high"), @Tag("SP-T1125"), @Tag("smoke"), @Tag("smoke_core")})
    @DisplayName("SP-T1125: Create an account")
    @Description("Check the success created of the new account")
    void testCreateAccount() {

        String fullName = "SP-T1125_" + DataGenerator.getRandomString();
        String password = DataGenerator.getRandomValidPassword();
        MailService mailService = MailServiceBuilder.build();

        String emailBoxName = mailService.generateNewMailBox();

        LoginService.clearCookiesAndRefresh();

        open(IBNextURLs.MAIN_URL);
        LoginPage.init()
                .goToRegistration()
                .fillInInviteCode(inviteCode)
                .continueRegistration()
                .fillInFormField(SignUpFormFieldTypeEnum.COUNTRY, "United States")
                .fillInFormField(SignUpFormFieldTypeEnum.FULL_NAME, fullName)
                .fillInFormField(SignUpFormFieldTypeEnum.EMAIL, emailBoxName)
                .fillInFormField(SignUpFormFieldTypeEnum.PASSWORD, password)
                .fillInFormField(SignUpFormFieldTypeEnum.CONFIRM_PASSWORD, password)
                .fillInFormField(SignUpFormFieldTypeEnum.INSTITUTION, prefixName + DataGenerator.getRandomString())
                .fillInFormField(SignUpFormFieldTypeEnum.PHONE_NUMBER, DataGenerator.getRandomNumber())
                .agreeTermsPolicy()
                .submitForm();

        String registrationURL = mailService.getRegistrationLink(emailBoxName);

        open(registrationURL);

        IBRegistrationConfirmationPage
                .init()
                .goToMyLogin()
                .fillInLoginFiled(emailBoxName)
                .fillInPassFiled(password)
                .submitForm();
    }

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T811")})
    @DisplayName("SP-T811: Integrations page is opened when user logs in first time")
    void testIntegrationsPageIsOpenedFirstTimeLogin() {

        String password = DataGenerator.getRandomValidPassword();
        String fullName = "SP-T811_" + DataGenerator.getRandomString();
        MailService mailService = MailServiceBuilder.build();

        String emailBoxName = mailService.generateNewMailBox();

        LoginService.clearCookiesAndRefresh();

        open(IBNextURLs.MAIN_URL);
        LoginPage.init()
                .goToRegistration()
                .fillInInviteCode(inviteCode)
                .continueRegistration()
                .fillInFormField(SignUpFormFieldTypeEnum.COUNTRY, "United States")
                .fillInFormField(SignUpFormFieldTypeEnum.FULL_NAME, fullName)
                .fillInFormField(SignUpFormFieldTypeEnum.EMAIL, emailBoxName)
                .fillInFormField(SignUpFormFieldTypeEnum.PASSWORD, password)
                .fillInFormField(SignUpFormFieldTypeEnum.CONFIRM_PASSWORD, password)
                .fillInFormField(SignUpFormFieldTypeEnum.INSTITUTION, prefixName + DataGenerator.getRandomString())
                .fillInFormField(SignUpFormFieldTypeEnum.PHONE_NUMBER, DataGenerator.getRandomNumber())
                .agreeTermsPolicy()
                .submitForm();

        String registrationURL = mailService.getRegistrationLink(emailBoxName);

        open(registrationURL);

        IBRegistrationConfirmationPage
                .init()
                .goToMyLogin()
                .fillInLoginFiled(emailBoxName)
                .fillInPassFiled(password)
                .submitForm();

        assertThat(
                WelcomePage
                        .init()
                        .isWelcomeMessageIsExist(fullName))
                .withFailMessage("Welcome message is not exist")
                .isTrue();
    }

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T1315"), @Tag("smoke"), @Tag("smoke_core")})
    @Description("Verify that a main user account is deleted successfully and is removed from the admin page.")
    @DisplayName("SP-T1315: Deleting a main user account")
    void testDeleteAccount() {

        String fullName = "SP-T81315_" + DataGenerator.getRandomString();
        String password = DataGenerator.getRandomValidPassword();
        MailService mailService = MailServiceBuilder.build();
        String emailBoxName = mailService.generateNewMailBox();

        LoginService.clearCookiesAndRefresh();

        open(IBNextURLs.MAIN_URL);
        LoginPage.init()
                .goToRegistration()
                .fillInInviteCode(inviteCode)
                .continueRegistration()
                .fillInFormField(SignUpFormFieldTypeEnum.COUNTRY, "United States")
                .fillInFormField(SignUpFormFieldTypeEnum.FULL_NAME, fullName)
                .fillInFormField(SignUpFormFieldTypeEnum.EMAIL, emailBoxName)
                .fillInFormField(SignUpFormFieldTypeEnum.PASSWORD, password)
                .fillInFormField(SignUpFormFieldTypeEnum.CONFIRM_PASSWORD, password)
                .fillInFormField(SignUpFormFieldTypeEnum.INSTITUTION, prefixName + DataGenerator.getRandomString())
                .fillInFormField(SignUpFormFieldTypeEnum.PHONE_NUMBER, DataGenerator.getRandomNumber())
                .agreeTermsPolicy()
                .submitForm();

        String registrationURL = mailService.getRegistrationLink(emailBoxName);

        open(registrationURL);

        IBRegistrationConfirmationPage
                .init()
                .goToMyLogin()
                .fillInLoginFiled(emailBoxName)
                .fillInPassFiled(password)
                .submitForm();

        Selenide.sleep(SLEEP_TIMEOUT_LONG);

        HeaderObject
                .init()
                .openDropDownMenu()
                .openMyAccountProfilePage()
                .deleteUserAccount();

        LoginPage
                .init()
                .fillInLoginFiled(emailBoxName)
                .fillInPassFiled(password)
                .getButtonSubmit()
                .click();

        LoginPage.init().getErrorMessageLogin().shouldBe(Condition.visible, Duration.ofSeconds(30));
    }

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T1132"), @Tag("smoke"), @Tag("smoke_core")})
    @Description("Verify that after changing and saving the new password the user will be able to go to the site")
    @DisplayName("SP-T1132: Change password")
    void testChangePassword() {

        String fullName = "SP-T1132_" + DataGenerator.getRandomString();
        String password = DataGenerator.getRandomValidPassword();
        String newPassword = DataGenerator.getRandomValidPassword();
        MailService mailService = MailServiceBuilder.build();

        String emailBoxName = mailService.generateNewMailBox();

        LoginService.clearCookiesAndRefresh();

        open(IBNextURLs.MAIN_URL);
        LoginPage.init()
                .goToRegistration()
                .fillInInviteCode(inviteCode)
                .continueRegistration()
                .fillInFormField(SignUpFormFieldTypeEnum.COUNTRY, "United States")
                .fillInFormField(SignUpFormFieldTypeEnum.FULL_NAME, fullName)
                .fillInFormField(SignUpFormFieldTypeEnum.EMAIL, emailBoxName)
                .fillInFormField(SignUpFormFieldTypeEnum.PASSWORD, password)
                .fillInFormField(SignUpFormFieldTypeEnum.CONFIRM_PASSWORD, password)
                .fillInFormField(SignUpFormFieldTypeEnum.INSTITUTION, prefixName + DataGenerator.getRandomString())
                .fillInFormField(SignUpFormFieldTypeEnum.PHONE_NUMBER, DataGenerator.getRandomNumber())
                .agreeTermsPolicy()
                .submitForm();

        String registrationURL = mailService.getRegistrationLink(emailBoxName);

        open(registrationURL);

        IBRegistrationConfirmationPage
                .init()
                .goToMyLogin()
                .fillInLoginFiled(emailBoxName)
                .fillInPassFiled(password)
                .submitForm();

        Selenide.sleep(SLEEP_TIMEOUT_LONG);

        open(IBNextURLs.USER_PROFILE_SECURITY_SETTINGS);

        UserProfileSecuritySettings
                .init()
                .setCurrentPassword(password)
                .setNewPassword(newPassword)
                .setConfirmNewPassword(newPassword)
                .saveSettings();

        HeaderObject
                .init()
                .openDropDownMenu()
                .logOut();

        Selenide.sleep(SLEEP_TIMEOUT_LONG);

        LoginService.clearCookiesAndRefresh();
        open(IBNextURLs.LOGIN_PAGE);

        LoginService
                .loginAppUI(emailBoxName, newPassword);
    }

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T1126"), @Tag("smoke"), @Tag("smoke_core")})
    @DisplayName("SP-T1126: Edit the user profile")
    @Description("Verify that the user can edit profile")
    void testEditUserProfile() {

        String password = DataGenerator.getRandomValidPassword();
        String fullName = "SP-T1126_" + DataGenerator.getRandomString();
        MailService mailService = MailServiceBuilder.build();

        String emailBoxName = mailService.generateNewMailBox();

        LoginService.clearCookiesAndRefresh();

        open(IBNextURLs.MAIN_URL);
        LoginPage.init()
                .goToRegistration()
                .fillInInviteCode(inviteCode)
                .continueRegistration()
                .fillInFormField(SignUpFormFieldTypeEnum.COUNTRY, "United States")
                .fillInFormField(SignUpFormFieldTypeEnum.FULL_NAME, fullName)
                .fillInFormField(SignUpFormFieldTypeEnum.EMAIL, emailBoxName)
                .fillInFormField(SignUpFormFieldTypeEnum.PASSWORD, password)
                .fillInFormField(SignUpFormFieldTypeEnum.CONFIRM_PASSWORD, password)
                .fillInFormField(SignUpFormFieldTypeEnum.INSTITUTION, prefixName + DataGenerator.getRandomString())
                .fillInFormField(SignUpFormFieldTypeEnum.PHONE_NUMBER, DataGenerator.getRandomNumber())
                .agreeTermsPolicy()
                .submitForm();

        String registrationURL = mailService.getRegistrationLink(emailBoxName);

        open(registrationURL);

        IBRegistrationConfirmationPage
                .init()
                .goToMyLogin()
                .fillInLoginFiled(emailBoxName)
                .fillInPassFiled(password)
                .submitForm();

        assertThat(
                WelcomePage
                        .init()
                        .isWelcomeMessageIsExist(fullName))
                .withFailMessage("Welcome message is not exist")
                .isTrue();

        open(IBNextURLs.USER_PROFILE);

        String changedFirst = "SP-T1126_" + DataGenerator.getRandomString();
        String changedLastName = DataGenerator.getRandomString();
        String zip = DataGenerator.getRandomNumber();
        String address = DataGenerator.getRandomNumber();
        String city = DataGenerator.getRandomNumber();

        IBUserPage ibUserPage = IBUserPage.init();

        ibUserPage
                .openEditProfilePage()
                .setFirstName(changedFirst)
                .setLastName(changedLastName)
                .setAddress(address)
                .setZIP(zip)
                .setCity(city)
                .setDateFormat(DateFormatEnum.YYYY_MM_DD)
                .submitForm();

        SoftAssertions softly = new SoftAssertions();

        softly.assertThat(ibUserPage.getFirstName().equals(changedFirst))
                .withFailMessage("User First Name is not changed : old full %s, new %s", fullName, changedFirst)
                .isTrue();

        softly.assertThat(ibUserPage.getLastName().equals(changedLastName))
                .withFailMessage("User Last Name is not changed : old full %s, new %s", fullName, changedLastName)
                .isTrue();

        softly.assertThat(ibUserPage.getAddress().equals(address))
                .withFailMessage("User Address is not valid : expt %s", address)
                .isTrue();

        softly.assertThat(ibUserPage.getZIP().equals(zip))
                .withFailMessage("User zip is not valid : expt %s", zip)
                .isTrue();

        softly.assertThat(ibUserPage.getCity().equals(city))
                .withFailMessage("User City is not valid : expt %s", city)
                .isTrue();

        softly.assertThat(ibUserPage.getDateFormat().equals(DateFormatEnum.YYYY_MM_DD.value))
                .withFailMessage("User Date Format is not valid : expt %s", DateFormatEnum.YYYY_MM_DD.value)
                .isTrue();

        softly.assertAll();
    }
}