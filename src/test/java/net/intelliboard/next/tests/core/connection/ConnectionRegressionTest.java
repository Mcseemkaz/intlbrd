package net.intelliboard.next.tests.core.connection;

import io.qameta.allure.Feature;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.helpers.DataGenerator;
import net.intelliboard.next.services.pages.connections.ConnectionsListPage;
import net.intelliboard.next.services.pages.connections.CreateConnectionPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static net.intelliboard.next.services.IBNextURLs.*;

@Feature("Process Connection")
@Tag("Connection_Processing")
class ConnectionRegressionTest extends IBNextAbstractTest {

    @Test
    @Tags(value = {@Tag("high"), @Tag("SP-T1618")})
    @DisplayName("SP-T1618: Checking 'Go to Dashboard' button")
    void testCheckBackDashboardButtonProcessingPage() {
        String connectionName = "SP-T1618_" + DataGenerator.getRandomString();
        open(CREATE_MOODLE_CONNECTION);

        CreateConnectionPage.init()
                .createMoodleConnection(
                        connectionName,
                        CreateConnectionPage.MOODLE_CLIENT_ID,
                        CreateConnectionPage.MOODLE_LMS_URL)
                .saveFilterSettings()
                .editConnection(connectionName)
                .processData()
                .backToDashBoardConnectionList();

        open(ALL_CONNECTIONS);

        ConnectionsListPage
                .init()
                .deleteConnection(connectionName);
    }
}