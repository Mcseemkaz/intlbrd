package net.intelliboard.next.tests.core.connection;

import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.helpers.DataGenerator;
import net.intelliboard.next.services.pages.connections.ConnectionsListPage;
import net.intelliboard.next.services.pages.connections.CreateConnectionPage;
import net.intelliboard.next.services.pages.connections.LoginCanvasPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;
import static net.intelliboard.next.services.IBNextURLs.*;

@Tag("Connection_Processing")
public class ProcessConnectionTest extends IBNextAbstractTest {

    @Test
    @Tags(value = {@Tag("high"), @Tag("SP-T1120")})
    @DisplayName("SP-T1120: Processing D2L connection")
    public void testProcessConnectionD2L() throws InterruptedException {
        open(CREATE_D2L_CONNECTION);
        String connectionName = "D2L_" + DataGenerator.getRandomString();
        CreateConnectionPage.init()
                .createD2LConnection(connectionName, CreateConnectionPage.D2L_URL, CreateConnectionPage.D2L_CLIENT_ID,
                        CreateConnectionPage.D2L_CLIENT_SECRET, CreateConnectionPage.D2L_USER_LOGIN, CreateConnectionPage.D2L_USER_PASS);

        ConnectionsListPage connectionsListPage = ConnectionsListPage
                .init();

        connectionsListPage
                .editConnection(connectionName)
                .processData()
                .waitingProcessingComplete();

        open(ALL_CONNECTIONS);
        connectionsListPage.checkLastProcessing(connectionName, LocalDateTime.now());
        connectionsListPage.deleteConnection(connectionName);

    }

    @Test
    @Tags(value = {@Tag("high"), @Tag("SP-T1118")})
    @DisplayName("SP-T1118: Processing Moodle connection")
    public void testProcessConnectionMoodle() throws InterruptedException {
        open(CREATE_MOODLE_CONNECTION);
        String connectionName = "Moodle_" + DataGenerator.getRandomString();
        CreateConnectionPage.init()
                .createMoodleConnection(connectionName, CreateConnectionPage.MOODLE_CLIENT_ID, CreateConnectionPage.MOODLE_LMS_URL)
                .saveFilterSettings()
                .editConnection(connectionName)
                .processData()
                .waitingProcessingComplete();

        open(ALL_CONNECTIONS);
        ConnectionsListPage connectionsListPage = ConnectionsListPage
                .init();

        connectionsListPage.checkLastProcessing(connectionName, LocalDateTime.now());
        connectionsListPage.deleteConnection(connectionName);
    }

    @Test
    @Tags(value = {@Tag("high"), @Tag("SP-T1119")})
    @DisplayName("SP-T1119: Processing Canvas connection")
    public void testProcessConnectionCanvas() throws InterruptedException {
        open(CREATE_CANVAS_CONNECTION);
        String connectionName = "Canvas_" + DataGenerator.getRandomString();
        CreateConnectionPage.init().
                createCanvasConnection(connectionName, CreateConnectionPage.CANVAS_CLIENT_ID, CreateConnectionPage.CANVAS_LMS_URL,
                        CreateConnectionPage.CANVAS_CLIENT_SECRET, CreateConnectionPage.CANVAS_DATA_CLIENT_ID, CreateConnectionPage.CANVAS_DATA_CLIENT_SECRET);
        $x("//a[contains (text(), '" + connectionName + "')]").exists();

        LoginCanvasPage.init()
                .fillEmail(CreateConnectionPage.CANVAS_USER_LOGIN)
                .fillPassword(CreateConnectionPage.CANVAS_USER_PASS)
                .loginInCanvas()
                .confirmAuthorize()
                .saveFilterSettings()
                .editConnection(connectionName)
                .processData()
                .waitingProcessingComplete();

        open(ALL_CONNECTIONS);
        ConnectionsListPage connectionsListPage = ConnectionsListPage
                .init();

        connectionsListPage.checkLastProcessing(connectionName, LocalDateTime.now());
        connectionsListPage.deleteConnection(connectionName);
    }

