package net.intelliboard.next.tests.core.connection;

import io.qameta.allure.Feature;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.helpers.DataGenerator;
import net.intelliboard.next.services.pages.connections.*;
import net.intelliboard.next.services.pages.connections.zoom.CreateZoomConnectionPage;
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

        assertThat(EditConnectionPage.init().isEllucianConnectionExist())
                .isFalse();
    }

    @Test
    @Tags(value = {@Tag("high"), @Tag("SP-T202")})
    @DisplayName("SP-T202: Deleting selected connections [connection mass deletion]")
    public void testDeleteSelectedConnection() {

        int numberConnections = 5;
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

        connectionsList.forEach( k -> ConnectionsListPage.init().findConnectionByName(k).selectConnection(k, true));

        ConnectionsListPage.init().deleteSelectedConnectionsByActionDropdown();

        assertThat(connectionsList.stream().allMatch(k -> !(ConnectionsListPage.init().isConnectionExist(k))))
                .isTrue();
    }
}
