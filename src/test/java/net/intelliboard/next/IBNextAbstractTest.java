package net.intelliboard.next;

import com.codeborne.selenide.Condition;
import net.intelliboard.next.services.IBNextURLs;
import net.intelliboard.next.services.pages.LoginPage;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class IBNextAbstractTest extends AbstractTest {

    protected static String USER_LOGIN;
    protected static String USER_PASS;
    protected static String MOODLE_CLIENT_ID;
    protected static String MOODLE_LMS_URL;
    protected static String SIGNUP_INVITE_REGISTRATION_CODE;
    protected static String CANVAS_CLIENT_ID;
    protected static String CANVAS_LMS_URL;
    protected static String CANVAS_CLIENT_SECRET;
    protected static String CANVAS_DATA_CLIENT_ID;
    protected static String CANVAS_DATA_CLIENT_SECRET;
    protected static String CANVAS_USER_LOGIN;
    protected static String CANVAS_USER_PASS;

    static {
        try {
            USER_LOGIN = propertiesGetValue.getPropertyValue("user_login");
            USER_PASS = propertiesGetValue.getPropertyValue("user_pass");
            SIGNUP_INVITE_REGISTRATION_CODE = propertiesGetValue.getPropertyValue("invite_code");
            MOODLE_CLIENT_ID = propertiesGetValue.getPropertyValue("moodel_client_id");
            MOODLE_LMS_URL = propertiesGetValue.getPropertyValue("moodel_lms_url");
            CANVAS_CLIENT_ID = propertiesGetValue.getPropertyValue("canvas_client_id");
            CANVAS_LMS_URL = propertiesGetValue.getPropertyValue("canvas_lms_url");
            CANVAS_CLIENT_SECRET = propertiesGetValue.getPropertyValue("canvas_client_secret");
            CANVAS_DATA_CLIENT_ID = propertiesGetValue.getPropertyValue("canvas_data_client_id");
            CANVAS_DATA_CLIENT_SECRET = propertiesGetValue.getPropertyValue("canvas_data_client_secret");
            CANVAS_USER_LOGIN = propertiesGetValue.getPropertyValue("canvas_user_login");
            CANVAS_USER_PASS = propertiesGetValue.getPropertyValue("canvas_user_pass");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loginAppUI(String userLogin, String userPass) {

        LoginPage loginPage = new LoginPage();

        open(IBNextURLs.LOGIN_PAGE);
        loginPage.loginField.setValue(userLogin);
        loginPage.buttonSubmit.click();
        loginPage.passwordField.shouldBe(Condition.visible).setValue(userPass);
        loginPage.buttonSubmit.click();
        $x("//header").shouldBe(Condition.visible);
    }
}
