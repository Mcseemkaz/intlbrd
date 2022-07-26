package net.intelliboard.next.tests.core.connection;

import io.qameta.allure.Feature;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.helpers.DataGenerator;
import net.intelliboard.next.services.pages.connections.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static net.intelliboard.next.services.IBNextURLs.CREATE_ELLUCIAN_BANNER_CONNECTION;
import static net.intelliboard.next.services.IBNextURLs.CREATE_MOODLE_CONNECTION;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Feature("Delete Connections")
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

        assertThat(ConnectionsListPage.init().checkIntegration(ConnectionIntegrationType.ELLUCIAN_COLLEAGUE, connectionName))
                .isTrue()
                .as(String.format("Integration connection %s is not exist", ConnectionIntegrationType.ELLUCIAN_COLLEAGUE));

        ConnectionsListPage
                .init()
                .editConnection(connectionName)
                .expandEllucianSubConnectionArea()
                .deleteEllucianSubConnection();

        assertThat(EditConnectionPage.init().isEllucianConnectionExist())
                .isFalse();
    }
}
