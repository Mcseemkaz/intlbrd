package net.intelliboard.next.tests.core.connection;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Feature;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.helpers.DataGenerator;
import net.intelliboard.next.services.pages.connections.*;
import net.intelliboard.next.services.pages.connections.blackboardcollaborate.CreateBlackBoardCollaborateConnectionPage;
import net.intelliboard.next.services.pages.connections.connection.ConnectionProcessingFrequencyTypeEnum;
import net.intelliboard.next.services.pages.connections.connection.zoom.CreateZoomConnectionPage;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.time.LocalDateTime;

import static com.codeborne.selenide.Selenide.open;
import static net.intelliboard.next.services.IBNextURLs.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Feature("Create Connections")
@Tag("Create_Connections")
public class CreateConnectionTest extends IBNextAbstractTest {
    @Test
    @Tags(value = {@Tag("high"), @Tag("SP-T83"), @Tag("health")})
    @DisplayName("SP-T83: Creating of Moodle connection")
    public void testCreateMoodleConnection() {

        String connectionName = "Moodle_SP-T83_" + DataGenerator.getRandomString();
        open(CREATE_MOODLE_CONNECTION);
        CreateConnectionPage
                .init()
                .createMoodleConnection(
                        connectionName,
                        CreateConnectionPage.MOODLE_CLIENT_ID,
                        CreateConnectionPage.MOODLE_LMS_URL)
                .saveFilterSettings()
                .deleteConnection(connectionName);
    }

    @Test
    @Tags(value = {@Tag("high"), @Tag("SP-T89")})
    @DisplayName("SP-T89: Creating of Canvas connection")
    public void testCreateCanvasConnection() {

        String connectionName = "Canvas_SP-T89_" + DataGenerator.getRandomString();

        open(CREATE_CANVAS_CONNECTION);
        CreateConnectionPage
                .init()
                .createCanvasConnection(
                        connectionName,
                        CreateConnectionPage.CANVAS_CLIENT_ID,
                        CreateConnectionPage.CANVAS_LMS_URL,
                        CreateConnectionPage.CANVAS_CLIENT_SECRET,
                        CreateConnectionPage.CANVAS_DATA_CLIENT_ID,
                        CreateConnectionPage.CANVAS_DATA_CLIENT_SECRET);

        LoginCanvasPage.init()
                .fillEmail(CreateConnectionPage.CANVAS_USER_LOGIN)
                .fillPassword(CreateConnectionPage.CANVAS_USER_PASS)
                .loginInCanvas()
                .confirmAuthorize()
                .saveFilterSettings()
                .deleteConnection(connectionName);
    }

    @Test
    @Tags(value = {@Tag("high"), @Tag("SP-T599")})
    @DisplayName("SP-T599: Creating a Blackboard connection")
    public void testCreateBlackboardConnection() {

        String connectionName = "Blackboard_SP-T599_" + DataGenerator.getRandomString();

        open(CREATE_BLACKBOARD_CONNECTION);

        CreateConnectionPage
                .init()
                .createBlackboardConnection(
                        connectionName,
                        CreateConnectionPage.BLACKBOARD_CLIENT_ID,
                        CreateConnectionPage.BLACKBOARD_LMS_URL)
                .saveFilterSettings()
                .deleteConnection(connectionName);
    }

    @Test
    @Tags(value = {@Tag("high"), @Tag("SP-T106")})
    @DisplayName("SP-T106: Creating of Zoom independent connection")
    public void testCreateZoomConnection() {

        open(CREATE_ZOOM_CONNECTION);

        String connectionName = "Zoom_SP-T106_" + DataGenerator.getRandomString();
        CreateZoomConnectionPage
                .init()
                .createZoomConnection(
                        connectionName,
                        CreateZoomConnectionPage.ZOOM_INDEPENDENT_CONNECTION_NAME,
                        CreateZoomConnectionPage.ZOOM_TOKEN,
                        CreateZoomConnectionPage.ZOOM_SECRET);

        ConnectionsListPage connectionsListPage = ConnectionsListPage.init();

        connectionsListPage.findConnectionByName(connectionName);

        assertThat(connectionsListPage.isConnectionExist(connectionName))
                .isTrue()
                .as(String.format("Connection : %s is not existed", connectionName));

        connectionsListPage
                .deleteConnection(connectionName);
    }

