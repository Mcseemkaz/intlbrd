package net.intelliboard.next.tests.core.connection;

import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.helpers.DataGenerator;
import net.intelliboard.next.services.pages.connections.ConnectionsListPage;
import net.intelliboard.next.services.pages.connections.CreateConnectionPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static com.codeborne.selenide.Selenide.open;
import static net.intelliboard.next.services.IBNextURLs.*;

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
}
