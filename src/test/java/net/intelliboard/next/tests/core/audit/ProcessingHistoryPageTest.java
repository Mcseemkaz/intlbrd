package net.intelliboard.next.tests.core.audit;

import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.IBNextURLs;
import net.intelliboard.next.services.pages.connections.AuditMainPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Tag("Connection_Processing_History")
public class ProcessingHistoryPageTest extends IBNextAbstractTest {

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T256")})
    @DisplayName("SP-T256: Audit pagination verification")
    public void testAuditPageVerification() {
        String connectionName = "Automation Canvans";
        open(IBNextURLs.AUDIT_PAGE);

        assertThat(
                AuditMainPage
                .init()
                .isConnectionExist(connectionName))
                .withFailMessage(String.format("Connection with name %s is not existed", connectionName))
                .isTrue();
    }
}
