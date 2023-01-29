package net.intelliboard.next.tests.core.registration;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.IBNextURLs;
import net.intelliboard.next.services.PropertiesGetValue;
import net.intelliboard.next.services.api.connectors.MailService;
import net.intelliboard.next.services.api.connectors.mailtramp.MailTrapServiceImpl;
import net.intelliboard.next.services.api.connectors.onesecmail.OneSecMailServiceImpl;
import net.intelliboard.next.services.helpers.DataGenerator;
import net.intelliboard.next.services.login.LoginService;
import net.intelliboard.next.services.pages.header.HeaderObject;
import net.intelliboard.next.services.pages.login.LoginPage;
import net.intelliboard.next.services.pages.signup.IBRegistrationConfirmationPage;
import net.intelliboard.next.services.pages.signup.SignUpFormFieldTypeEnum;
import net.intelliboard.next.services.pages.signup.WelcomePage;
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

    public UserRegistrationTest() throws IOException {
    }

    @Test
    @Tags(value = {@Tag("high"), @Tag("SP-T35"), @Tag("smoke")})
    @DisplayName("SP-T35: Successful user registration")
    void testUserSuccessRegistration() {

        String email = DataGenerator.getRandomValidEmail();
        String password = DataGenerator.getRandomValidPassword();

        LoginService.clearCookiesAndRefresh();

        open(IBNextURLs.MAIN_URL);
        LoginPage.init()
                .goToRegistration()
                .fillInInviteCode(inviteCode)
                .continueRegistration()
                .fillInFormField(SignUpFormFieldTypeEnum.COUNTRY, "United States")
                .fillInFormField(SignUpFormFieldTypeEnum.FULL_NAME, DataGenerator.getRandomString())
                .fillInFormField(SignUpFormFieldTypeEnum.EMAIL, email)
                .fillInFormField(SignUpFormFieldTypeEnum.PASSWORD, password)
                .fillInFormField(SignUpFormFieldTypeEnum.CONFIRM_PASSWORD, password)
                .fillInFormField(SignUpFormFieldTypeEnum.INSTITUTION, DataGenerator.getRandomString())
                .fillInFormField(SignUpFormFieldTypeEnum.PHONE_NUMBER, DataGenerator.getRandomNumber())
                .agreeTermsPolicy()
                .submitForm();
    }

    @Test
    @Tags(value = {@Tag("high"), @Tag("SP-T1125"), @Tag("smoke")})
    @DisplayName("SP-T1125: Create an account")
    void testCreateAccount() throws IOException {

        PropertiesGetValue propertiesGetValue = new PropertiesGetValue();
        String inviteCode = propertiesGetValue.getPropertyValue("invite_code");
        String password = DataGenerator.getRandomValidPassword();
        MailService mailService;

        if (System.getProperty("TestEnvironment").contains("stage") || System.getProperty("TestEnvironment").contains("dev")) {
            mailService = new MailTrapServiceImpl();
        } else if (System.getProperty("TestEnvironment").contains("prod")) {
            mailService = new OneSecMailServiceImpl();
        } else mailService = null;

        String emailBoxName = mailService.generateNewMailBoxes();

        LoginService.clearCookiesAndRefresh();

        open(IBNextURLs.MAIN_URL);
        LoginPage.init()
                .goToRegistration()
                .fillInInviteCode(inviteCode)
                .continueRegistration()
                .fillInFormField(SignUpFormFieldTypeEnum.COUNTRY, "United States")
                .fillInFormField(SignUpFormFieldTypeEnum.FULL_NAME, DataGenerator.getRandomString())
                .fillInFormField(SignUpFormFieldTypeEnum.EMAIL, emailBoxName)
                .fillInFormField(SignUpFormFieldTypeEnum.PASSWORD, password)
                .fillInFormField(SignUpFormFieldTypeEnum.CONFIRM_PASSWORD, password)
                .fillInFormField(SignUpFormFieldTypeEnum.INSTITUTION, DataGenerator.getRandomString())
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
    void testIntegrationsPageIsOpenedFirstTimeLogin() throws IOException {

        PropertiesGetValue propertiesGetValue = new PropertiesGetValue();
        String inviteCode = propertiesGetValue.getPropertyValue("invite_code");
        String password = DataGenerator.getRandomValidPassword();
        String fullName = "SP-T811_" + DataGenerator.getRandomString();
        MailService mailService;

        if (System.getProperty("TestEnvironment").contains("stage") || System.getProperty("TestEnvironment").contains("dev")) {
            mailService = new MailTrapServiceImpl();
        } else if (System.getProperty("TestEnvironment").contains("prod")) {
            mailService = new OneSecMailServiceImpl();
        } else mailService = null;

        String emailBoxName = mailService.generateNewMailBoxes();

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
                .fillInFormField(SignUpFormFieldTypeEnum.INSTITUTION, DataGenerator.getRandomString())
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
    @Tags(value = {@Tag("normal"), @Tag("SP-T1315"), @Tag("smoke")})
    @Description("Verify that a main user account is deleted successfully and is removed from the admin page.")
    @DisplayName("SP-T1315: Deleting a main user account")
    void testDeleteAccount() throws IOException {

        PropertiesGetValue propertiesGetValue = new PropertiesGetValue();
        String inviteCode = propertiesGetValue.getPropertyValue("invite_code");
        String password = DataGenerator.getRandomValidPassword();
        MailService mailService;

        if (System.getProperty("TestEnvironment").contains("stage") || System.getProperty("TestEnvironment").contains("dev")) {
            mailService = new MailTrapServiceImpl();
        } else if (System.getProperty("TestEnvironment").contains("prod")) {
            mailService = new OneSecMailServiceImpl();
        } else mailService = null;

        String emailBoxName = mailService.generateNewMailBoxes();

        LoginService.clearCookiesAndRefresh();

        open(IBNextURLs.MAIN_URL);
        LoginPage.init()
                .goToRegistration()
                .fillInInviteCode(inviteCode)
                .continueRegistration()
                .fillInFormField(SignUpFormFieldTypeEnum.COUNTRY, "United States")
                .fillInFormField(SignUpFormFieldTypeEnum.FULL_NAME, DataGenerator.getRandomString())
                .fillInFormField(SignUpFormFieldTypeEnum.EMAIL, emailBoxName)
                .fillInFormField(SignUpFormFieldTypeEnum.PASSWORD, password)
                .fillInFormField(SignUpFormFieldTypeEnum.CONFIRM_PASSWORD, password)
                .fillInFormField(SignUpFormFieldTypeEnum.INSTITUTION, DataGenerator.getRandomString())
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

        Selenide.sleep(Long.parseLong(propertiesGetValue.getPropertyValue("sleep_time_long")));

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
}