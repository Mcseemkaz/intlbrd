package net.intelliboard.next.tests.sql;

import groovyjarjarantlr4.v4.misc.OrderedHashMap;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.database.DBMainManagingService;
import net.intelliboard.next.services.database.DataBaseConnectorService;
import net.intelliboard.next.services.database.SQLQueries;
import net.intelliboard.next.services.helpers.DataGenerator;
import net.intelliboard.next.services.pages.connections.ConnectionsListPage;
import net.intelliboard.next.services.pages.connections.CreateConnectionPage;
import net.intelliboard.next.services.pages.connections.connection.ConnectionAdvancedSettingsMainPage;
import net.intelliboard.next.services.pages.connections.connection.ConnectionTabsEnum;
import net.intelliboard.next.services.pages.connections.connection.MainConnectionPage;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;
import static net.intelliboard.next.services.IBNextURLs.ALL_CONNECTIONS;
import static net.intelliboard.next.services.IBNextURLs.CREATE_BLACKBOARD_CONNECTION;
import static net.intelliboard.next.services.database.SQLQueries.*;

@Tag("DB_Connection_Validation")
public class DBConnectionValidationTest extends IBNextAbstractTest {

    @Test
    @DisplayName("SP-TXXXX: Check not empty tables for BB Ultra")
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

//        assertThat(connectionsListPage.checkLastProcessing(connectionName, LocalDateTime.now()))
//                .withFailMessage("Connection %s has not been processed properly", connectionName)
//                .isTrue();

        connectionsListPage
                .editConnection(connectionName);
        connectionID = DBMainManagingService.getDBIdFromConnectionSettingsUrl();
        System.out.println("-----------------------------------" + connectionID + "___________________");
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

    @Test
    @DisplayName("SP-TXXXX: Check not empty tables for BB Ultra")
    @Tags(value = {@Tag("normal"), @Tag("SP-TXXXX")})
    void testDBValidationBBUltraScenarioTwo() throws InterruptedException, IOException, SQLException {

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

//        assertThat(connectionsListPage.checkLastProcessing(connectionName, LocalDateTime.now()))
//                .withFailMessage("Connection %s has not been processed properly", connectionName)
//                .isTrue();

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
            int validRecordNumber = 0;

            for (String value : ROWS_TEST_ONE_LIST) {

                if (schema_name.equals("public") && columnValue.equals(value)) {

                    switch (columnValue) {
                        case ("users"):
                            validRecordNumber = 20000;
                            break;
                        case ("courses"):
                            validRecordNumber = 6000;
                            break;
                        case ("activities"):
                            validRecordNumber = 40000;
                            break;
                        case ("user_tracking_total"):
                            validRecordNumber = 10000;
                            break;
                        case ("grade_objects"):
                            validRecordNumber = 50000;
                            break;
                        case ("grade_objects_results"):
                            validRecordNumber = 30000;
                            break;
                        case ("login_history"):
                            validRecordNumber = 100000;
                            break;
                    }
                    softly.assertThat(Integer.parseInt(record_number) > validRecordNumber)
                            .withFailMessage("Column %s has less records than expected - %s", columnValue, validRecordNumber)
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

    @Test
    @DisplayName("SP-TXXXX: Check amount of records in incontact tables")
    @Tags(value = {@Tag("normal"), @Tag("SP-TXXXX")})
    void testDBValidationBBUltraScenarioThree() throws InterruptedException, IOException, SQLException {

        //Create BB Ultra Connection
        //Processing connection
        String connectionName = "BB_Ultra_SQL-3-" + DataGenerator.getRandomString();
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
                .openSettingsTab(ConnectionTabsEnum.ADVANCED_SETTINGS);

        ConnectionAdvancedSettingsMainPage
                .init()
                .setIncontact();

        //TODO MO - should be refactored save settings
        MainConnectionPage mainConnectionPage = new MainConnectionPage();
        mainConnectionPage
                .saveConnectionSettings()
                .editConnection(connectionName)
                .processData()
                .waitingProcessingComplete();

        open(ALL_CONNECTIONS);
        ConnectionsListPage connectionsListPage = ConnectionsListPage
                .init();

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

        OrderedHashMap<String, String> listOne = new OrderedHashMap<>();
        OrderedHashMap<String, String> listTwo = new OrderedHashMap<>();

        while (rs1.next()) {
            String columnValue = rs1.getString("table_name");
            String record_number = rs1.getString("record_number");
            String schema_name = rs1.getString("schema_name");

            for (String value : ROWS_TEST_THREE_LIST_ONE) {
                if (schema_name.equals("public") && columnValue.equals(value)) {
                    listOne.put(value, record_number);
                }
            }

            for (String value : ROWS_TEST_THREE_LIST_TWO) {
                if (schema_name.equals("public") && columnValue.equals(value)) {
                    listTwo.put(value, record_number);
                }
            }
        }

        for (int i = 0; i < listOne.size(); i++) {

            softly.assertThat(listOne.getElement(i).equals(listTwo.getElement(i)))
                    .withFailMessage("Number of records for column %s and %s not equal", listOne.getElement(i), listTwo.getElement(i))
                    .isTrue();
        }
        softly.assertAll();
        //Clean Up
        rs1.close();
        ds.environmentCleanUp();
        open(ALL_CONNECTIONS);
        connectionsListPage.deleteConnection(connectionName);
    }
}
