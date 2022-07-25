package net.intelliboard.next.tests.core.connection;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Feature;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.helpers.DataGenerator;
import net.intelliboard.next.services.pages.connections.*;
import net.intelliboard.next.services.pages.header.HeaderConnectionManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;
import static net.intelliboard.next.services.IBNextURLs.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Feature("Create Connections")
public class CreateConnectionTest extends IBNextAbstractTest {
    @Test
    @Tags(value = {@Tag("high"), @Tag("SP-T83"), @Tag("health")})
    @DisplayName("SP-T83: Creating of Moodle connection")
    public void testCreateMoodleConnection() throws InterruptedException {

        CreateConnectionPage createConnectionPage = new CreateConnectionPage();
        LmsFilterSettingPage lmsFilterSettingPage = new LmsFilterSettingPage();
        ConnectionsListPage connectionsListPage = new ConnectionsListPage();

        String lmsMoodleName = "Moodle" + DataGenerator.getRandomString();
        open(CREATE_MOODLE_CONNECTION);
        createConnectionPage.createMoodleConnection(lmsMoodleName, CreateConnectionPage.MOODLE_CLIENT_ID, CreateConnectionPage.MOODLE_LMS_URL);
        $x("//header").shouldBe(Condition.visible);
        lmsFilterSettingPage.saveFilterSettings();
        $x("//a[contains (text(), '" + lmsMoodleName + "')]").exists();
        connectionsListPage.deleteConnection(lmsMoodleName);
    }

    @Test
    @Tags(value = {@Tag("high"), @Tag("SP-T89")})
    @DisplayName("SP-T89: Creating of Canvas connection")
    public void testCreateCanvasConnection() {

        CreateConnectionPage createConnectionPage = new CreateConnectionPage();
        ConnectionsListPage connectionsListPage = new ConnectionsListPage();

        String lmsCanvasName = "Canvas" + DataGenerator.getRandomString();

        open(CREATE_CANVAS_CONNECTION);
        createConnectionPage.createCanvasConnection(lmsCanvasName, CreateConnectionPage.CANVAS_CLIENT_ID, CreateConnectionPage.CANVAS_LMS_URL,
                CreateConnectionPage.CANVAS_CLIENT_SECRET, CreateConnectionPage.CANVAS_DATA_CLIENT_ID, CreateConnectionPage.CANVAS_DATA_CLIENT_SECRET);
        $x("//a[contains (text(), '" + lmsCanvasName + "')]").exists();

        LoginCanvasPage.init()
                .fillEmail(CreateConnectionPage.CANVAS_USER_LOGIN)
                .fillPassword(CreateConnectionPage.CANVAS_USER_PASS)
                .loginInCanvas()
                .confirmAuthorize()
                .saveFilterSettings();

        connectionsListPage.deleteConnection(lmsCanvasName);
    }

    @Test
    @Tags(value = {@Tag("high"), @Tag("SP-T599")})
    @DisplayName("SP-T599: Creating a Blackboard connection")
    public void testCreateBlackboardConnection() {

        CreateConnectionPage createConnectionPage = new CreateConnectionPage();
        ConnectionsListPage connectionsListPage = new ConnectionsListPage();
        LmsFilterSettingPage lmsFilterSettingPage = new LmsFilterSettingPage();

        String lmsBlackboardName = "Blackboard_" + DataGenerator.getRandomString();

        open(CREATE_BLACKBOARD_CONNECTION);

        createConnectionPage.createBlackboardConnection(lmsBlackboardName, CreateConnectionPage.BLACKBOARD_CLIENT_ID, CreateConnectionPage.BLACKBOARD_LMS_URL);
        $x("//header").shouldBe(Condition.visible);
        lmsFilterSettingPage.saveFilterSettings();
        $x("//a[contains (text(), '" + lmsBlackboardName + "')]").exists();

        connectionsListPage.deleteConnection(lmsBlackboardName);
    }

    @Test
    @Tags(value = {@Tag("high"), @Tag("SP-T106")})
    @DisplayName("SP-T106: Creating of Zoom independent connection")
    public void testCreateZoomConnection() {

        open(CREATE_ZOOM_CONNECTION);
        String lmsZoomName = "Zoom_" + DataGenerator.getRandomString();
        CreateConnectionPage.init()
                .createZoomConnection(lmsZoomName, CreateConnectionPage.ZOOM_TOKEN, CreateConnectionPage.ZOOM_SECRET);

        ConnectionsListPage connectionsListPage = ConnectionsListPage.init();

        connectionsListPage.findConnectionByName(lmsZoomName);

        assertThat(connectionsListPage.isConnectionExist(lmsZoomName))
                .isTrue()
                .as(String.format("Connection : %s is not existed", lmsZoomName));

        connectionsListPage
                .deleteConnection(lmsZoomName);
    }

