package net.intelliboard.next.tests.reports;

import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.helpers.DataGenerator;
import net.intelliboard.next.services.pages.connections.ConnectionsTypeEnum;
import net.intelliboard.next.services.pages.header.HeaderConnectionManager;
import net.intelliboard.next.services.pages.header.HeaderObject;
import net.intelliboard.next.services.pages.myintelliboard.MyIntelliBoardPage;
import net.intelliboard.next.services.pages.report.builder.BuilderRightSideBarLayoutPage;
import net.intelliboard.next.services.pages.report.builder.ReportBuilderDisplayElementEnum;
import net.intelliboard.next.services.pages.report.builder.ReportBuilderDisplayElementsMainEnum;
import net.intelliboard.next.services.pages.report.builder.ReportBuilderMainPage;
import net.intelliboard.next.services.pages.report.create_wizard.ReportTypeEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static net.intelliboard.next.services.IBNextURLs.MAIN_URL;
import static net.intelliboard.next.services.IBNextURLs.MY_INTELLIBOARD_PAGE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CreateReportsTest extends IBNextAbstractTest {


    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T154")})
    @DisplayName("SP-T154: Short way to create report")
    public void testCreateTableReport() throws InterruptedException {

        String connectionName = "Automation Canvans";
        String reportName = "AQA-" + DataGenerator.getRandomString();

        open(MAIN_URL);

        HeaderConnectionManager
                .expandOpenConnectionManager()
                .selectConnection(connectionName);

        HeaderObject.init()
                .createReport()
                .fillName(reportName)
                .fillDescription(DataGenerator.getRandomString())
                .proceedNext()
                .selectReportType(ReportTypeEnum.TABLE)
                .proceedNext()
                .selectLMSType(ConnectionsTypeEnum.CANVAS)
                .proceedNext()
                .goToReport();

        BuilderRightSideBarLayoutPage
                .init()
                .addDisplayElement(ReportBuilderDisplayElementsMainEnum.USERS_CATEGORY
                        , ReportBuilderDisplayElementEnum.USERS_CATEGORY_FULL_NAME);

        ReportBuilderMainPage
                .init()
                .saveReportToDashboard();

        //Clean-up

        open(MY_INTELLIBOARD_PAGE);

        assertThat(MyIntelliBoardPage.init().isReportExist(reportName)).isTrue();

        MyIntelliBoardPage
                .init()
                .deleteReport(reportName)
                .confirmDeletion();

        assertThat(MyIntelliBoardPage.init().isReportExist(reportName)).isFalse();
    }
}