    @Test
    @Tags(value = {@Tag("high"), @Tag("SP-T103")})
    @DisplayName("SP-T103: Creating of D2L connection")
    public void testCreateD2LConnection() {

        open(CREATE_D2L_CONNECTION);
        String connectionName = "D2L_SP-T103_" + DataGenerator.getRandomString();
        CreateConnectionPage
                .init()
                .createD2LConnection(
                        connectionName,
                        CreateConnectionPage.D2L_URL,
                        CreateConnectionPage.D2L_CLIENT_ID,
                        CreateConnectionPage.D2L_CLIENT_SECRET,
                        CreateConnectionPage.D2L_USER_LOGIN,
                        CreateConnectionPage.D2L_USER_PASS);

        ConnectionsListPage connectionsListPage = ConnectionsListPage.init();

        connectionsListPage.findConnectionByName(connectionName);

        assertThat(connectionsListPage.isConnectionExist(connectionName))
                .isTrue()
                .as(String.format("Connection : %s is not existed", connectionName));

        connectionsListPage
                .deleteConnection(connectionName);
    }

    @Test
    @Tags(value = {@Tag("high"), @Tag("SP-T104")})
    @DisplayName("SP-T104: Creating of Ilias connection")
    public void testCreateIliasConnection() {

        open(CREATE_ILIAS_CONNECTION);
        String connectionName = "Ilias_SP-T104_" + DataGenerator.getRandomString();
        CreateConnectionPage
                .init()
                .createILIASConnection(
                        connectionName,
                        CreateConnectionPage.ILIAS_URL,
                        CreateConnectionPage.ILIAS_TOKEN,
                        CreateConnectionPage.ILIAS_KEY);

        ConnectionsListPage connectionsListPage = ConnectionsListPage.init();

        connectionsListPage.findConnectionByName(connectionName);

        assertThat(connectionsListPage.isConnectionExist(connectionName))
                .isTrue()
                .as(String.format("Connection : %s is not existed", connectionName));

        connectionsListPage
                .deleteConnection(connectionName);
    }

    @Test
    @Tags(value = {@Tag("high"), @Tag("SP-T823")})
    @DisplayName("SP-T823: Creating of SAKAI connection")
    public void testCreateSAKAIConnection() {

        open(CREATE_SAKAI_CONNECTION);
        String connectionName = "SAKAI_SP-T823_" + DataGenerator.getRandomString();
        CreateConnectionPage
                .init()
                .createSAKAIConnection(
                        connectionName,
                        CreateConnectionPage.SAKAI_URL,
                        CreateConnectionPage.SAKAI_TOKEN,
                        CreateConnectionPage.SAKAI_KEY);

        ConnectionsListPage connectionsListPage = ConnectionsListPage.init();

        connectionsListPage.findConnectionByName(connectionName);

        assertThat(connectionsListPage.isConnectionExist(connectionName))
                .isTrue()
                .as(String.format("Connection : %s is not existed", connectionName));

        connectionsListPage
                .deleteConnection(connectionName);
    }

    @Test
    @Tags(value = {@Tag("high"), @Tag("SP-T814")})
    @DisplayName("SP-T814: Creating of Ellucian Colleague Sub-connection")
    public void testCreateEllucianColleagueSubConnection() {

        CreateConnectionPage createConnectionPage = new CreateConnectionPage();
        String connectionName = "SP-T814_Main_" + DataGenerator.getRandomString();

        open(CREATE_CANVAS_CONNECTION);
        createConnectionPage.createCanvasConnection(
                connectionName,
                CreateConnectionPage.CANVAS_CLIENT_ID,
                CreateConnectionPage.CANVAS_LMS_URL,
                CreateConnectionPage.CANVAS_CLIENT_SECRET,
                CreateConnectionPage.CANVAS_DATA_CLIENT_ID,
                CreateConnectionPage.CANVAS_DATA_CLIENT_SECRET);

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
                .selectProcessingFrequency(ConnectionProcessingFrequencyTypeEnum.DAILY)
                .selectProcessingTime(12)
                .submitForm();

        assertThat(
                ConnectionsListPage
                        .init()
                        .checkIntegration(ConnectionIntegrationTypeEnum.ELLUCIAN_COLLEAGUE, connectionName))
                .withFailMessage("Integration connection %s is not exist", ConnectionIntegrationTypeEnum.ELLUCIAN_COLLEAGUE.value)
                .isTrue();

        ConnectionsListPage
                .init()
                .deleteConnection(connectionName);
    }