    @Test
    @Tags(value = {@Tag("high"), @Tag("SP-T103")})
    @DisplayName("SP-T103: Creating of D2L connection")
    public void testCreateD2LConnection() {

        open(CREATE_D2L_CONNECTION);
        String lmsD2LName = "D2L_" + DataGenerator.getRandomString();
        CreateConnectionPage.init()
                .createD2LConnection(lmsD2LName, CreateConnectionPage.D2L_URL, CreateConnectionPage.D2L_CLIENT_ID,
                        CreateConnectionPage.D2L_CLIENT_SECRET, CreateConnectionPage.D2L_USER_LOGIN, CreateConnectionPage.D2L_USER_PASS);

        ConnectionsListPage connectionsListPage = ConnectionsListPage.init();

        connectionsListPage.findConnectionByName(lmsD2LName);

        assertThat(connectionsListPage.isConnectionExist(lmsD2LName))
                .isTrue()
                .as(String.format("Connection : %s is not existed", lmsD2LName));

        connectionsListPage
                .deleteConnection(lmsD2LName);
    }

    @Test
    @Tags(value = {@Tag("high"), @Tag("SP-T104")})
    @DisplayName("SP-T104: Creating of Ilias connection")
    public void testCreateIliasConnection() {

        open(CREATE_ILIAS_CONNECTION);
        String lmsILIASName = "ILIAS_" + DataGenerator.getRandomString();
        CreateConnectionPage.init()
                .createILIASConnection(lmsILIASName, CreateConnectionPage.ILIAS_URL, CreateConnectionPage.ILIAS_TOKEN,
                        CreateConnectionPage.ILIAS_KEY);

        ConnectionsListPage connectionsListPage = ConnectionsListPage.init();

        connectionsListPage.findConnectionByName(lmsILIASName);

        assertThat(connectionsListPage.isConnectionExist(lmsILIASName))
                .isTrue()
                .as(String.format("Connection : %s is not existed", lmsILIASName));

        connectionsListPage
                .deleteConnection(lmsILIASName);
    }

    @Test
    @Tags(value = {@Tag("high"), @Tag("SP-T823")})
    @DisplayName("SP-T823: Creating of SAKAI connection")
    public void testCreateSAKAIConnection() {

        open(CREATE_SAKAI_CONNECTION);
        String lmsSAKAIName = "SAKAI_" + DataGenerator.getRandomString();
        CreateConnectionPage.init()
                .createSAKAIConnection(lmsSAKAIName, CreateConnectionPage.SAKAI_URL, CreateConnectionPage.SAKAI_TOKEN,
                        CreateConnectionPage.SAKAI_KEY);

        ConnectionsListPage connectionsListPage = ConnectionsListPage.init();

        connectionsListPage.findConnectionByName(lmsSAKAIName);

        assertThat(connectionsListPage.isConnectionExist(lmsSAKAIName))
                .isTrue()
                .as(String.format("Connection : %s is not existed", lmsSAKAIName));

        connectionsListPage
                .deleteConnection(lmsSAKAIName);
    }

    @Test
    @Tags(value = {@Tag("high"), @Tag("SP-T814")})
    @DisplayName("SP-T814: Creating of Ellucian Colleague Sub-connection")
    public void testCreateEllucianColleagueSubConnection() {

        CreateConnectionPage createConnectionPage = new CreateConnectionPage();
        String connectionName = "Canvas" + DataGenerator.getRandomString();

        open(CREATE_CANVAS_CONNECTION);
        createConnectionPage.createCanvasConnection(connectionName, CreateConnectionPage.CANVAS_CLIENT_ID, CreateConnectionPage.CANVAS_LMS_URL,
                CreateConnectionPage.CANVAS_CLIENT_SECRET, CreateConnectionPage.CANVAS_DATA_CLIENT_ID, CreateConnectionPage.CANVAS_DATA_CLIENT_SECRET);
        $x("//a[contains (text(), '" + connectionName + "')]").exists();

        LoginCanvasPage.init()
                .fillEmail(CreateConnectionPage.CANVAS_USER_LOGIN)
                .fillPassword(CreateConnectionPage.CANVAS_USER_PASS)
                .loginInCanvas()
                .confirmAuthorize()
                .saveFilterSettings();

        open(CREATE_ELLUCIAN_COLLEAGUE_CONNECTION);

        EllucianConnectionPage.init()
                .selectConnection(connectionName)
                .fillInEllucianToken(CreateConnectionPage.ELLUCIAN_COLLEUGUE_KEY)
                .submitForm();

        assertThat(ConnectionsListPage.init().checkIntegration(ConnectionIntegrationType.ELLUCIAN_COLLEAGUE, connectionName))
                .isTrue()
                .as(String.format("Integration connection %s is not exist", ConnectionIntegrationType.ELLUCIAN_COLLEAGUE));

        ConnectionsListPage
                .init()
                .deleteConnection(connectionName);
    }

