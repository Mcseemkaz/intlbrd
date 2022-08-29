package net.intelliboard.next.tests.core.registration;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Feature;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.IBNextURLs;
import net.intelliboard.next.services.PropertiesGetValue;
import net.intelliboard.next.services.api.connectors.onesecmail.OneSecMailRequestBuilder;
import net.intelliboard.next.services.api.dto.OneSecMailMessageDTO;
import net.intelliboard.next.services.helpers.DataGenerator;
import net.intelliboard.next.services.pages.login.LoginPage;
import net.intelliboard.next.services.pages.signup.IBRegistrationConfirmationPage;
import net.intelliboard.next.services.pages.signup.SignUpFormFieldTypeEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;

import static com.codeborne.selenide.Selenide.open;

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
    public void testOneSecMailBox() throws IOException {

        //Get OneSecMailBox
        OneSecMailRequestBuilder mailRequestBuilder = new OneSecMailRequestBuilder();
        String oneSecMailBox = mailRequestBuilder.generateNewMailBoxes();

        String password = DataGenerator.getRandomValidPassword();
        PropertiesGetValue propertiesGetValue = new PropertiesGetValue();
        String inviteCode = propertiesGetValue.getPropertyValue("invite_code");

        WebDriverRunner.getWebDriver().manage().deleteAllCookies();

        open(IBNextURLs.MAIN_URL);
        LoginPage.init()
                .goToRegistration()
                .fillInInviteCode(inviteCode)
                .continueRegistration()
                .fillInFormField(SignUpFormFieldTypeEnum.COUNTRY, "United States")
                .fillInFormField(SignUpFormFieldTypeEnum.FULL_NAME, DataGenerator.getRandomString())
                .fillInFormField(SignUpFormFieldTypeEnum.EMAIL, oneSecMailBox)
                .fillInFormField(SignUpFormFieldTypeEnum.PASSWORD, password)
                .fillInFormField(SignUpFormFieldTypeEnum.CONFIRM_PASSWORD, password)
                .fillInFormField(SignUpFormFieldTypeEnum.INSTITUTION, DataGenerator.getRandomString())
                .fillInFormField(SignUpFormFieldTypeEnum.PHONE_NUMBER, DataGenerator.getRandomNumber())
                .agreeTermsPolicy()
                .submitForm();

        String id =
                String.valueOf(
                        Arrays.stream(mailRequestBuilder.getListEmails(oneSecMailBox))
                                .findFirst()
                                .get()
                                .getId());

        OneSecMailMessageDTO message = mailRequestBuilder.getMessageById(oneSecMailBox, id);
        open(mailRequestBuilder.getRegistrationLink(message.getHtmlBody()));

        IBRegistrationConfirmationPage
                .init()
                .goToMyLogin()
                .fillInLoginFiled(oneSecMailBox)
                .fillInPassFiled(password)
                .submitForm();
    }
}
