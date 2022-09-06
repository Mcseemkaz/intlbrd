package net.intelliboard.next.tests.core.connection;

import com.codeborne.selenide.Selenide;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.helpers.DataGenerator;
import net.intelliboard.next.services.pages.connections.ConnectionsListPage;
import net.intelliboard.next.services.pages.connections.zoom.CreateZoomConnectionPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static net.intelliboard.next.services.IBNextURLs.CREATE_ZOOM_CONNECTION;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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
                        CreateZoomConnectionPage.ZOOM_SECRET)
                .findConnectionByName(connectionName)
                .selectConnection(connectionName, true);

        ConnectionsListPage.init()
                .deactivateConnectionByActionMenu();

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
