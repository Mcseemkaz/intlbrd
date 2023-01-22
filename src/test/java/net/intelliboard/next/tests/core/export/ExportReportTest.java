package net.intelliboard.next.tests.core.export;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Feature;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.pages.export.ExportMainPage;
import net.intelliboard.next.services.pages.myintelliboard.MyIntelliBoardPage;
import net.intelliboard.next.services.pages.report.ReportPage;
import net.intelliboard.next.services.pages.report.builder.ReportShareOptionEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import static com.codeborne.selenide.Selenide.open;
import static net.intelliboard.next.services.IBNextURLs.EXPORT;
import static net.intelliboard.next.services.IBNextURLs.MY_INTELLIBOARD_PAGE;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("Export_Report")
@Feature("Export Report")
class ExportReportTest extends IBNextAbstractTest {

    private final String xlsReportName = "XLS Export";

    @Test
    @Tags(value = {@Tag("smoke"), @Tag("normal"), @Tag("SP-T1089")})
    @DisplayName("SP-T1089: Export of the reports in XLS format")
    void testExportReportXLS() throws IOException {

        open(MY_INTELLIBOARD_PAGE);

        MyIntelliBoardPage
                .init()
                .viewReport(xlsReportName)
                .selectShareOption(ReportShareOptionEnum.XLS);

        Selenide.sleep(Long.parseLong(propertiesGetValue.getPropertyValue("sleep_time_long")));

        open(EXPORT);
        File item = ExportMainPage
                .init()
                .downloadItem(xlsReportName, LocalDateTime.now());

        assertTrue(item.exists());
    }

    @Test
    @Tags(value = {@Tag("smoke"), @Tag("normal"), @Tag("SP-T989")})
    @DisplayName("SP-T989: Check appearing report at Export after click XLS")
    void testExportReportXLSByPopup() throws IOException {

        open(MY_INTELLIBOARD_PAGE);

        ReportPage reportPage = MyIntelliBoardPage
                .init()
                .viewReport(xlsReportName);

        reportPage.selectShareOption(ReportShareOptionEnum.XLS);
        File item = reportPage.downloadFileByInfoBlockPopup();

        Selenide.sleep(Long.parseLong(propertiesGetValue.getPropertyValue("sleep_time_long")));
        assertTrue(item.exists());
    }
}
