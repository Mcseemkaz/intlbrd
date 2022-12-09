package net.intelliboard.next.tests.core.connection;


import io.qameta.allure.Feature;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.helpers.DataGenerator;
import net.intelliboard.next.services.pages.connections.ConnectionsListPage;
import net.intelliboard.next.services.pages.connections.connection.zoom.CreateZoomConnectionPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static net.intelliboard.next.services.IBNextURLs.ALL_CONNECTIONS;
import static net.intelliboard.next.services.IBNextURLs.CREATE_ZOOM_CONNECTION;

@Feature("Process Connection")
@Tag("Connection_Processing")
public class ConnectionRegressionTest extends IBNextAbstractTest {

      @Test
    @Tags(value = {@Tag("high"), @Tag("SP-T1618")})
    @DisplayName("SP-T1618: Checking 'Go to Dashboard' button")
    public void testCheckBackDashboardButtonProcessingPage() throws InterruptedException {
        open(CREATE_ZOOM_CONNECTION);
        String connectionName = "Zoom_SP-T1618_" + DataGenerator.getRandomString();
        CreateZoomConnectionPage.init()
                .createZoomConnection(
                        connectionName,
                        CreateZoomConnectionPage.ZOOM_INDEPENDENT_CONNECTION_NAME,
                        CreateZoomConnectionPage.ZOOM_TOKEN,
                        CreateZoomConnectionPage.ZOOM_SECRET)
                .editConnection(connectionName)
                .processData()
                .waitingProcessingComplete()
                .backToDashBoardConnectionList();

        open(ALL_CONNECTIONS);
                ConnectionsListPage
                        .init()
                        .deleteConnection(connectionName);
        //connectionsListPage.checkLastProcessing(connectionName, LocalDateTime.now());
    }
}
