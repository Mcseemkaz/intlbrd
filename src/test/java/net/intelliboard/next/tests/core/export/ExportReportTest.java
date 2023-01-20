package net.intelliboard.next.tests.core.export;

import io.qameta.allure.Feature;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.pages.myintelliboard.MyIntelliBoardPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static com.codeborne.selenide.Selenide.open;
import static net.intelliboard.next.services.IBNextURLs.MY_INTELLIBOARD_PAGE;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("Export_Report")
@Feature("Export Report")
public class ExportReportTest extends IBNextAbstractTest {

    private final String xlsReportName = "XLS Export";

    @Test
    @Tags(value = {@Tag("smoke"), @Tag("normal"), @Tag("SP-T1089")})
    @DisplayName("SP-T1089: Export of the reports in XLS format")
    void testExportReportXLS() throws FileNotFoundException {

        //Open report
        open(MY_INTELLIBOARD_PAGE);

        File report = MyIntelliBoardPage
                .init()
                .openEditReport(xlsReportName)
                .downloadXLSReport();

        assertTrue(report.exists());
        //Check Export XLS
        //Check success message
    }
}
