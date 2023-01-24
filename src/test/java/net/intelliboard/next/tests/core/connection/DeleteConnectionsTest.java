package net.intelliboard.next.tests.core.connection;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Flaky;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.helpers.DataGenerator;
import net.intelliboard.next.services.pages.connections.*;
import net.intelliboard.next.services.pages.connections.connection.ConnectionConnectionSettingsMainPage;
import net.intelliboard.next.services.pages.connections.connection.ConnectionProcessingFrequencyTypeEnum;
import net.intelliboard.next.services.pages.connections.connection.zoom.CreateZoomConnectionPage;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.open;
import static net.intelliboard.next.services.IBNextURLs.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Feature("Delete Connections")
@Tag("Delete_Connections")
public class DeleteConnectionsTest extends IBNextAbstractTest {

    @Test
    @Tags(value = {@Tag("high"), @Tag("SP-T817")})
    @DisplayName("SP-T817: Deleting Ellucian connection")
    public void testDeleteEllucianConnection() {
        String connectionName = "SP-T817_Main_" + DataGenerator.getRandomString();

        open(CREATE_MOODLE_CONNECTION);
        CreateConnectionPage
                .init()
                .createMoodleConnection(
                        connectionName,
                        CreateConnectionPage.MOODLE_CLIENT_ID,
                        CreateConnectionPage.MOODLE_LMS_URL)
                .saveFilterSettings();

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
                .expandEllucianSubConnectionArea()
                .deleteEllucianSubConnection();

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

    @Flaky
    @Test
    @Tags(value = {@Tag("high"), @Tag("SP-T202")})
    @DisplayName("SP-T202: Deleting selected connections [connection mass deletion]")
    public void testDeleteSelectedConnection() throws IOException {

        int numberConnections = 3;
        ArrayList<String> connectionsList = new ArrayList<>();

        //Create connections
        while (numberConnections > 0) {
            String connectionName = "AQA_Mass_" + DataGenerator.getRandomString();
            open(CREATE_ZOOM_CONNECTION);

            CreateZoomConnectionPage.init().createZoomConnection(
                            connectionName,
                            CreateZoomConnectionPage.ZOOM_INDEPENDENT_CONNECTION_NAME,
                            CreateZoomConnectionPage.ZOOM_TOKEN,
                            CreateZoomConnectionPage.ZOOM_SECRET)
                    .findConnectionByName(connectionName);
            connectionsList.add(connectionName);
            numberConnections--;
        }

        open(ALL_CONNECTIONS + "?per_page=300");

        connectionsList.forEach(k -> ConnectionsListPage.init().selectConnection(k, true));

        ConnectionsListPage
                .init()
                .deleteSelectedConnectionsByActionDropdown();

        waitForPageLoaded();

        Selenide.sleep(Long.parseLong(propertiesGetValue.getPropertyValue("sleep_time_long")));

        SoftAssertions softly = new SoftAssertions();

        connectionsList
                .stream()
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
    @Tags(value = {@Tag("smoke"), @Tag("high"), @Tag("SP-T988")})
    @DisplayName("SP-T988: Deleting Canvas connection")
    @Description("Verify that Canvas connection can be successfully deleted")
    void testCreateCanvasConnection() {

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
    @Tags(value = {@Tag("smoke"), @Tag("high"), @Tag("SP-T1084")})
    @DisplayName("SP-T1084: Deleting ilias connection")
    @Description("Verify that ilias connection can be successfully deleted")
    void testCreateIliasConnection() {

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

        connectionsListPage.findConnectionByName(connectionName);

        assertThat(connectionsListPage.isConnectionExist(connectionName))
                .withFailMessage("Connection : %s is not existed", connectionName)
                .isTrue();

        connectionsListPage
                .deleteConnection(connectionName);
    }
}
