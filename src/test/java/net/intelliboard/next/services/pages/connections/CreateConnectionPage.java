package net.intelliboard.next.services.pages.connections;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import net.intelliboard.next.IBNextAbstractTest;

import java.io.IOException;
import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;
import static net.intelliboard.next.AbstractTest.propertiesGetValue;

public class CreateConnectionPage {

    public static String MOODLE_CLIENT_ID;
    public static String MOODLE_LMS_URL;
    public static String SIGNUP_INVITE_REGISTRATION_CODE;
    public static String CANVAS_CLIENT_ID;
    public static String CANVAS_LMS_URL;
    public static String CANVAS_CLIENT_SECRET;
    public static String CANVAS_DATA_CLIENT_ID;
    public static String CANVAS_DATA_CLIENT_SECRET;
    public static String CANVAS_USER_LOGIN;
    public static String CANVAS_USER_PASS;
    public static String BLACKBOARD_CLIENT_ID;
    public static String BLACKBOARD_LMS_URL;
    public static String ZOOM_TOKEN;
    public static String ZOOM_SECRET;
    public static String D2L_URL;
    public static String D2L_CLIENT_ID;
    public static String D2L_CLIENT_SECRET;
    public static String D2L_USER_LOGIN;
    public static String D2L_USER_PASS;
    public static String ILIAS_URL;
    public static String ILIAS_TOKEN;
    public static String ILIAS_KEY;
    public static String SAKAI_URL;
    public static String SAKAI_TOKEN;
    public static String SAKAI_KEY;


