package net.intelliboard.next.tests.core.connection;

import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.helpers.DataGenerator;
import net.intelliboard.next.services.pages.connections.ConnectionsListPage;
import net.intelliboard.next.services.pages.connections.connection.ConnectionProcessingFrequencyTypeEnum;
import net.intelliboard.next.services.pages.connections.connection.zoom.CreateZoomConnectionPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static net.intelliboard.next.services.IBNextURLs.CREATE_ZOOM_CONNECTION;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Tag("Connection Operations")
public class ConnectionOperationsTest extends IBNextAbstractTest {

    @Test
    @DisplayName("SP-T206: Deactivating connection by actions")
    @Tags(value = {@Tag("normal"), @Tag("SP-T206")})
    public void testDeactivateConnection() {

        String connectionName = "AQA_SP-T206_" + DataGenerator.getRandomString();

        open(CREATE_ZOOM_CONNECTION);

        CreateZoomConnectionPage.init().createZoomConnection(
                        connectionName,
                        CreateZoomConnectionPage.ZOOM_INDEPENDENT_CONNECTION_NAME,
                        CreateZoomConnectionPage.ZOOM_TOKEN,
                        CreateZoomConnectionPage.ZOOM_SECRET, ConnectionProcessingFrequencyTypeEnum.DAILY,
                        12)
                .findConnectionByName(connectionName)
                .selectConnection(connectionName, true);

        ConnectionsListPage.init()
                .deactivateSelectedConnectionByActionMenu();

        assertThat(ConnectionsListPage
                .init()
                .findConnectionByName(connectionName)
                .isConnectionActivation(connectionName, false))
                .isTrue();

        ConnectionsListPage
                .init()
                .deleteConnection(connectionName);
    }

    @Test
    @DisplayName("SP-T203: Deactivating connection by clicking at radiobutton")
    @Tags(value = {@Tag("normal"), @Tag("SP-T203")})
    public void testDeactivateConnectionByRadioButton() {

        String connectionName = "AQA_SP-T203_" + DataGenerator.getRandomString();

        open(CREATE_ZOOM_CONNECTION);

        CreateZoomConnectionPage.init().createZoomConnection(
                        connectionName,
                        CreateZoomConnectionPage.ZOOM_INDEPENDENT_CONNECTION_NAME,
                        CreateZoomConnectionPage.ZOOM_TOKEN,
                        CreateZoomConnectionPage.ZOOM_SECRET, ConnectionProcessingFrequencyTypeEnum.DAILY,
                        12)
                .findConnectionByName(connectionName);

        ConnectionsListPage.init()
                .setActiveConnection(connectionName, false);

        assertThat(ConnectionsListPage
                .init()
                .findConnectionByName(connectionName)
                .isConnectionActivation(connectionName, false))
                .isTrue();

        ConnectionsListPage
                .init()
                .deleteConnection(connectionName);
    }
}
