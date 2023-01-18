package net.intelliboard.next.tests.core.audit;

import io.qameta.allure.Description;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.IBNextURLs;
import net.intelliboard.next.services.pages.connections.ConnectionProcessingHistoryMainPage;
import net.intelliboard.next.services.pages.connections.ConnectionsListPage;
import net.intelliboard.next.services.pages.connections.ConnectionsTypeEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Tag("Connection_Processing_History")
class ProcessingHistoryPageTest extends IBNextAbstractTest {

    String connectionName = ConnectionsTypeEnum.CANVAS.defaultName;

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T256")})
    @DisplayName("SP-T256: Audit pagination verification")
    void testAuditPageVerification() {

        open(IBNextURLs.AUDIT_PAGE);

        assertThat(
                ConnectionProcessingHistoryMainPage
                        .init()
                        .isConnectionExist(connectionName))
                .withFailMessage(String.format("Connection with name %s is not existed", connectionName))
                .isTrue();
    }

    @Test
    @Tags(value = {@Tag("smoke"), @Tag("normal"), @Tag("SP-T1004")})
    @DisplayName("SP-T1004: Checking Status of connection")
    @Description("Verify that connection status is displayed correctly")
    void testCheckingConnectionStatus() {

        String connectionStatus = "Completed";

        open(IBNextURLs.ALL_CONNECTIONS);

        ConnectionsListPage
                .init()
                .openProcessingConnectionsHistory();

        assertThat(ConnectionProcessingHistoryMainPage
                .init()
                .checkConnectionStatus(connectionName)
                .contains(connectionStatus))
                .withFailMessage("Connection %s has no %s status", connectionName, connectionStatus)
                .isTrue();
    }
}