    static {
        try {
            CreateConnectionPage.SIGNUP_INVITE_REGISTRATION_CODE = propertiesGetValue.getPropertyValue("invite_code");
            CreateConnectionPage.MOODLE_CLIENT_ID = propertiesGetValue.getPropertyValue("moodel_client_id");
            CreateConnectionPage.MOODLE_LMS_URL = propertiesGetValue.getPropertyValue("moodel_lms_url");
            CreateConnectionPage.CANVAS_CLIENT_ID = propertiesGetValue.getPropertyValue("canvas_client_id");
            CreateConnectionPage.CANVAS_LMS_URL = propertiesGetValue.getPropertyValue("canvas_lms_url");
            CreateConnectionPage.CANVAS_CLIENT_SECRET = propertiesGetValue.getPropertyValue("canvas_client_secret");
            CreateConnectionPage.CANVAS_DATA_CLIENT_ID = propertiesGetValue.getPropertyValue("canvas_data_client_id");
            CreateConnectionPage.CANVAS_DATA_CLIENT_SECRET = propertiesGetValue.getPropertyValue("canvas_data_client_secret");
            CreateConnectionPage.CANVAS_USER_LOGIN = propertiesGetValue.getPropertyValue("canvas_user_login");
            CreateConnectionPage.CANVAS_USER_PASS = propertiesGetValue.getPropertyValue("canvas_user_pass");
            CreateConnectionPage.BLACKBOARD_LMS_URL = propertiesGetValue.getPropertyValue("blackboard_lms_url");
            CreateConnectionPage.BLACKBOARD_CLIENT_ID = propertiesGetValue.getPropertyValue("blackboard_client_id");
            CreateConnectionPage.ZOOM_TOKEN = propertiesGetValue.getPropertyValue("zoom_token");
            CreateConnectionPage.ZOOM_SECRET = propertiesGetValue.getPropertyValue("zoom_secret");
            CreateConnectionPage.D2L_URL = propertiesGetValue.getPropertyValue("d2l_url");
            CreateConnectionPage.D2L_CLIENT_ID = propertiesGetValue.getPropertyValue("d2l_connection_id");
            CreateConnectionPage.D2L_CLIENT_SECRET = propertiesGetValue.getPropertyValue("d2l_connection_secret");
            CreateConnectionPage.D2L_USER_LOGIN = propertiesGetValue.getPropertyValue("d2l_user_login");
            CreateConnectionPage.D2L_USER_PASS = propertiesGetValue.getPropertyValue("d2l_user_pass");
            CreateConnectionPage.ILIAS_URL = propertiesGetValue.getPropertyValue("ilias_url");
            CreateConnectionPage.ILIAS_TOKEN = propertiesGetValue.getPropertyValue("ilias_token");
            CreateConnectionPage.ILIAS_KEY = propertiesGetValue.getPropertyValue("ilias_key");
            CreateConnectionPage.SAKAI_URL = propertiesGetValue.getPropertyValue("sakai_url");
            CreateConnectionPage.SAKAI_TOKEN = propertiesGetValue.getPropertyValue("sakai_token");
            CreateConnectionPage.SAKAI_KEY = propertiesGetValue.getPropertyValue("sakai_key");


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private SelenideElement lmsNameField = $x("//input[@id=\"lmsName\"]");
    private SelenideElement clientIdField = $x("//input[@id=\"clientId\"]");
    private SelenideElement lmsUrlField = $x("//input[@id=\"lmsUrl\"]");
    private SelenideElement clientSecretField = $x("//input[@id=\"clientSecret\"]");
    private SelenideElement dataClientIdField = $x("//input[@id=\"dataClientId\"]");
    private SelenideElement dataClientSecretField = $x("//input[@id=\"dataClientSecret\"]");
    private SelenideElement buttonContinue = $x("//button[@type=\"submit\"]");
    private SelenideElement zoomTokenField = $x("//input[@name='zoom_token']");
    private SelenideElement zoomTokenSecret = $x("//input[@name='zoom_secret']");
    private SelenideElement d2lId = $x("//input[@name='client_id']");
    private SelenideElement d2lSecret = $x("//input[@name='client_secret']");
    private SelenideElement sakaiTokenField = $x("//input[@id='clientId']");


    public static CreateConnectionPage init() {
        $x("//form[contains (@id,'create-connection-form')]")
                .shouldBe(Condition.exist, Duration.ofSeconds(60));
        return new CreateConnectionPage();
    }

    public LmsFilterSettingPage createMoodleConnection(String lmsName, String clientId, String lmsUrl) throws InterruptedException {
        lmsNameField.setValue(lmsName);
        clientIdField.setValue(clientId);
        lmsUrlField.setValue(lmsUrl);
        submitForm();
        return LmsFilterSettingPage.init();
    }

    public void createCanvasConnection(String lmsName, String clientId, String lmsUrl, String clientSecret,
                                       String dataClientId, String dataClientSecret) {
        lmsNameField.setValue(lmsName);
        clientIdField.setValue(clientId);
        lmsUrlField.setValue(lmsUrl);
        clientSecretField.setValue(clientSecret);
        dataClientIdField.setValue(dataClientId);
        dataClientSecretField.setValue(dataClientSecret);
        submitForm();
    }

    public void createBlackboardConnection(String lmsName, String clientId, String lmsUrl) {
        lmsNameField.setValue(lmsName);
        clientIdField.setValue(clientId);
        lmsUrlField.setValue(lmsUrl);
        submitForm();
    }

    public ConnectionsListPage createZoomConnection(String lmsName, String zoomToken, String zoomSecret) {
        lmsNameField.setValue(lmsName);
        zoomTokenField.setValue(zoomToken);
        zoomTokenSecret.setValue(zoomSecret);
        submitForm();

        LmsFilterSettingPage.init().saveFilterSettings();
        return ConnectionsListPage.init();
    }

    public ConnectionsListPage createD2LConnection(String lmsName, String D2LUrl, String D2LId, String D2LSecret,
                                                   String D2LLogin, String D2Lpassword) {
        lmsNameField.setValue(lmsName);
        lmsUrlField.setValue(D2LUrl);
        d2lId.setValue(D2LId);
        d2lSecret.setValue(D2LSecret);
        submitForm();

        return LoginD2LPage
                .init()
                .loginFill(D2LLogin)
                .fillPassword(D2Lpassword)
                .confirmAuthorize()
                .saveFilterSettings();
    }

    public ConnectionsListPage createILIASConnection(String lmsName, String IliasUrl, String IliasToken, String IliasKey) {
        lmsNameField.setValue(lmsName);
        lmsUrlField.setValue(IliasUrl);
        dataClientIdField.setValue(IliasToken);
        dataClientSecretField.setValue(IliasKey);
        submitForm();

        return LmsFilterSettingPage
                .init()
                .saveFilterSettings();
    }

    public ConnectionsListPage createSAKAIConnection(String lmsName, String SAKAIUrl, String SAKAIToken,
                                                     String SAKAYKey) {
        lmsNameField.setValue(lmsName);
        lmsUrlField.setValue(SAKAIUrl);
        sakaiTokenField.setValue(SAKAIToken);
        submitForm();

        return LmsFilterSettingPage
                .init()
                .saveFilterSettings();
    }

    private void waitForValidation() {
        IBNextAbstractTest ibNextAbstractTest = new IBNextAbstractTest();
        ibNextAbstractTest.waitForPageLoaded();
    }

    private void submitForm() {
        waitForValidation();
        buttonContinue.click();
    }
}
