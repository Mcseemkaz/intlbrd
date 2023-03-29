package net.intelliboard.next.tests.sql;

import groovyjarjarantlr4.v4.misc.OrderedHashMap;
import io.qameta.allure.Feature;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.database.DBMainManagingService;
import net.intelliboard.next.services.database.DataBaseConnectorService;
import net.intelliboard.next.services.database.SQLQueries;
import net.intelliboard.next.services.helpers.DataGenerator;
import net.intelliboard.next.services.pages.connections.ConnectionsListPage;
import net.intelliboard.next.services.pages.connections.connection.ConnectionAdvancedSettingsMainPage;
import net.intelliboard.next.services.pages.connections.connection.ConnectionTabsEnum;
import net.intelliboard.next.services.pages.connections.connection.MainConnectionPage;
import net.intelliboard.next.services.pages.connections.connection.blackboard.CreateBlackBoardConnectionPage;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;
import static net.intelliboard.next.services.IBNextURLs.ALL_CONNECTIONS;
import static net.intelliboard.next.services.IBNextURLs.CREATE_BLACKBOARD_CONNECTION;
import static net.intelliboard.next.services.database.SQLQueries.*;

@Feature("DB Connection Validation")
@Tag("DB_Connection_Validation")
class DBConnectionValidationTest extends IBNextAbstractTest {

    @Test
    @Disabled("Need clarify requirements")
    @DisplayName("SP-TXXXX120: Check not empty tables for BB Ultra")
    @Tags(value = {@Tag("normal"), @Tag("SP-TXXXX120")})
    void testDBValidationBBUltraScenarioOne() throws InterruptedException, IOException, SQLException {

        //Create BB Ultra Connection
        //Processing connection
        String connectionName = "BB_Ultra_SQL-" + DataGenerator.getRandomString();
        String connectionID;
        open(CREATE_BLACKBOARD_CONNECTION);
        CreateBlackBoardConnectionPage
                .init()
                .createBlackboardConnection(
                        connectionName,
                        CreateBlackBoardConnectionPage.BLACKBOARD_ULTRA_CLIENT_ID,
                        CreateBlackBoardConnectionPage.BLACKBOARD_ULTRA_LMS_URL)
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
    @Disabled("Need clarify requirements")
    @DisplayName("SP-TXXXX121: Check amount of records in base tables")
    @Tags(value = {@Tag("normal"), @Tag("SP-TXXXX121")})
    void testDBValidationBBUltraScenarioTwo() throws InterruptedException, IOException, SQLException {

        //Create BB Ultra Connection
        //Processing connection
        String connectionName = "BB_Ultra_SQL-" + DataGenerator.getRandomString();
        String connectionID;
        open(CREATE_BLACKBOARD_CONNECTION);
        CreateBlackBoardConnectionPage
                .init()
                .createBlackboardConnection(
                        connectionName,
                        CreateBlackBoardConnectionPage.BLACKBOARD_ULTRA_CLIENT_ID,
                        CreateBlackBoardConnectionPage.BLACKBOARD_ULTRA_LMS_URL)
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
    @Disabled("Need clarify requirements")
    @DisplayName("SP-TXXXX122: Check amount of records in incontact tables")
    @Tags(value = {@Tag("normal"), @Tag("SP-TXXXX122")})
    void testDBValidationBBUltraScenarioThree() throws InterruptedException, IOException, SQLException {

        //Create BB Ultra Connection
        //Processing connection
        String connectionName = "BB_Ultra_SQL-3-" + DataGenerator.getRandomString();
        String connectionID;
        open(CREATE_BLACKBOARD_CONNECTION);
        CreateBlackBoardConnectionPage
                .init()
                .createBlackboardConnection(
                        connectionName,
                        CreateBlackBoardConnectionPage.BLACKBOARD_ULTRA_CLIENT_ID,
                        CreateBlackBoardConnectionPage.BLACKBOARD_ULTRA_LMS_URL)
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

    //TODO [MO] Remove debug printing after stabilize
    @Test
    @Disabled("Need clarify requirements")
    @DisplayName("SP-TXXXX123: Check not empty tables for BB Ultra")
    @Tags(value = {@Tag("normal"), @Tag("SP-TXXXX123")})
    void testDBValidationBBUltraScenarioFour() throws InterruptedException, IOException, SQLException {

        //Create BB Ultra Connection
        //Processing connection
        String connectionName = "BB_Ultra_SQL-4-" + DataGenerator.getRandomString();
        String connectionID;
        open(CREATE_BLACKBOARD_CONNECTION);
        CreateBlackBoardConnectionPage
                .init()
                .createBlackboardConnection(
                        connectionName,
                        CreateBlackBoardConnectionPage.BLACKBOARD_ULTRA_CLIENT_ID,
                        CreateBlackBoardConnectionPage.BLACKBOARD_ULTRA_LMS_URL)
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
                FULL_REPORT_QUERY,
                "lms_" + connectionID + "_blackboard");

        SoftAssertions softly = new SoftAssertions();
        int i = 0;

        while (rs1.next()) {
            System.out.print(rs1.getString("table_name") + " | ");
            System.out.print(rs1.getString("type") + " | ");
            System.out.print(rs1.getString("type") + " | ");
            System.out.print(rs1.getString("column_name") + " | ");
            System.out.print(rs1.getString("null_values") + " | ");
            System.out.println(rs1.getString("total") + " | ");

            for (String[] value : TEST_QUERY) {
                if (rs1.getString("table_name").equals(value[0]) &&
                        rs1.getString("column_name").equals(value[1])) {
                    softly.assertThat(Integer.parseInt(rs1.getString("not_null_values")) > 0)
                            .withFailMessage(" Row %s %s is empty !", rs1.getString("table_name"), rs1.getString("column_name"))
                            .isTrue();
                    i++;
                }
            }
        }

        softly.assertAll();
        System.out.println(i);
        rs1.close();
        ds.environmentCleanUp();
    }

    @Test
    @DisplayName("SP-TXXXX124: Check categories.parent_id column")
    @Tags(value = {@Tag("normal"), @Tag("SP-TXXXX124")})
    void testDBValidationBBUltraScenarioFive() throws InterruptedException, IOException, SQLException {
        //Create BB Ultra Connection
        //Processing connection
        String connectionName = "BB_Ultra_SQL-5-" + DataGenerator.getRandomString();
        String connectionID;
        open(CREATE_BLACKBOARD_CONNECTION);
        CreateBlackBoardConnectionPage
                .init()
                .createBlackboardConnection(
                        connectionName,
                        CreateBlackBoardConnectionPage.BLACKBOARD_ULTRA_CLIENT_ID,
                        CreateBlackBoardConnectionPage.BLACKBOARD_ULTRA_LMS_URL)
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
                FULL_REPORT_QUERY,
                "lms_" + connectionID + "_blackboard");

        SoftAssertions softly = new SoftAssertions();

        while (rs1.next()) {

            for (String[] value : ROWS_TEST_FIVE) {
                if (rs1.getString("table_name").equals(value[0]) &&
                        rs1.getString("column_name").equals(value[1])) {
                    softly.assertThat(Integer.parseInt(rs1.getString("null_values")) > 0)
                            .withFailMessage(" Row %s %s has no empty values !", rs1.getString("table_name"), rs1.getString("column_name"))
                            .isTrue();
                }
            }
        }

        softly.assertAll();
        rs1.close();
        ds.environmentCleanUp();
    }
}
