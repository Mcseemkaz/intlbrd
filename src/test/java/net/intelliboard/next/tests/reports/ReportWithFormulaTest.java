package net.intelliboard.next.tests.reports;

import io.qameta.allure.Feature;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.helpers.DataGenerator;
import net.intelliboard.next.services.pages.connections.ConnectionsTypeEnum;
import net.intelliboard.next.services.pages.header.HeaderConnectionManager;
import net.intelliboard.next.services.pages.header.HeaderObject;
import net.intelliboard.next.services.pages.report.builder.BuilderRightSideBarTableLayoutPage;
import net.intelliboard.next.services.pages.report.builder.ReportBuilderMainPage;
import net.intelliboard.next.services.pages.report.create_wizard.ReportTypeEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static net.intelliboard.next.services.IBNextURLs.MAIN_URL;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Feature("Report")
@Tag("Report_Formula")
class ReportWithFormulaTest extends IBNextAbstractTest {

    @Test
    @Tags(value = {@Tag("high"), @Tag("SP-T692")})
    @DisplayName("SP-T692: Add Formula to the report")
    void testAddFormulaToReport() {

        String connectionName = ConnectionsTypeEnum.CANVAS.defaultName;
        String reportName = "SP-T692-" + DataGenerator.getRandomString();
        String formulaTitle = "Formula-" + DataGenerator.getRandomString();
        String description = "Formula-" + DataGenerator.getRandomString();

        open(MAIN_URL);

        HeaderConnectionManager
                .expandOpenConnectionManager()
                .selectConnection(connectionName);

        HeaderObject
                .init()
                .createReport()
                .fillName(reportName)
                .fillDescription(DataGenerator.getRandomString())
                .proceedNext()
                .selectReportType(ReportTypeEnum.TABLE)
                .proceedNext()
                .selectLMSType(ConnectionsTypeEnum.CANVAS)
                .proceedNext()
                .goToReport();

        BuilderRightSideBarTableLayoutPage
                .init()
                .openAddFormulaEditor()
                .addFormulaText("(90 * 3)")
                .saveFormula()
                .fillInTitle(formulaTitle)
                .fillInDescription(description)
                .submitFormula();

        assertThat(ReportBuilderMainPage.init().getTableRowValue(formulaTitle, 1))
                .isEqualTo("270");
    }
}
