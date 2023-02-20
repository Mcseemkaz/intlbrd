package net.intelliboard.next.tests.reports;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Feature;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.helpers.DataGenerator;
import net.intelliboard.next.services.pages.connections.ConnectionsTypeEnum;
import net.intelliboard.next.services.pages.header.HeaderConnectionManager;
import net.intelliboard.next.services.pages.header.HeaderObject;
import net.intelliboard.next.services.pages.myintelliboard.MyIntelliBoardPage;
import net.intelliboard.next.services.pages.report.builder.*;
import net.intelliboard.next.services.pages.report.create_wizard.ReportTypeEnum;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.open;
import static net.intelliboard.next.services.IBNextURLs.MAIN_URL;
import static net.intelliboard.next.services.IBNextURLs.MY_INTELLIBOARD_PAGE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Feature("Report")
@Tag("Create_Report")
class CreateReportsTest extends IBNextAbstractTest {

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T154")})
    @DisplayName("SP-T154: Short way to create report")
    void testCreateTableReport() {

        String connectionName = ConnectionsTypeEnum.CANVAS.defaultName;
        String reportName = "Untitled Report";

        open(MAIN_URL);

        HeaderConnectionManager
                .expandOpenConnectionManager()
                .selectConnection(connectionName);

        HeaderObject.init()
                .createReport()
                .skipWizardFlow();

        BuilderRightSideBarTableLayoutPage
                .init()
                .addDisplayElement(
                        ReportBuilderDisplayElementsMainEnum.USERS_CATEGORY,
                        ReportBuilderDisplayElementEnum.USERS_CATEGORY_FULL_NAME);

        ReportBuilderMainPage
                .init()
                .saveReportToDashboard();

        //Clean-up

        open(MY_INTELLIBOARD_PAGE);

        Selenide.sleep(SLEEP_TIMEOUT_LONG);

        assertThat(
                MyIntelliBoardPage
                        .init()
                        .isReportExist(reportName))
                .withFailMessage(String.format("Report - %s is not existed", reportName))
                .isTrue();

        MyIntelliBoardPage
                .init()
                .deleteReport(reportName)
                .confirmDeletion();


        assertThat(
                MyIntelliBoardPage
                        .init()
                        .isReportExist(reportName))
                .withFailMessage(String.format("Report - %s is still existed", reportName))
                .isFalse();
    }

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T153")})
    @DisplayName("SP-T153: Create report (long way)")
    void testCreateTableReportLongWay() {
        String connectionName = ConnectionsTypeEnum.CANVAS.defaultName;
        String reportName = "SP-T153-" + DataGenerator.getRandomString();

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

        BuilderRightSideBarTableLayoutPage
                .init()
                .addDisplayElement(
                        ReportBuilderDisplayElementsMainEnum.USERS_CATEGORY,
                        ReportBuilderDisplayElementEnum.USERS_CATEGORY_FULL_NAME);

        ReportBuilderMainPage
                .init()
                .saveReportToDashboard();

        //Clean-up

        open(MY_INTELLIBOARD_PAGE);

        Selenide.sleep(SLEEP_TIMEOUT_LONG);

        assertThat(
                MyIntelliBoardPage
                        .init()
                        .isReportExist(reportName))
                .withFailMessage("Report %s does not exist", reportName)
                .isTrue();

        MyIntelliBoardPage
                .init()
                .deleteReport(reportName)
                .confirmDeletion();

        Selenide.sleep(SLEEP_TIMEOUT_SHORT);

        assertThat(
                MyIntelliBoardPage
                        .init()
                        .isReportExist(reportName))
                .withFailMessage(String.format("Report - %s is still existed", reportName))
                .isFalse();
    }

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T1255")})
    @DisplayName("SP-T1255: Create report on Totara connection")
    void testCreateTableReportTotara() {
        String connectionName = ConnectionsTypeEnum.TOTARA.defaultName;
        String reportName = "SP-T1255-" + DataGenerator.getRandomString();

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
                .selectLMSType(ConnectionsTypeEnum.TOTARA)
                .proceedNext()
                .goToReport();

        BuilderRightSideBarTableLayoutPage
                .init()
                .addDisplayElement(
                        ReportBuilderDisplayElementsMainEnum.USERS_CATEGORY,
                        ReportBuilderDisplayElementEnum.USERS_CATEGORY_FULL_NAME);

        ReportBuilderMainPage
                .init()
                .saveReportToDashboard();

        //Clean-up

        open(MY_INTELLIBOARD_PAGE);

        Selenide.sleep(SLEEP_TIMEOUT_LONG);

        assertThat(
                MyIntelliBoardPage
                        .init()
                        .isReportExist(reportName))
                .withFailMessage("Report %s is not exist", reportName)
                .isTrue();

        MyIntelliBoardPage
                .init()
                .deleteReport(reportName)
                .confirmDeletion();

        assertThat(
                MyIntelliBoardPage
                        .init()
                        .isReportExist(reportName))
                .withFailMessage("Report %s is still exist", reportName)
                .isFalse();
    }

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T161")})
    @DisplayName("SP-T161: Сhange report's Title and Description in Settings")
    void testChangeTitleDescriptionReport() {

        String connectionName = ConnectionsTypeEnum.CANVAS.defaultName;
        String reportName = "SP-T161-" + DataGenerator.getRandomString();
        String reportNameUPD = reportName + "_UPD_" + DataGenerator.getRandomString();

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

        BuilderRightSideBarTableLayoutPage
                .init()
                .addDisplayElement(
                        ReportBuilderDisplayElementsMainEnum.USERS_CATEGORY,
                        ReportBuilderDisplayElementEnum.USERS_CATEGORY_FULL_NAME);

        Selenide.sleep(SLEEP_TIMEOUT_LONG);

        ReportBuilderMainPage
                .init()
                .saveReportToDashboard();

        open(MY_INTELLIBOARD_PAGE);

        Selenide.sleep(SLEEP_TIMEOUT_LONG);

        assertThat(MyIntelliBoardPage.init().isReportExist(reportName)).isTrue();

        MyIntelliBoardPage.init()
                .editReport(reportName)
                .openSettingsModal()
                .fillInName(reportNameUPD)
                .fillInDescription(DataGenerator.getRandomString())
                .continueToPreview()
                .saveReportToDashboard();

        // Clean-up
        open(MY_INTELLIBOARD_PAGE);

        Selenide.sleep(SLEEP_TIMEOUT_SHORT);

        MyIntelliBoardPage
                .init()
                .deleteReport(reportNameUPD)
                .confirmDeletion();

        assertThat(MyIntelliBoardPage.init().isReportExist(reportNameUPD)).isFalse();
    }

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T164")})
    @DisplayName("SP-T164: Сhange report's Availability in Settings")
    void testChangeReportAvailability() {

        String connectionName = ConnectionsTypeEnum.CANVAS.defaultName;
        String connectionNameOther = ConnectionsTypeEnum.TOTARA.defaultName;
        String reportName = "SP-T164-" + DataGenerator.getRandomString();

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

        BuilderRightSideBarTableLayoutPage
                .init()
                .addDisplayElement(
                        ReportBuilderDisplayElementsMainEnum.USERS_CATEGORY,
                        ReportBuilderDisplayElementEnum.USERS_CATEGORY_FULL_NAME);

        Selenide.sleep(SLEEP_TIMEOUT_LONG);

        ReportBuilderMainPage
                .init()
                .saveReportToDashboard();

        open(MY_INTELLIBOARD_PAGE);

        Selenide.sleep(SLEEP_TIMEOUT_LONG);

        assertThat(MyIntelliBoardPage.init().isReportExist(reportName)).isTrue();

        ArrayList<ReportConnectionTypeAvailabilityEnum> listAdding = new ArrayList<>();
        listAdding.add(ReportConnectionTypeAvailabilityEnum.TOTARA);

        ArrayList<ReportConnectionTypeAvailabilityEnum> listRemoving = new ArrayList<>();
        listAdding.add(ReportConnectionTypeAvailabilityEnum.CANVAS);

        MyIntelliBoardPage.init()
                .editReport(reportName)
                .openSettingsModal()
                .changeAvailability(listAdding, true)
                .changeAvailability(listRemoving, false)
                .continueToPreview()
                .saveReportToDashboard();

        // Clean-up
        open(MY_INTELLIBOARD_PAGE);

        HeaderConnectionManager
                .expandOpenConnectionManager()
                .selectConnection(connectionNameOther);

        MyIntelliBoardPage
                .init()
                .editReport(reportName);

        open(MY_INTELLIBOARD_PAGE);

        MyIntelliBoardPage
                .init()
                .deleteReport(reportName)
                .confirmDeletion();

        assertThat(MyIntelliBoardPage.init().isReportExist(reportName))
                .isFalse();
    }

