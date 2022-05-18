package net.intelliboard.next.services.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

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

    public static CreateConnectionPage init() {
        $x("//form[contains (@id,'create-connection-form')]")
                .shouldBe(Condition.exist, Duration.ofSeconds(60));
        return new CreateConnectionPage();
    }

    public void createMoodleConnection(String lmsName, String clientId, String lmsUrl) {
        lmsNameField.setValue(lmsName);
        clientIdField.setValue(clientId);
        lmsUrlField.setValue(lmsUrl);
        buttonContinue.click();
    }

    public void createCanvasConnection(String lmsName, String clientId, String lmsUrl, String clientSecret,
                                       String dataClientId, String dataClientSecret) {
        lmsNameField.setValue(lmsName);
        clientIdField.setValue(clientId);
        lmsUrlField.setValue(lmsUrl);
        clientSecretField.setValue(clientSecret);
        dataClientIdField.setValue(dataClientId);
        dataClientSecretField.setValue(dataClientSecret);
        buttonContinue.click();
    }

    public void createBlackboardConnection(String lmsName, String clientId, String lmsUrl) {
        lmsNameField.setValue(lmsName);
        clientIdField.setValue(clientId);
        lmsUrlField.setValue(lmsUrl);
        buttonContinue.click();
    }

    public ConnectionsListPage createZoomConnection(String lmsName, String zoomToken, String zoomSecret) {
        lmsNameField.setValue(lmsName);
        zoomTokenField.setValue(zoomToken);
        zoomTokenSecret.setValue(zoomSecret);
        buttonContinue.click();
        LmsFilterSettingPage.init().saveFilterSettings();
        return ConnectionsListPage.init();
    }

}
