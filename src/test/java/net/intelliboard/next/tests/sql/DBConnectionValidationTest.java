package net.intelliboard.next.tests.sql;

import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.database.DBMainManagingService;
import net.intelliboard.next.services.database.DataBaseConnectorService;
import net.intelliboard.next.services.database.SQLQueries;
import net.intelliboard.next.services.helpers.DataGenerator;
import net.intelliboard.next.services.pages.connections.ConnectionsListPage;
import net.intelliboard.next.services.pages.connections.CreateConnectionPage;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import static com.codeborne.selenide.Selenide.open;
import static net.intelliboard.next.services.IBNextURLs.ALL_CONNECTIONS;
import static net.intelliboard.next.services.IBNextURLs.CREATE_BLACKBOARD_CONNECTION;
import static net.intelliboard.next.services.database.SQLQueries.ROWS_TEST_ONE_LIST;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Tag("DB_Connection_Validation")
public class DBConnectionValidationTest extends IBNextAbstractTest {

    @Test
    @DisplayName("SP-TXXXX: Validate Scenario #1 for BB Ultra")
    @Tags(value = {@Tag("normal"), @Tag("SP-TXXXX")})
    void testDBValidationBBUltraScenarioOne() throws InterruptedException, IOException, SQLException {

        //Create BB Ultra Connection
        //Processing connection
        String connectionName = "BB_Ultra_SQL-" + DataGenerator.getRandomString();
        String connectionID;
        open(CREATE_BLACKBOARD_CONNECTION);
        CreateConnectionPage
                .init()
                .createBlackboardConnection(
                        connectionName,
                        CreateConnectionPage.BLACKBOARD_ULTRA_CLIENT_ID,
                        CreateConnectionPage.BLACKBOARD_ULTRA_LMS_URL)
                .saveFilterSettings()
                .editConnection(connectionName)
                .processData()
                .waitingProcessingComplete();

        open(ALL_CONNECTIONS);
        ConnectionsListPage connectionsListPage = ConnectionsListPage
                .init();

        assertThat(connectionsListPage.checkLastProcessing(connectionName, LocalDateTime.now()))
                .withFailMessage("Connection %s has not been processed properly", connectionName)
                .isTrue();

        connectionsListPage
                .editConnection(connectionName);
        connectionID = DBMainManagingService.getDBIdFromConnectionSettingsUrl();
        // Migration BB before processing
//      BlackBoardMigrationService blackBoardMigrationService = new BlackBoardMigrationService();
//      blackBoardMigrationService.performMigrationProcess();

        //Executed query
        DataBaseConnectorService ds = new DataBaseConnectorService();
        ResultSet rs1 = ds.executeQuery(
                SQLQueries.GENERAL_REPORT_QUERY,
                "lms_" + connectionID + "_blackboard");

        //Validate rows table
        SoftAssertions softly = new SoftAssertions();

        while (rs1.next()) {
            String columnValue = rs1.getString("table_name");
            String record_number = rs1.getString("record_number");
            String schema_name = rs1.getString("schema_name");

            for (String value : ROWS_TEST_ONE_LIST) {

                if (schema_name.equals("public") && columnValue.equals(value)) {
                    softly.assertThat(Integer.parseInt(record_number) > 0)
                            .withFailMessage("Column %s has zero records", columnValue)
                            .isTrue();
                }
            }
        }
        softly.assertAll();
        //Clean Up
        rs1.close();
        ds.environmentCleanUp();
        open(ALL_CONNECTIONS);
        connectionsListPage.deleteConnection(connectionName);
    }
}