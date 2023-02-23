package net.intelliboard.next.tests.core.connection;

import io.qameta.allure.Link;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.helpers.DataGenerator;
import net.intelliboard.next.services.pages.connections.ConnectionsListPage;
import net.intelliboard.next.services.pages.connections.connection.ConnectionProcessingFrequencyTypeEnum;
import net.intelliboard.next.services.pages.connections.connection.zoom.CreateZoomConnectionPage;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Selenide.open;
import static net.intelliboard.next.services.IBNextURLs.CREATE_ZOOM_CONNECTION;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Tag("Connection Operations")
class ConnectionOperationsTest extends IBNextAbstractTest {

    @Disabled("Create connection has a bug with the fields")
    @Link("https://intelliboard.atlassian.net/browse/SP-9506")
    @Test
    @DisplayName("SP-T206: Deactivating connection by actions")
    @Tags(value = {@Tag("normal"), @Tag("SP-T206")})
    void testDeactivateConnection() {

        String connectionName = "SP-T206_" + DataGenerator.getRandomString();

        open(CREATE_ZOOM_CONNECTION);

        CreateZoomConnectionPage.init().createZoomConnection(
                        connectionName,
                        CreateZoomConnectionPage.ZOOM_INDEPENDENT_CONNECTION_NAME,
                        CreateZoomConnectionPage.ZOOM_TOKEN,
                        CreateZoomConnectionPage.ZOOM_SECRET, ConnectionProcessingFrequencyTypeEnum.DAILY,
                        12)
                .searhConnectionByName(connectionName)
                .selectConnection(connectionName, true);

        ConnectionsListPage.init()
                .deactivateSelectedConnectionByActionMenu();

        assertThat(ConnectionsListPage
                .init()
                .searhConnectionByName(connectionName)
                .isConnectionActivation(connectionName, false))
                .isTrue();

        ConnectionsListPage
                .init()
                .deleteConnection(connectionName);
    }

    @Disabled("Create connection has a bug with the fields")
    @Link("https://intelliboard.atlassian.net/browse/SP-9506")
    @Test
    @DisplayName("SP-T203: Deactivating connection by clicking at radiobutton")
    @Tags(value = {@Tag("normal"), @Tag("SP-T203")})
    void testDeactivateConnectionByRadioButton() {

        String connectionName = "SP-T203_" + DataGenerator.getRandomString();

        open(CREATE_ZOOM_CONNECTION);

        CreateZoomConnectionPage
                .init()
                .createZoomConnection(
                        connectionName,
                        CreateZoomConnectionPage.ZOOM_INDEPENDENT_CONNECTION_NAME,
                        CreateZoomConnectionPage.ZOOM_TOKEN,
                        CreateZoomConnectionPage.ZOOM_SECRET,
                        ConnectionProcessingFrequencyTypeEnum.DAILY,
                        12)
                .searhConnectionByName(connectionName);

        ConnectionsListPage.init()
                .setActiveConnection(connectionName, false);

        assertThat(ConnectionsListPage
                .init()
                .searhConnectionByName(connectionName)
                .isConnectionActivation(connectionName, false))
                .isTrue();

        ConnectionsListPage
                .init()
                .deleteConnection(connectionName);
    }
}