    @Test
    @Tags(value = {@Tag("high"), @Tag("SP-T1112")})
    @DisplayName("SP-T1112: Processing Blackboard connection")
    public void testProcessConnectionBlackboard() throws InterruptedException {
        open(CREATE_BLACKBOARD_CONNECTION);
        String connectionName = "Blackboard_" + DataGenerator.getRandomString();
        CreateConnectionPage.init()
                .createBlackboardConnection(connectionName, CreateConnectionPage.BLACKBOARD_CLIENT_ID, CreateConnectionPage.BLACKBOARD_LMS_URL)
                .saveFilterSettings()
                .editConnection(connectionName)
                .processData()
                .waitingProcessingComplete();

        open(ALL_CONNECTIONS);
        ConnectionsListPage connectionsListPage = ConnectionsListPage
                .init();

        connectionsListPage.checkLastProcessing(connectionName, LocalDateTime.now());
        connectionsListPage.deleteConnection(connectionName);
    }

    @Test
    @Tags(value = {@Tag("high"), @Tag("SP-T1121")})
    @DisplayName("SP-T1121: Processing Zoom connection")
    public void testProcessConnectionZoom() throws InterruptedException {
        open(CREATE_ZOOM_CONNECTION);
        String connectionName = "Zoom_" + DataGenerator.getRandomString();
        CreateConnectionPage.init()
                .createZoomConnection(connectionName, CreateConnectionPage.ZOOM_TOKEN, CreateConnectionPage.ZOOM_SECRET)
                .editConnection(connectionName)
                .processData()
                .waitingProcessingComplete();

        open(ALL_CONNECTIONS);
        ConnectionsListPage connectionsListPage = ConnectionsListPage
                .init();

        connectionsListPage.checkLastProcessing(connectionName, LocalDateTime.now());
        connectionsListPage.deleteConnection(connectionName);
    }

    @Test
    @Tags(value = {@Tag("high"), @Tag("SP-T1122")})
    @DisplayName("SP-T1122: Processing Ilias connection")
    public void testProcessConnectionIlyas() throws InterruptedException {
        open(CREATE_ILIAS_CONNECTION);
        String connectionName = "Ilias_" + DataGenerator.getRandomString();
        CreateConnectionPage.init()
                .createILIASConnection(connectionName, CreateConnectionPage.ILIAS_URL, CreateConnectionPage.ILIAS_TOKEN,
                        CreateConnectionPage.ILIAS_KEY)
                .editConnection(connectionName)
                .processData()
                .waitingProcessingComplete();

        open(ALL_CONNECTIONS);
        ConnectionsListPage connectionsListPage = ConnectionsListPage
                .init();

        connectionsListPage.checkLastProcessing(connectionName, LocalDateTime.now());
        connectionsListPage.deleteConnection(connectionName);
    }

    @Test
    @Tags(value = {@Tag("high"), @Tag("SP-T1105")})
    @DisplayName("SP-T1105: Processing Sakai connection")
    public void testProcessConnectionSakai() throws InterruptedException {
        open(CREATE_SAKAI_CONNECTION);
        String connectionName = "Sakai_" + DataGenerator.getRandomString();
        CreateConnectionPage.init()
                .createSAKAIConnection(connectionName, CreateConnectionPage.SAKAI_URL, CreateConnectionPage.SAKAI_TOKEN,
                        CreateConnectionPage.SAKAI_KEY)
                .editConnection(connectionName)
                .processData()
                .waitingProcessingComplete();

        open(ALL_CONNECTIONS);
        ConnectionsListPage connectionsListPage = ConnectionsListPage
                .init();

        connectionsListPage.checkLastProcessing(connectionName, LocalDateTime.now());
        connectionsListPage.deleteConnection(connectionName);
    }
}