    @Test
    @Tags(value = {@Tag("high"), @Tag("SP-T814 2")})
    @DisplayName("SP-T814 2: Creating of Ellucian Banner Sub-connection")
    public void testCreateEllucianBannerSubConnection() {

        CreateConnectionPage createConnectionPage = new CreateConnectionPage();
        String connectionName = "Canvas" + DataGenerator.getRandomString();

        open(CREATE_CANVAS_CONNECTION);
        createConnectionPage.createCanvasConnection(connectionName, CreateConnectionPage.CANVAS_CLIENT_ID, CreateConnectionPage.CANVAS_LMS_URL,
                CreateConnectionPage.CANVAS_CLIENT_SECRET, CreateConnectionPage.CANVAS_DATA_CLIENT_ID, CreateConnectionPage.CANVAS_DATA_CLIENT_SECRET);
        $x("//a[contains (text(), '" + connectionName + "')]").exists();

        LoginCanvasPage.init()
                .fillEmail(CreateConnectionPage.CANVAS_USER_LOGIN)
                .fillPassword(CreateConnectionPage.CANVAS_USER_PASS)
                .loginInCanvas()
                .confirmAuthorize()
                .saveFilterSettings();

        open(CREATE_ELLUCIAN_BANNER_CONNECTION);

        EllucianConnectionPage.init()
                .selectConnection(connectionName)
                .fillInEllucianToken(CreateConnectionPage.ELLUCIAN_BANNER_KEY)
                .submitForm();

        assertThat(ConnectionsListPage.init().checkIntegration(ConnectionIntegrationType.ELLUCIAN_COLLEAGUE, connectionName))
                .isTrue()
                .as(String.format("Integration connection %s is not exist", ConnectionIntegrationType.ELLUCIAN_COLLEAGUE));

        ConnectionsListPage
                .init()
                .deleteConnection(connectionName);
    }

    @Test
    @Tags(value = {@Tag("high"), @Tag("SP-T1248")})
    @DisplayName("SP-T1248: Create Totara connection")
    public void testCreateTotaraConnection() {
        CreateConnectionPage createConnectionPage = new CreateConnectionPage();
        String connectionName = "Totara_" + DataGenerator.getRandomString();

        open(CREATE_TOTARA_CONNECTION);

        createConnectionPage.createTOTARAConnection(connectionName, CreateConnectionPage.TOTARA_URL, CreateConnectionPage.TOTARA_KEY);

        assertThat(ConnectionsListPage.init().findConnectionByName(connectionName).isConnectionExist(connectionName))
                .isTrue()
                .as(String.format("Connection : %s is not existed", connectionName));

        ConnectionsListPage
                .init()
                .deleteConnection(connectionName);
    }

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T1250")})
    @DisplayName("SP-T1250: Create MWP connection for moodle.intelliboard")
    public void testCreateMWPConnectionMoodle() {
        CreateConnectionPage createConnectionPage = new CreateConnectionPage();
        String connectionName = "MWP_Moodle_" + DataGenerator.getRandomString();

        open(CREATE_MWP_MOODLE_CONNECTION);

        createConnectionPage.createMoodleConnection(connectionName, CreateConnectionPage.MWP_KEY, CreateConnectionPage.MWP_URL)
                .saveFilterSettings();

        assertThat(ConnectionsListPage.init().findConnectionByName(connectionName).isConnectionExist(connectionName))
                .isTrue()
                .as(String.format("Connection : %s is not existed", connectionName));

        ConnectionsListPage
                .init()
                .deleteConnection(connectionName);
    }

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T1249")})
    @DisplayName("SP-T1249: Create MWP connection for moodleworkplace.intelliboard")
    public void testCreateMWPWorkspaceConnectionMoodle() {
        CreateConnectionPage createConnectionPage = new CreateConnectionPage();
        String connectionName = "MWP_MoodleWorkspace_" + DataGenerator.getRandomString();

        open(CREATE_MWP_MOODLE_CONNECTION);

        createConnectionPage.createMoodleConnection(connectionName, CreateConnectionPage.MWP_W_KEY, CreateConnectionPage.MWP_W_URL)
                .saveFilterSettings();

        assertThat(ConnectionsListPage.init().findConnectionByName(connectionName).isConnectionExist(connectionName))
                .isTrue()
                .as(String.format("Connection : %s is not existed", connectionName));

        ConnectionsListPage
                .init()
                .deleteConnection(connectionName);
    }

    @Test
    public void testSetActiveConnection(){

        String connectionName = "SAKAI";
        open(ALL_CONNECTIONS);

        ConnectionsListPage
                .init()
                .setActiveConnection(connectionName, true);

        HeaderConnectionManager
                .expandOpenConnectionManager()
                .selectConnection(connectionName);

    }


}
