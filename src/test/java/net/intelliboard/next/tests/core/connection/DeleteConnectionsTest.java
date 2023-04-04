package net.intelliboard.next.tests.core.connection;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Flaky;
import io.qameta.allure.Link;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.helpers.DataGenerator;
import net.intelliboard.next.services.pages.connections.*;
import net.intelliboard.next.services.pages.connections.connection.ConnectionConnectionSettingsMainPage;
import net.intelliboard.next.services.pages.connections.connection.ConnectionProcessingFrequencyTypeEnum;
import net.intelliboard.next.services.pages.connections.connection.blackboard.CreateBlackBoardConnectionPage;
import net.intelliboard.next.services.pages.connections.connection.zoom.CreateZoomConnectionPage;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.open;
import static net.intelliboard.next.services.IBNextURLs.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Feature("Delete Connections")
@Tag("Delete_Connections")
class DeleteConnectionsTest extends IBNextAbstractTest {

    @Test
    @Tags(value = {@Tag("high"), @Tag("SP-T817")})
    @DisplayName("SP-T817: Deleting Ellucian connection")
    void testDeleteEllucianConnection() {
        String connectionName = "SP-T817_Main_" + DataGenerator.getRandomString();

        open(CREATE_MOODLE_CONNECTION);
        CreateConnectionPage
                .init()
                .createMoodleConnection(
                        connectionName,
                        CreateConnectionPage.MOODLE_CLIENT_ID,
                        CreateConnectionPage.MOODLE_LMS_URL)
                .saveFilterSettings()
                .setActiveConnection(connectionName, true);

        waitForPageLoaded();

        open(CREATE_ELLUCIAN_BANNER_CONNECTION);

        EllucianConnectionPage
                .init()
                .selectConnection(connectionName)
                .fillInEllucianToken(CreateConnectionPage.ELLUCIAN_BANNER_KEY)
                .selectProcessingFrequency(ConnectionProcessingFrequencyTypeEnum.DAILY)
                .selectProcessingTime(12)
                .submitForm();

        assertThat(
                ConnectionsListPage
                        .init()
                        .checkIntegration(ConnectionIntegrationTypeEnum.ELLUCIAN_COLLEAGUE, connectionName))
                .withFailMessage("Integration connection %s is not exist", ConnectionIntegrationTypeEnum.ELLUCIAN_COLLEAGUE)
                .isTrue();

        ConnectionsListPage
                .init()
                .editConnection(connectionName)
                .deleteEllucianSubConnection();

        Selenide.sleep(SLEEP_TIMEOUT_SHORT);

        assertThat(
                ConnectionConnectionSettingsMainPage
                        .init()
                        .isEllucianConnectionExist())
                .isFalse();

        open(ALL_CONNECTIONS);

        ConnectionsListPage
                .init()
                .deleteConnection(connectionName);
    }

//    @Disabled("Create connection has a bug with the fields")
    @Link("https://intelliboard.atlassian.net/browse/SP-9506")
    @Flaky
    @Test
    @Tags(value = {@Tag("high"), @Tag("SP-T202")})
    @DisplayName("SP-T202: Deleting selected connections [connection mass deletion]")
    void testDeleteSelectedConnection() {

        int numberConnections = 3;
        ArrayList<String> connectionsList = new ArrayList<>();

        //Create connections
        while (numberConnections > 0) {
            String connectionName = "SP-T202_" + DataGenerator.getRandomString();
            open(CREATE_ZOOM_CONNECTION);

            CreateZoomConnectionPage.init().createZoomConnection(
                            connectionName,
                            CreateZoomConnectionPage.ZOOM_INDEPENDENT_CONNECTION_NAME,
                            CreateZoomConnectionPage.ZOOM_TOKEN,
                            CreateZoomConnectionPage.ZOOM_SECRET)
                    .searchConnectionByName(connectionName);
            connectionsList.add(connectionName);
            numberConnections--;
        }

        open(ALL_CONNECTIONS + "?per_page=300");

        connectionsList.forEach(k -> ConnectionsListPage.init().selectConnection(k, true));

        ConnectionsListPage
                .init()
                .deleteSelectedConnectionsByActionDropdown();

        waitForPageLoaded();

        Selenide.sleep(SLEEP_TIMEOUT_LONG);

        SoftAssertions softly = new SoftAssertions();

        connectionsList
                .forEach(k ->
                        softly.assertThat(
                                        ConnectionsListPage
                                                .init()
                                                .isConnectionExist(k))
                                .withFailMessage("Connection %s is still existed", (k))
                                .isFalse());

        softly.assertAll();
    }

