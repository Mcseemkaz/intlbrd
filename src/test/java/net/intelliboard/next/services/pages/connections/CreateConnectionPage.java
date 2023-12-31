package net.intelliboard.next.services.pages.connections;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.pages.connections.connection.ConnectionProcessingFrequencyTypeEnum;
import net.intelliboard.next.services.pages.elements.DatePickerElement;
import net.intelliboard.next.services.pages.elements.spinners.PageSpinner;
import org.openqa.selenium.Keys;

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
    public static String QWICKLY_URL;
    public static String QWICKLY_KEY;
    public static String QWICKLY_SECRET;
    public static String MONGOOSE_API_KEY;
    public static String MONGOOSE_SECRET;
    public static String MONGOOSE_TEAM_CODE;
    public static String HUBSPOT_ACCESS_TOKEN;


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
            CreateConnectionPage.QWICKLY_URL = propertiesGetValue.getPropertyValue("qwickly_url");
            CreateConnectionPage.QWICKLY_KEY = propertiesGetValue.getPropertyValue("qwickly_key");
            CreateConnectionPage.QWICKLY_SECRET = propertiesGetValue.getPropertyValue("qwickly_secret");
            CreateConnectionPage.MONGOOSE_API_KEY = propertiesGetValue.getPropertyValue("mongoose_api_key");
            CreateConnectionPage.MONGOOSE_SECRET = propertiesGetValue.getPropertyValue("mongoose_secret");
            CreateConnectionPage.MONGOOSE_TEAM_CODE = propertiesGetValue.getPropertyValue("mongoose_team_code");
            CreateConnectionPage.HUBSPOT_ACCESS_TOKEN = propertiesGetValue.getPropertyValue("hubspot_access_token");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected final SelenideElement connectionNameField = $x("//input[@id='lmsName']");
    protected final SelenideElement clientIdField = $x("//input[@id='clientId']");
    protected final SelenideElement lmsUrlField = $x("//input[@id='lmsUrl']");
    private final static SelenideElement connectionForm = $x("//form[contains (@id,'create-connection-form')]");
    private final SelenideElement clientSecretField = $x("//input[@id='clientSecret']");
    private final SelenideElement dataClientIdField = $x("//input[@id='dataClientId']");
    private final SelenideElement dataClientSecretField = $x("//input[@id='dataClientSecret']");
    private final SelenideElement buttonContinue = $x("//button[@type='submit']");
    private final SelenideElement d2lId = $x("//input[@name='client_id']");
    private final SelenideElement d2lSecret = $x("//input[@name='client_secret']");
    private final SelenideElement sakaiTokenField = $x("//input[@id='clientId']");

    @Step("Create Connection Page init")
    public static CreateConnectionPage init() {
        PageSpinner.waitPreloader();
        PageSpinner.waitSpinner();
        connectionForm
                .shouldBe(Condition.exist, Duration.ofSeconds(60));
        return new CreateConnectionPage();
    }

    @Step("Create Moodle Connection")
    public LmsFilterSettingPage createMoodleConnection(String lmsName, String clientId, String lmsUrl) {
        connectionNameField.setValue(lmsName);
        clientIdField.setValue(clientId);
        lmsUrlField.setValue(lmsUrl);
        submitForm();
        return LmsFilterSettingPage.init();
    }

    @Step("Create Canvas Connection")
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

    @Step("Create D2L Connection")
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

    @Step("Create ILIAS Connection")
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

    @Step("Create SAKAI Connection")
    public ConnectionsListPage createSAKAIConnection(String lmsName, String SAKAIUrl, String SAKAIToken) {
        connectionNameField.setValue(lmsName);
        lmsUrlField.setValue(SAKAIUrl);
        sakaiTokenField.setValue(SAKAIToken);
        submitForm();

        return LmsFilterSettingPage
                .init()
                .saveFilterSettings();
    }

    @Step("Create TOTARA Connection")
    public ConnectionsListPage createTOTARAConnection(String connectionName, String TOTARAUrl, String TOTARAToken) {
        connectionNameField.setValue(connectionName);
        lmsUrlField.setValue(TOTARAUrl);
        clientIdField.setValue(TOTARAToken);
        submitForm();

        LmsFilterSettingPage.init().saveFilterSettings();
        return ConnectionsListPage.init();
    }

    @Step("Create QWICKLY Connection")
    public ConnectionsListPage createQWICKLYConnection(String mainConnectionName, String qwicklyDataFeedUrl, String qwicklyKey, String qwicklySecret, ConnectionProcessingFrequencyTypeEnum type, int time) {
        selectConnection(mainConnectionName);
        $x("//input[@id='qwickly_data_feed_url']").sendKeys(qwicklyDataFeedUrl);
        $x("//input[@id='qwickly_key']").sendKeys(qwicklyKey);
        $x("//input[@id='qwickly_secret']").sendKeys(qwicklySecret);
        selectProcessingFrequency(type);
        selectProcessingTime(time);
        submitForm();
        return ConnectionsListPage.init();
    }

    @Step("Create Mongoose Connection")
    public ConnectionsListPage createMongooseConnection(String mainConnectionName, String mogooseAPIKey, String mongooseSecret, String mongooseTeamCode, LocalDateTime date, ConnectionProcessingFrequencyTypeEnum type, int time) {
        selectConnection(mainConnectionName);
        $x("//input[@id='mongoose_cadence_api_key']").sendKeys(mogooseAPIKey);
        $x("//input[@id='mongoose_cadence_secret']").sendKeys(mongooseSecret);
        $x("//input[@id='mongoose_cadence_team_code']").sendKeys(mongooseTeamCode);
        selectProcessingFrequency(type);
        selectProcessingTime(time);

        $x("//input[contains (@class,'date-picker-input') and not (@name)][ ./preceding-sibling::label[contains (text(),'Processing Date')]]").click();
        DatePickerElement.init().setDayOfMonth(date);
        submitForm();
        return ConnectionsListPage.init();
    }

    @Step("Create Hubspot Connection")
    public ConnectionsListPage createHubspotConnection(String mainConnectionName, String hubspotAPIKey, ConnectionProcessingFrequencyTypeEnum type, int time) {
        selectConnection(mainConnectionName);
        $x("//input[@id='hubspot_token']").sendKeys(hubspotAPIKey);
        selectProcessingFrequency(type);
        selectProcessingTime(time);
        submitForm();
        return ConnectionsListPage.init();
    }

    @Step("Select Connection")
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

    protected CreateConnectionPage selectProcessingFrequency(ConnectionProcessingFrequencyTypeEnum type) {
        $x("//div[contains (@label,'Processing Frequency')]//button[contains (@class, 'tree-choice')]").click();
        $x("//div[contains (@label,'Processing Frequency')]//li[ .//*[contains (text(),'" + type.value + "')]]").click();
        return this;
    }

    protected CreateConnectionPage selectProcessingTime(int time) {
        $x("//input[contains (@class,'date-picker-input') and not (@name)][ ./preceding-sibling::label[contains (text(),'Processing Time') or contains (text(), 'Processing time')]]")
                .click();
        $x("//input[contains (@class,'flatpickr-hour')]")
                .setValue(String.valueOf(time))
                .sendKeys(Keys.ENTER);
        return this;
    }
}
