package net.intelliboard.next.tests.sql;

import io.qameta.allure.Feature;
import net.intelliboard.next.services.database.DataBaseConnectorService;
import net.intelliboard.next.services.database.SQLQueries;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
