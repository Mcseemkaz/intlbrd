package net.intelliboard.next.tests.core.connection;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Feature;
import io.qameta.allure.Flaky;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.IBNextURLs;
import net.intelliboard.next.services.helpers.DataGenerator;
import net.intelliboard.next.services.pages.connections.*;
import net.intelliboard.next.services.pages.connections.connection.ConnectionConnectionSettingsMainPage;
import net.intelliboard.next.services.pages.connections.connection.zoom.CreateZoomConnectionPage;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

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
    public void testDeletEllucianConnection() {
        String connectionName = "Moodle_" + DataGenerator.getRandomString();

        open(CREATE_MOODLE_CONNECTION);
        CreateConnectionPage
                .init()
                .createMoodleConnection(connectionName, CreateConnectionPage.MOODLE_CLIENT_ID, CreateConnectionPage.MOODLE_LMS_URL)
                .saveFilterSettings();

        open(CREATE_ELLUCIAN_BANNER_CONNECTION);

        EllucianConnectionPage.init()
                .selectConnection(connectionName)
                .fillInEllucianToken(CreateConnectionPage.ELLUCIAN_BANNER_KEY)
                .submitForm();

        assertThat(ConnectionsListPage.init().checkIntegration(ConnectionIntegrationTypeEnum.ELLUCIAN_COLLEAGUE, connectionName))
                .isTrue()
                .as(String.format("Integration connection %s is not exist", ConnectionIntegrationTypeEnum.ELLUCIAN_COLLEAGUE));

        ConnectionsListPage
                .init()
                .editConnection(connectionName)
                .expandEllucianSubConnectionArea()
                .deleteEllucianSubConnection();

        assertThat(ConnectionConnectionSettingsMainPage.init().isEllucianConnectionExist())
                .isFalse();
    }

    @Flaky
    @Test
    @Tags(value = {@Tag("high"), @Tag("SP-T202")})
    @DisplayName("SP-T202: Deleting selected connections [connection mass deletion]")
    public void testDeleteSelectedConnection() {

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

        Selenide.sleep(5000);

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
}