    @Test
    @Tags(value = {@Tag("high"), @Tag("SP-T814 2")})
    @DisplayName("SP-T814 2: Creating of Ellucian Banner Sub-connection")
    public void testCreateEllucianBannerSubConnection() {

        CreateConnectionPage createConnectionPage = new CreateConnectionPage();
        String connectionName = "SP-T814_2_Main_" + DataGenerator.getRandomString();

        open(CREATE_CANVAS_CONNECTION);
        createConnectionPage.createCanvasConnection(
                connectionName,
                CreateConnectionPage.CANVAS_CLIENT_ID,
                CreateConnectionPage.CANVAS_LMS_URL,
                CreateConnectionPage.CANVAS_CLIENT_SECRET,
                CreateConnectionPage.CANVAS_DATA_CLIENT_ID,
                CreateConnectionPage.CANVAS_DATA_CLIENT_SECRET);

        LoginCanvasPage.init()
                .fillEmail(CreateConnectionPage.CANVAS_USER_LOGIN)
                .fillPassword(CreateConnectionPage.CANVAS_USER_PASS)
                .loginInCanvas()
                .confirmAuthorize();

        open(CREATE_ELLUCIAN_BANNER_CONNECTION);

        EllucianConnectionPage.init()
                .selectConnection(connectionName)
                .fillInEllucianToken(CreateConnectionPage.ELLUCIAN_BANNER_KEY)
                .selectProcessingFrequency(ConnectionProcessingFrequencyTypeEnum.DAILY)
                .selectProcessingTime(12)
                .submitForm();

        assertThat(ConnectionsListPage.init().checkIntegration(ConnectionIntegrationTypeEnum.ELLUCIAN_COLLEAGUE, connectionName))
                .withFailMessage("Integration connection %s is not exist", ConnectionIntegrationTypeEnum.ELLUCIAN_COLLEAGUE.value)
                .isTrue();

        ConnectionsListPage
                .init()
                .deleteConnection(connectionName);
    }

