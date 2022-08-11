package net.intelliboard.next.services.pages.connections;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.pages.DatePicker;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;

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
    public static String ELLUCIAN_BANNER_KEY;
    public static String ELLUCIAN_COLLEUGUE_KEY;
    public static String TOTARA_URL;
    public static String TOTARA_KEY;
    public static String MWP_URL;
    public static String MWP_KEY;
    public static String MWP_W_URL;
    public static String MWP_W_KEY;
    public static String QWICKLY_KEY;
    public static String QWICKLY_SECRET;
    public static String MONGOOSE_API_KEY;
    public static String MONGOOSE_SECRET;
    public static String MONGOOSE_TEAM_CODE;


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
            CreateConnectionPage.ELLUCIAN_BANNER_KEY = propertiesGetValue.getPropertyValue("ellucian_banner");
            CreateConnectionPage.ELLUCIAN_COLLEUGUE_KEY = propertiesGetValue.getPropertyValue("ellucian_colleugue");
            CreateConnectionPage.TOTARA_URL = propertiesGetValue.getPropertyValue("totara_url");
            CreateConnectionPage.TOTARA_KEY = propertiesGetValue.getPropertyValue("totara_token");
            CreateConnectionPage.MWP_URL = propertiesGetValue.getPropertyValue("mwp_url");
            CreateConnectionPage.MWP_KEY = propertiesGetValue.getPropertyValue("mwp_token");
            CreateConnectionPage.MWP_W_URL = propertiesGetValue.getPropertyValue("mwp_workspace_url");
            CreateConnectionPage.MWP_W_KEY = propertiesGetValue.getPropertyValue("mwp_workspace_token");
            CreateConnectionPage.QWICKLY_KEY = propertiesGetValue.getPropertyValue("qwickly_key");
            CreateConnectionPage.QWICKLY_SECRET = propertiesGetValue.getPropertyValue("qwickly_secret");
            CreateConnectionPage.MONGOOSE_API_KEY = propertiesGetValue.getPropertyValue("mongoose_api_key");
            CreateConnectionPage.MONGOOSE_SECRET = propertiesGetValue.getPropertyValue("mongoose_secret");
            CreateConnectionPage.MONGOOSE_TEAM_CODE = propertiesGetValue.getPropertyValue("mongoose_team_code");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected SelenideElement connectionNameField = $x("//input[@id='lmsName']");
    private SelenideElement clientIdField = $x("//input[@id='clientId']");
    private SelenideElement lmsUrlField = $x("//input[@id='lmsUrl']");
    private SelenideElement clientSecretField = $x("//input[@id='clientSecret']");
    private SelenideElement dataClientIdField = $x("//input[@id='dataClientId']");
    private SelenideElement dataClientSecretField = $x("//input[@id='dataClientSecret']");
    private SelenideElement buttonContinue = $x("//button[@type='submit']");
    private SelenideElement d2lId = $x("//input[@name='client_id']");
    private SelenideElement d2lSecret = $x("//input[@name='client_secret']");
    private SelenideElement sakaiTokenField = $x("//input[@id='clientId']");

    public static CreateConnectionPage init() {
        $x("//form[contains (@id,'create-connection-form')]")
                .shouldBe(Condition.exist, Duration.ofSeconds(60));
        return new CreateConnectionPage();
    }

    public LmsFilterSettingPage createMoodleConnection(String lmsName, String clientId, String lmsUrl) {
        connectionNameField.setValue(lmsName);
        clientIdField.setValue(clientId);
        lmsUrlField.setValue(lmsUrl);
        submitForm();
        return LmsFilterSettingPage.init();
    }

    public void createCanvasConnection(String lmsName, String clientId, String lmsUrl, String clientSecret,
                                       String dataClientId, String dataClientSecret) {
        connectionNameField.setValue(lmsName);
        clientIdField.setValue(clientId);
        lmsUrlField.setValue(lmsUrl);
        clientSecretField.setValue(clientSecret);
        dataClientIdField.setValue(dataClientId);
        dataClientSecretField.setValue(dataClientSecret);
        submitForm();
    }

    public LmsFilterSettingPage createBlackboardConnection(String lmsName, String clientId, String lmsUrl) {
        connectionNameField.setValue(lmsName);
        clientIdField.setValue(clientId);
        lmsUrlField.setValue(lmsUrl);
        submitForm();
        return LmsFilterSettingPage.init();
    }



    public ConnectionsListPage createD2LConnection(String lmsName, String D2LUrl, String D2LId, String D2LSecret,
                                                   String D2LLogin, String D2Lpassword) {
        connectionNameField.setValue(lmsName);
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
        connectionNameField.setValue(lmsName);
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
        connectionNameField.setValue(lmsName);
        lmsUrlField.setValue(SAKAIUrl);
        sakaiTokenField.setValue(SAKAIToken);
        submitForm();

        return LmsFilterSettingPage
                .init()
                .saveFilterSettings();
    }

    public ConnectionsListPage createTOTARAConnection(String lmsName, String TOTARAUrl, String TOTARAToken) {
        connectionNameField.setValue(lmsName);
        lmsUrlField.setValue(TOTARAUrl);
        clientIdField.setValue(TOTARAToken);
        submitForm();

        return ConnectionsListPage.init();
    }

    public ConnectionsListPage createQWICKLYConnection(String qwicklyDataFeedUrl, String qwicklyKey, String qwicklySecret) {
        $x("//input[@id='qwickly_data_feed_url']").sendKeys(qwicklyDataFeedUrl);
        $x("//input[@id='qwickly_key']").sendKeys(qwicklyKey);
        $x("//input[@id='qwickly_secret']").sendKeys(qwicklySecret);

        submitForm();
        return ConnectionsListPage.init();
    }

    public ConnectionsListPage createMongooseConnection(String mainConnectionName, String mogooseAPIKey, String mongooseSecret, String mongooseTeamCode, LocalDateTime date) {
        selectConnection(mainConnectionName);
        $x("//input[@id='mongoose_cadence_api_key']").sendKeys(mogooseAPIKey);
        $x("//input[@id='mongoose_cadence_secret']").sendKeys(mongooseSecret);
        $x("//input[@id='mongoose_cadence_team_code']").sendKeys(mongooseTeamCode);
        $x("//input[contains (@class,'date-picker-input') and contains (@class,'form-control')]").click();
        DatePicker.init().setDayOfMonth(date);
        submitForm();
        return ConnectionsListPage.init();
    }

    public CreateConnectionPage selectConnection(String connectionName) {
        $x("//div[contains(@class, 'intelli-dropdown')]//button[@class='tree-choice']")
                .click();
        $x("//div[contains(@class, 'tree-drop')]//li[.//strong[text()='" + connectionName + "']]")
                .click();
        $x("(//div[contains(@class, 'intelli-dropdown')]//button[@class='tree-choice']/span)[1]")
                .shouldBe(Condition.visible, Duration.ofSeconds(5))
                .shouldHave(Condition.text(connectionName));
        return this;
    }

    private void waitForValidation() {
        IBNextAbstractTest ibNextAbstractTest = new IBNextAbstractTest();
        ibNextAbstractTest.waitForPageLoaded();
    }

    public void submitForm() {
        waitForValidation();
        buttonContinue.click();
    }
}