    @Disabled("Unclear requirements - seems that functionality was disabled") //TODO [MO] Unclear requirements - seems that functionality was disabled
    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T163")})
    @DisplayName("SP-T163: Change report color in the setting")
    void testChangeReportColor() {

        String connectionName = ConnectionsTypeEnum.CANVAS.defaultName;
        String reportName = "SP-T163-" + DataGenerator.getRandomString();

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

        BuilderRightSideBarTableLayoutPage
                .init()
                .addDisplayElement(
                        ReportBuilderDisplayElementsMainEnum.USERS_CATEGORY,
                        ReportBuilderDisplayElementEnum.USERS_CATEGORY_FULL_NAME);

        Selenide.sleep(SLEEP_TIMEOUT_LONG);

        ReportBuilderMainPage
                .init()
                .saveReportToDashboard();

        open(MY_INTELLIBOARD_PAGE);

        Selenide.sleep(SLEEP_TIMEOUT_LONG);

        assertThat(MyIntelliBoardPage.init().isReportExist(reportName)).isTrue();

        MyIntelliBoardPage.init()
                .editReport(reportName)
                .openSettingsModal()
                .setReportColor(ReportSettingsColorsEnum.GREEN)
                .continueToPreview()
                .saveReportToDashboard();

        // Clean-up
        open(MY_INTELLIBOARD_PAGE);

        Selenide.sleep(SLEEP_TIMEOUT_LONG);

        assertThat(MyIntelliBoardPage.init().getReportBackgroundColors(reportName)
                .contains(ReportSettingsColorsEnum.GREEN.rgbColor))
                .isTrue();

        MyIntelliBoardPage
                .init()
                .deleteReport(reportName)
                .confirmDeletion();

        Selenide.sleep(SLEEP_TIMEOUT_LONG);

        assertThat(MyIntelliBoardPage.init().isReportExist(reportName))
                .isFalse();
    }

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T449")})
    @DisplayName("SP-T449: Create pie chart")
    void testCreateReportPaiChart() {

        String connectionName = ConnectionsTypeEnum.CANVAS.defaultName;
        String reportName = "SP-T449-" + DataGenerator.getRandomString();

        open(MAIN_URL);

        HeaderConnectionManager
                .expandOpenConnectionManager()
                .selectConnection(connectionName);

        HeaderObject.init()
                .createReport()
                .fillName(reportName)
                .fillDescription(DataGenerator.getRandomString())
                .proceedNext()
                .selectReportType(ReportTypeEnum.PIE_CHART)
                .proceedNext()
                .selectLMSType(ConnectionsTypeEnum.CANVAS)
                .proceedNext()
                .goToReport();

        BuilderRightSideBarPaiChartLayoutPage
                .init()
                .addDisplayElement(
                        ReportBuilderDisplayElementsMainEnum.USERS_CATEGORY,
                        ReportBuilderDisplayElementEnum.USERS_CATEGORY_FULL_NAME,
                        BuilderRightSideBarPaiChartValuesTypeEnum.CATEGORY)
                .addDisplayElement(
                        ReportBuilderDisplayElementsMainEnum.USERS_CATEGORY,
                        ReportBuilderDisplayElementEnum.USERS_CATEGORY_USERS_LOGINS_COUNT,
                        BuilderRightSideBarPaiChartValuesTypeEnum.VALUE);

        Selenide.sleep(SLEEP_TIMEOUT_LONG);

        ReportBuilderMainPage
                .init()
                .saveReportToDashboard();

        open(MY_INTELLIBOARD_PAGE);

        MyIntelliBoardPage
                .init()
                .deleteReport(reportName)
                .confirmDeletion();

        assertThat(MyIntelliBoardPage.init().isReportExist(reportName))
                .isFalse();
    }

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T1281")})
    @DisplayName("SP-T1281: When clicking the “Cancel” button, no changes to the report are saved (adding columns).")
    void testRevertReportChangesByCancel() {
        String connectionName = ConnectionsTypeEnum.CANVAS.defaultName;
        String reportName = "SP-T1281-" + DataGenerator.getRandomString();

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

        BuilderRightSideBarTableLayoutPage
                .init()
                .addDisplayElement(
                        ReportBuilderDisplayElementsMainEnum.USERS_CATEGORY,
                        ReportBuilderDisplayElementEnum.USERS_CATEGORY_FULL_NAME);

        Selenide.sleep(SLEEP_TIMEOUT_LONG);

        ReportBuilderMainPage
                .init()
                .saveReportToDashboard();

        //Clean-up
        open(MY_INTELLIBOARD_PAGE);

        Selenide.sleep(SLEEP_TIMEOUT_LONG);

        assertThat(MyIntelliBoardPage.init().isReportExist(reportName)).isTrue();

        MyIntelliBoardPage
                .init()
                .editReport(reportName);

        BuilderRightSideBarTableLayoutPage
                .init()
                .addDisplayElement(
                        ReportBuilderDisplayElementsMainEnum.USERS_CATEGORY,
                        ReportBuilderDisplayElementEnum.USERS_CATEGORY_USERS_ID);

        ReportBuilderMainPage
                .init()
                .cancelSavingReport()
                .deleteReport(reportName)
                .confirmDeletion();

        assertThat(MyIntelliBoardPage.init().isReportExist(reportName)).isFalse();
    }
}