    @Test
    @Tags(value = {@Tag("high"), @Tag("SP-T1397")})
    @DisplayName("SP-T1397: Create Totara connection")
    public void testCreateTotaraConnection() {
        CreateConnectionPage createConnectionPage = new CreateConnectionPage();
        String connectionName = "Totara_SP-T1397_" + DataGenerator.getRandomString();

        open(CREATE_TOTARA_CONNECTION);

        createConnectionPage.createTOTARAConnection(connectionName, CreateConnectionPage.TOTARA_URL, CreateConnectionPage.TOTARA_KEY);

        assertThat(ConnectionsListPage.init().findConnectionByName(connectionName).isConnectionExist(connectionName))
                .withFailMessage("Connection : %s is not existed", connectionName)
                .isTrue();

        ConnectionsListPage
                .init()
                .deleteConnection(connectionName);
    }

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T1250")})
    @DisplayName("SP-T1250: Create MWP connection for moodle.intelliboard")
    public void testCreateMWPConnectionMoodle() {
        CreateConnectionPage createConnectionPage = new CreateConnectionPage();
        String connectionName = "MWP_Moodle_SP-T1250_" + DataGenerator.getRandomString();

        open(CREATE_MWP_MOODLE_CONNECTION);

        createConnectionPage.createMoodleConnection(
                        connectionName,
                        CreateConnectionPage.MWP_KEY,
                        CreateConnectionPage.MWP_URL)
                .saveFilterSettings();

        assertThat(ConnectionsListPage.init().findConnectionByName(connectionName).isConnectionExist(connectionName))
                .withFailMessage("Connection : %s is not existed", connectionName)
                .isTrue();

        ConnectionsListPage
                .init()
                .deleteConnection(connectionName);
    }

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T1249")})
    @DisplayName("SP-T1249, SP-T1398: Create MWP connection for moodleworkplace.intelliboard")
    public void testCreateMWPWorkspaceConnectionMoodle() {
        CreateConnectionPage createConnectionPage = new CreateConnectionPage();
        String connectionName = "MWP_MoodleWorkspace_SP-T1249" + DataGenerator.getRandomString();

        open(CREATE_MWP_MOODLE_CONNECTION);

        createConnectionPage.createMoodleConnection(
                        connectionName,
                        CreateConnectionPage.MWP_W_KEY,
                        CreateConnectionPage.MWP_W_URL)
                .saveFilterSettings();

        assertThat(ConnectionsListPage.init()
                .findConnectionByName(connectionName)
                .isConnectionExist(connectionName))
                .withFailMessage("Connection : %s is not existed", connectionName)
                .isTrue();

        ConnectionsListPage
                .init()
                .deleteConnection(connectionName);
    }

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T1370")})
    @DisplayName("SP-T1370: Create Qwickly connection")
    public void testCreateQwicklyConnection() throws IOException {
        CreateConnectionPage createConnectionPage = new CreateConnectionPage();
        String mainConnectionName = "Canvas_Main_SP-T1370" + DataGenerator.getRandomString();

        open(CREATE_CANVAS_CONNECTION);
        createConnectionPage.createCanvasConnection
                (
                        mainConnectionName,
                        CreateConnectionPage.CANVAS_CLIENT_ID,
                        CreateConnectionPage.CANVAS_LMS_URL,
                        CreateConnectionPage.CANVAS_CLIENT_SECRET,
                        CreateConnectionPage.CANVAS_DATA_CLIENT_ID,
                        CreateConnectionPage.CANVAS_DATA_CLIENT_SECRET);

        LoginCanvasPage.init()
                .fillEmail(CreateConnectionPage.CANVAS_USER_LOGIN)
                .fillPassword(CreateConnectionPage.CANVAS_USER_PASS)
                .loginInCanvas()
                .confirmAuthorize()
                .saveFilterSettings()
                .setActiveConnection(mainConnectionName, true);

        Selenide.sleep(Long.parseLong(propertiesGetValue.getPropertyValue("sleep_time")));

        open(CREATE_QWICKLY_CONNECTION);

        createConnectionPage
                .createQWICKLYConnection
                        (
                                mainConnectionName,
                                CreateConnectionPage.QWICKLY_URL,
                                CreateConnectionPage.QWICKLY_KEY,
                                CreateConnectionPage.QWICKLY_SECRET,
                                ConnectionProcessingFrequencyTypeEnum.DAILY,
                                12);

        assertThat(ConnectionsListPage.init()
                .findConnectionByName(mainConnectionName)
                .checkIntegration(ConnectionIntegrationTypeEnum.QWICKLY, mainConnectionName))
                .withFailMessage("Integration sub-connection for %s is not existed", mainConnectionName)
                .isTrue();

        ConnectionsListPage
                .init()
                .deleteConnection(mainConnectionName);
    }

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T1375")})
    @DisplayName("SP-T1375: Creating of Mangoose Cadence sub-connection")
    public void testCreateMongooseSubConnection() {
        CreateConnectionPage createConnectionPage = new CreateConnectionPage();
        //Main connection
        String mainConnectionName = "Moodle_Main_SP-T1375" + DataGenerator.getRandomString();

        // Mongoose connection
        String connectionName = "Mongoose_" + DataGenerator.getRandomString();

        open(CREATE_MOODLE_CONNECTION);
        createConnectionPage
                .createMoodleConnection
                        (
                                mainConnectionName,
                                CreateConnectionPage.MOODLE_CLIENT_ID,
                                CreateConnectionPage.MOODLE_LMS_URL)
                .saveFilterSettings();

        assertThat(ConnectionsListPage.init()
                .findConnectionByName(mainConnectionName)
                .isConnectionExist(mainConnectionName))
                .withFailMessage("Connection : %s is not existed", connectionName)
                .isTrue();

        open(CREATE_MONGOOSE_CONNECTION);

        createConnectionPage
                .createMongooseConnection
                        (
                                mainConnectionName,
                                CreateConnectionPage.MONGOOSE_API_KEY,
                                CreateConnectionPage.MONGOOSE_SECRET,
                                CreateConnectionPage.MONGOOSE_TEAM_CODE,
                                LocalDateTime.now(),
                                ConnectionProcessingFrequencyTypeEnum.DAILY,
                                12);

        assertThat(ConnectionsListPage.init()
                .checkIntegration(ConnectionIntegrationTypeEnum.MONGOOSE_CADENCE, mainConnectionName))
                .withFailMessage("Integration sub-connection for %s is not existed", mainConnectionName)
                .isTrue();

        ConnectionsListPage
                .init()
                .deleteConnection(mainConnectionName);
    }