    @Test
    @Tags(value = {@Tag("smoke"), @Tag("high"), @Tag("SP-T988"), @Tag("smoke_core")})
    @DisplayName("SP-T988: Deleting Canvas connection")
    @Description("Verify that Canvas connection can be successfully deleted")
    void testDeleteCanvasConnection() {

        String connectionName = "Canvas_SP-T988_" + DataGenerator.getRandomString();

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
    @Tags(value = {@Tag("smoke"), @Tag("high"), @Tag("SP-T1084"), @Tag("smoke_core")})
    @DisplayName("SP-T1084: Deleting ilias connection")
    @Description("Verify that ilias connection can be successfully deleted")
    void testDeleteIliasConnection() {

        open(CREATE_ILIAS_CONNECTION);
        String connectionName = "Ilias_SP-T1084_" + DataGenerator.getRandomString();
        CreateConnectionPage
                .init()
                .createILIASConnection(
                        connectionName,
                        CreateConnectionPage.ILIAS_URL,
                        CreateConnectionPage.ILIAS_TOKEN,
                        CreateConnectionPage.ILIAS_KEY);

        ConnectionsListPage connectionsListPage = ConnectionsListPage.init();

        connectionsListPage.searchConnectionByName(connectionName);

        assertThat(connectionsListPage.isConnectionExist(connectionName))
                .withFailMessage("Connection : %s is not existed", connectionName)
                .isTrue();

        connectionsListPage
                .deleteConnection(connectionName);
    }

    @Test
    @Tags(value = {@Tag("high"), @Tag("SP-T1083"), @Tag("smoke"), @Tag("smoke_core")})
    @DisplayName("SP-T1083: Deleting BlackBoard connection")
    void testDeleteBlackboardConnection() {

        String connectionName = "SP-T1083_" + DataGenerator.getRandomString();

        open(CREATE_BLACKBOARD_CONNECTION);

        CreateBlackBoardConnectionPage
                .init()
                .createBlackboardConnection(
                        connectionName,
                        CreateBlackBoardConnectionPage.BLACKBOARD_CLIENT_ID,
                        CreateBlackBoardConnectionPage.BLACKBOARD_LMS_URL)
                .saveFilterSettings()
                .deleteConnection(connectionName);
    }

    @Test
    @Tags(value = {@Tag("high"), @Tag("SP-T1083"), @Tag("smoke"), @Tag("smoke_core")})
    @DisplayName("SP-T1083: Deleting BlackBoard ULTRA connection")
    void testDeleteBlackboardULTRAConnection() {

        String connectionName = "SP-T1083_" + DataGenerator.getRandomString();

        open(CREATE_BLACKBOARD_CONNECTION);

        CreateBlackBoardConnectionPage
                .init()
                .createBlackboardConnection(
                        connectionName,
                        CreateBlackBoardConnectionPage.BLACKBOARD_ULTRA_CLIENT_ID,
                        CreateBlackBoardConnectionPage.BLACKBOARD_ULTRA_LMS_URL)
                .saveFilterSettings()
                .deleteConnection(connectionName);
    }

    @Test
    @Tags(value = {@Tag("high"), @Tag("SP-T1082"), @Tag("smoke"), @Tag("smoke_core")})
    @DisplayName("SP-T1082: Deleting D2L connection")
    @Description("Verify that D2L connection can be successfully deleted")
    void testDeleteD2LConnection() {

        open(CREATE_D2L_CONNECTION);
        String connectionName = "D2L_SP-T1082_" + DataGenerator.getRandomString();
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

        connectionsListPage.searchConnectionByName(connectionName);

        assertThat(connectionsListPage.isConnectionExist(connectionName))
                .withFailMessage("Connection : %s is not existed", connectionName)
                .isTrue();

        connectionsListPage
                .deleteConnection(connectionName);
    }

    @Test
    @Tags(value = {@Tag("high"), @Tag("SP-T1081"), @Tag("health"), @Tag("smoke_core")})
    @DisplayName("SP-T1081: Deleting moodle connection")
    @Description("Verify that moodle connection can be successfully deleted")
    void testDeleteMoodleConnection() {

        String connectionName = "Moodle_SP-T1081_" + DataGenerator.getRandomString();
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
    @Tags(value = {@Tag("high"), @Tag("SP-T1086"), @Tag("smoke_core")})
    @DisplayName("SP-T1086: Deleting Sakai connection")
    void testDeleteSAKAIConnection() {

        open(CREATE_SAKAI_CONNECTION);
        String connectionName = "SAKAI_SP-T1086_" + DataGenerator.getRandomString();
        CreateConnectionPage
                .init()
                .createSAKAIConnection(
                        connectionName,
                        CreateConnectionPage.SAKAI_URL,
                        CreateConnectionPage.SAKAI_TOKEN
                );

        ConnectionsListPage connectionsListPage = ConnectionsListPage.init();

        connectionsListPage.searchConnectionByName(connectionName);

        assertThat(connectionsListPage.isConnectionExist(connectionName))
                .withFailMessage("Connection : %s is not existed", connectionName)
                .isTrue();

        connectionsListPage
                .deleteConnection(connectionName);
    }
}