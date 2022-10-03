package net.intelliboard.next.tests.core.registration;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Feature;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.IBNextURLs;
import net.intelliboard.next.services.PropertiesGetValue;
import net.intelliboard.next.services.api.connectors.MailService;
import net.intelliboard.next.services.api.connectors.mailtramp.MailTrapServiceImpl;
import net.intelliboard.next.services.api.connectors.onesecmail.OneSecMailServiceImpl;
import net.intelliboard.next.services.helpers.DataGenerator;
import net.intelliboard.next.services.pages.login.LoginPage;
import net.intelliboard.next.services.pages.signup.IBRegistrationConfirmationPage;
import net.intelliboard.next.services.pages.signup.SignUpFormFieldTypeEnum;
import net.intelliboard.next.services.pages.signup.WelcomePage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Feature("Sign_Up")
@Tag("Sign_Up")
public class UserRegistrationTest extends IBNextAbstractTest {

    private final String inviteCode = propertiesGetValue.getPropertyValue("invite_code");

    public UserRegistrationTest() throws IOException {
    }

    @Test
    @Tags(value = {@Tag("high"), @Tag("SP-T35"), @Tag("smoke")})
    @DisplayName("SP-T35: Successful user registration")
    public void testUserSuccessRegistration() {

        String email = DataGenerator.getRandomValidEmail();
        String password = DataGenerator.getRandomValidPassword();

        WebDriverRunner.getWebDriver().manage().deleteAllCookies();

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
    public void testCreateAccount() throws IOException {

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

        WebDriverRunner
                .getWebDriver()
                .manage()
                .deleteAllCookies();

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
    public void testIntegrationsPageIsOpenedFirstTimeLogin() throws IOException {

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

        WebDriverRunner
                .getWebDriver()
                .manage()
                .deleteAllCookies();

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
}