    @Test
    @Tags(value = {@Tag("high"), @Tag("SP-T600")})
    @DisplayName("SP-T600: Creating of BlackBoard Collaborate independent connection")
    public void testCreateBlackBoardCollaborateIndependentConnection() {

        open(CREATE_BLACKBOARD_COLLABORATE_CONNECTION);

        String connectionName = "BBCollaborate_SP-T600_" + DataGenerator.getRandomString();
        CreateBlackBoardCollaborateConnectionPage
                .init()
                .createBBCollaborateConnection(
                        connectionName,
                        CreateBlackBoardCollaborateConnectionPage.BLACK_BOARD_COLLABORATE_INDEPENDENT_CONNECTION_NAME,
                        CreateBlackBoardCollaborateConnectionPage.BLACK_BOARD_COLLABORATE_API_KEY,
                        CreateBlackBoardCollaborateConnectionPage.BLACK_BOARD_COLLABORATE_SECRET,
                        CreateBlackBoardCollaborateConnectionPage.BLACK_BOARD_COLLABORATE_URL)
                .findConnectionByName(connectionName);

        ConnectionsListPage connectionsListPage = ConnectionsListPage.init();
        assertThat(connectionsListPage.
                isConnectionExist(connectionName))
                .withFailMessage("Connection : %s is not existed", connectionName)
                .isTrue();

        connectionsListPage
                .deleteConnection(connectionName);
    }

    @Test
    @Tags(value = {@Tag("high"), @Tag("SP-T604")})
    @DisplayName("SP-T604: Creating of BlackBoard Collaborate sub-connection")
    public void testCreateBlackBoardCollaborateSubConnection() throws IOException {

        open(CREATE_D2L_CONNECTION);
        String mainConnectionName = "D2L_Main_SP-T604_" + DataGenerator.getRandomString();
        CreateConnectionPage
                .init()
                .createD2LConnection(
                        mainConnectionName,
                        CreateConnectionPage.D2L_URL,
                        CreateConnectionPage.D2L_CLIENT_ID,
                        CreateConnectionPage.D2L_CLIENT_SECRET,
                        CreateConnectionPage.D2L_USER_LOGIN,
                        CreateConnectionPage.D2L_USER_PASS)
                .setActiveConnection(mainConnectionName, true);

        Selenide.sleep(Long.parseLong(propertiesGetValue.getPropertyValue("sleep_time")));

        open(CREATE_BLACKBOARD_COLLABORATE_CONNECTION);

        String connectionName = "BBCollaborate_" + DataGenerator.getRandomString();
        CreateBlackBoardCollaborateConnectionPage
                .init()
                .createBBCollaborateConnection(
                        connectionName,
                        mainConnectionName,
                        CreateBlackBoardCollaborateConnectionPage.BLACK_BOARD_COLLABORATE_API_KEY,
                        CreateBlackBoardCollaborateConnectionPage.BLACK_BOARD_COLLABORATE_SECRET,
                        CreateBlackBoardCollaborateConnectionPage.BLACK_BOARD_COLLABORATE_URL)
                .findConnectionByName(mainConnectionName);

        ConnectionsListPage connectionsListPage = ConnectionsListPage.init();
        assertThat(connectionsListPage.checkIntegration(ConnectionIntegrationTypeEnum.BLACK_BOARD_COLLABORATE, mainConnectionName))
                .isTrue()
                .as(String.format("Connection : %s is not existed", mainConnectionName));

        connectionsListPage
                .deleteConnection(mainConnectionName);
    }

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T1674")})
    @DisplayName("SP-T1674: Creating Hubspot as a sub-connection")
    public void testCreateHubspotSubConnection() {
        CreateConnectionPage createConnectionPage = new CreateConnectionPage();
        //Main connection
        String mainConnectionName = "Moodle_Main_SP-T1674_" + DataGenerator.getRandomString();

        open(CREATE_MOODLE_CONNECTION);
        createConnectionPage
                .createMoodleConnection
                        (
                                mainConnectionName,
                                CreateConnectionPage.MOODLE_CLIENT_ID,
                                CreateConnectionPage.MOODLE_LMS_URL)
                .saveFilterSettings();

        assertThat(
                ConnectionsListPage
                        .init()
                        .findConnectionByName(mainConnectionName)
                        .isConnectionExist(mainConnectionName))
                .withFailMessage("Connection : %s is not existed", mainConnectionName)
                .isTrue();

        open(CREATE_HUBSPOT_CONNECTION);

        createConnectionPage
                .createHubspotConnection
                        (
                                mainConnectionName,
                                CreateConnectionPage.HUBSPOT_ACCESS_TOKEN,
                                ConnectionProcessingFrequencyTypeEnum.DAILY,
                                12);

        assertThat(ConnectionsListPage
                .init()
                .checkIntegration(ConnectionIntegrationTypeEnum.HUBSPOT, mainConnectionName))
                .withFailMessage("Integration sub-connection for %s is not existed", mainConnectionName)
                .isTrue();

        ConnectionsListPage
                .init()
                .deleteConnection(mainConnectionName);
    }
}
