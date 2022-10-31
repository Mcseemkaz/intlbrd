package net.intelliboard.next.tests.sql;

import io.qameta.allure.Feature;
import net.intelliboard.next.services.database.DataBaseConnectorService;
import net.intelliboard.next.services.database.SQLQueries;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import static net.intelliboard.next.services.database.SQLQueries.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Feature("DB Connection")
public class SQLConnectionTest {

    @BeforeAll
    public static void setUpALl() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    @AfterAll
    public static void cleanUp() {
    }

    @Disabled
    @Tag("SQL_LMS_Query_POC")
    @Test
    void testPOCConnection() throws SQLException, IOException {

        DataBaseConnectorService ds = new DataBaseConnectorService();
        ResultSet rs1 = ds.executeQuery(SQLQueries.GENERAL_REPORT_QUERY, "lms_2778_canvas");

        assertThat(!rs1.wasNull())
                .withFailMessage("Connection is not work")
                .isTrue();

        while (rs1.next()) {
            System.out.print(" | ");
            System.out.print(rs1.getRow());
            System.out.print(" | ");
            System.out.print(rs1.getString("schema_name"));
            System.out.print(" | ");
            System.out.print(rs1.getString("table_name"));
            System.out.print(" | ");
            System.out.println(rs1.getString("record_number"));
            System.out.println("----------------------------------");
        }
        rs1.close();
        ds.environmentCleanUp();
    }

    @Disabled
    @Tag("SQL_LMS_Query_POC")
    @Test
    void testPOCConnection2() throws SQLException, IOException {

        DataBaseConnectorService ds = new DataBaseConnectorService();
        ResultSet rs1 = ds.executeQuery(
                SQLQueries.GENERAL_REPORT_QUERY,
                "lms_8254_blackboard"
        );

        assertThat(!rs1.wasNull())
                .withFailMessage("Connection is not work")
                .isTrue();

        SoftAssertions softly = new SoftAssertions();

        int i = 1;
        while (rs1.next()) {
            for (String value : ROWS_TEST_ONE_LIST) {

                String columnValue = rs1.getString("table_name");
                String record_number = rs1.getString("record_number");
                String schema_name = rs1.getString("schema_name");

                if (schema_name.equals("public") && columnValue.equals(value)) {

                    System.out.println();
                    System.out.print(" | " + i + " | ");
                    System.out.print(columnValue);
                    System.out.print(" | ");
                    System.out.print(record_number);
                    System.out.println(" | ");
                    i++;
                    int recordNumber = Integer.parseInt(record_number);
                    softly.assertThat(recordNumber > 0)
                            .isTrue();
                }
            }
        }

        softly.assertAll();

        rs1.close();
        ds.environmentCleanUp();
    }

}
