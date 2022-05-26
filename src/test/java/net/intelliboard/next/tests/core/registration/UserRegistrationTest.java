package net.intelliboard.next.tests.core.registration;

import com.codeborne.selenide.WebDriverRunner;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.IBNextURLs;
import net.intelliboard.next.services.helpers.DataGenerator;
import net.intelliboard.next.services.pages.login.LoginPage;
import net.intelliboard.next.services.pages.signup.SignUpFormFieldTypeEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

public class UserRegistrationTest extends IBNextAbstractTest {

    @Test
    @Tags(value = {@Tag("high"), @Tag("SP-T35"), @Tag("smoke")})
    @DisplayName("SP-T35: Successful user registration")
    public void testUserSuccessRegistration() throws InterruptedException {

        String email = DataGenerator.getRandomValidEmail();
        String password = DataGenerator.getRandomValidPassword();
        WebDriverRunner.getWebDriver().manage().deleteAllCookies();

        open(IBNextURLs.MAIN_URL);
        LoginPage.init()
                .goToRegistration()
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
}
