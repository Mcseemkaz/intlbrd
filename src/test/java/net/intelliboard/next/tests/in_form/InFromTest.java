package net.intelliboard.next.tests.in_form;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.IBNextURLs;
import net.intelliboard.next.services.ProjectFilesEnum;
import net.intelliboard.next.services.helpers.DataGenerator;
import net.intelliboard.next.services.pages.elements.NotificationPopUpElement;
import net.intelliboard.next.services.pages.elements.table.TableElementBody;
import net.intelliboard.next.services.pages.header.HeaderAppsItemEnum;
import net.intelliboard.next.services.pages.header.HeaderObject;
import net.intelliboard.next.services.pages.inform.*;
import net.intelliboard.next.services.pages.library.LibraryItemTypeEnum;
import net.intelliboard.next.services.pages.myintelliboard.MyIntelliBoardPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Feature("InForm")
@Tag("InForm")
class InFromTest extends IBNextAbstractTest {

    @ParameterizedTest
    @EnumSource(value = InFormColumnType.class)
    @Tags(value = {@Tag("smoke"), @Tag("normal"), @Tag("SP-T89")})
    @DisplayName("SP-T89: Create Inform table")
    void createInformTableTest(InFormColumnType value) throws InterruptedException {

        String tableName = "SP-T89_" + value + DataGenerator.getRandomString();

        open(IBNextURLs.INFORM_LIST_PAGE);

        InFormPage.init()
                .openAddTablePage()
                .fillInTableTitle(tableName)
                .addColumn(value, tableName)
                .saveInformTable()
                .searchInfoTable(tableName);

        InFormPage inFormPage = InFormPage.init();
        assertThat(inFormPage.isTableExist(tableName))
                .withFailMessage("Table :%s is not existed", tableName)
                .isTrue();

        inFormPage
                .searchInfoTable(tableName)
                .deleteTable(tableName);
    }

    //TODO [MO] DropDown Issue
    @Test
    @Tags(value = {@Tag("smoke"), @Tag("SP-T94"), @Tag("smoke_inform")})
    @DisplayName("SP-T94: Create Inform form")
    void testCreateInFormForm() {

        String inFormTable = "Automation InFormTable";
        String inFormName = "SP-94_" + DataGenerator.getRandomString();

        HeaderObject
                .init()
                .createInFormForm()
                .selectTable(inFormTable)
                .setInFormName(inFormName)
                .addColumn("Text")
                .addColumn("Number")
                .saveForm();
    }

    @Test
    @Tags(value = {@Tag("smoke"), @Tag("SP-T329"), @Tag("smoke_inform")})
    @Description("Create report from Inform page")
    @DisplayName("SP-T329: Create report from Inform page")
    void testCreateReportFromInForm() {

        String reportName = "SP-T329 Automation InFormTable_" + DataGenerator.getRandomString();
        String inFromTableName = "Automation InFormTable";

        HeaderObject
                .init()
                .openApp(HeaderAppsItemEnum.INFORM);

        InFormPage
                .init()
                .searchInfoTable(inFromTableName)
                .generateStandardReport(inFromTableName)
                .fillInName(reportName)
                .continueToPreview()
                .saveReportToDashboard();

        open(IBNextURLs.MY_INTELLIBOARD_PAGE);

        assertThat(
                MyIntelliBoardPage
                        .init()
                        .isReportExist(reportName))
                .withFailMessage("Generated report %s is not existed", reportName)
                .isTrue();

        MyIntelliBoardPage
                .init()
                .deleteReport(reportName)
                .confirmDeletion();

        assertThat(MyIntelliBoardPage
                .init()
                .isReportExist(reportName))
                .withFailMessage("Generated report %s is still existed", reportName)
                .isFalse();


    }

    @Test
    @Tags(value = {@Tag("smoke"), @Tag("SP-T80"), @Tag("smoke_inform")})
    @Description("Add columns with all data type to the inform table")
    @DisplayName("SP-T80: Add columns with all data type to the inform table")
    void testAddAllDataTypesInFormTable() {

        String inFromTableName = "SP-T80 Automation Table_" + DataGenerator.getRandomString();

        HeaderObject
                .init()
                .openApp(HeaderAppsItemEnum.INFORM);

        InFormPage
                .init()
                .openAddTablePage();

        Arrays.stream(InFormColumnType.values()).forEach(k -> {
            try {
                AddNewInFormTablePage.init().addColumn(k, "Value_" + DataGenerator.getRandomString());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        AddNewInFormTablePage
                .init()
                .fillInTableTitle(inFromTableName)
                .saveInformTable();

        assertThat(InFormPage.init().isTableExist(inFromTableName))
                .withFailMessage("InForm Table is not created : %s", inFromTableName)
                .isTrue();

        InFormPage
                .init()
                .deleteTable(inFromTableName);
    }

    @Test
    @Tags(value = {@Tag("smoke"), @Tag("SP-T891"), @Tag("smoke_inform")})
    @Description("CSV import for New table")
    @DisplayName("SP-T891: CSV import for New table")
    void testImportCSVNeInFormTable() throws InterruptedException {

        //Open InForm Import
        String inFormTableName = "SP-T891_CVS_" + DataGenerator.getRandomString();

        HeaderObject
                .init()
                .openApp(HeaderAppsItemEnum.INFORM);

        InFormPage
                .init()
                .openImportFileList()
                .importFile()
                .selectTable("New Table")
                .fillInTableName(inFormTableName)
                .uploadFile(ProjectFilesEnum.INFORM_IMPORT_CSV)
                .proceedNext();

        InFormImportConfiguration
                .init()
                .saveInform()
                .waitingProcessingComplete();

        open(IBNextURLs.INFORM_LIST_PAGE);

        assertThat(
                InFormPage
                        .init()
                        .searchInfoTable(inFormTableName)
                        .isTableExist(inFormTableName))
                .withFailMessage("Imported InForm Table: %s does not existed", inFormTableName)
                .isTrue();

        InFormPage
                .init()
                .deleteTable(inFormTableName);

        assertThat(
                InFormPage
                        .init()
                        .searchInfoTable(inFormTableName)
                        .isTableExist(inFormTableName))
                .withFailMessage("Imported InForm Table: %s is still existed", inFormTableName)
                .isFalse();
    }

    @Test
    @Tags(value = {@Tag("smoke"), @Tag("SP-T890"), @Tag("smoke_inform")})
    @Description("XLS import for New table")
    @DisplayName("SP-T890: XLS import for New table")
    void testImportXLSNeInFormTable() throws InterruptedException {

        //Open InForm Import
        String inFormTableName = "SP-T890_XLS_" + DataGenerator.getRandomString();

        HeaderObject
                .init()
                .openApp(HeaderAppsItemEnum.INFORM);

        InFormPage
                .init()
                .openImportFileList()
                .importFile()
                .selectTable("New Table")
                .fillInTableName(inFormTableName)
                .uploadFile(ProjectFilesEnum.INFORM_IMPORT_XLS)
                .proceedNext();

        InFormXLSSheetSelector
                .init()
                .saveSheetConfiguration()
                .saveInform()
                .waitingProcessingComplete();

        open(IBNextURLs.INFORM_LIST_PAGE);

        assertThat(
                InFormPage
                        .init()
                        .searchInfoTable(inFormTableName)
                        .isTableExist(inFormTableName))
                .withFailMessage("Imported InForm Table: %s does not existed", inFormTableName)
                .isTrue();

        InFormPage
                .init()
                .deleteTable(inFormTableName);

        assertThat(
                InFormPage
                        .init()
                        .searchInfoTable(inFormTableName)
                        .isTableExist(inFormTableName))
                .withFailMessage("Imported InForm Table: %s is still existed", inFormTableName)
                .isFalse();
    }

    @Test
    @Tags(value = {@Tag("smoke"), @Tag("SP-T82"), @Tag("smoke_inform")})
    @Description("Save Inform table")
    @DisplayName("SP-T82: Save Inform table")
    void testSaveInFormTable() {

        String inFromTableName = "SP-T82 Automation Table_" + DataGenerator.getRandomString();

        HeaderObject
                .init()
                .openApp(HeaderAppsItemEnum.INFORM);

        InFormPage
                .init()
                .openAddTablePage();

        Arrays.stream(InFormColumnType.values()).forEach(k -> {
            try {
                AddNewInFormTablePage.init().addColumn(k, "Value_" + DataGenerator.getRandomString());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        AddNewInFormTablePage
                .init()
                .fillInTableTitle(inFromTableName)
                .saveInformTable();

        assertThat(InFormPage.init().isTableExist(inFromTableName))
                .withFailMessage("InForm Table is not created : %s", inFromTableName)
                .isTrue();

        InFormPage
                .init()
                .deleteTable(inFromTableName);
    }

    @Test
    @Tags(value = {@Tag("smoke"), @Tag("SP-T95"), @Tag("smoke_inform")})
    @Description("Save data to the Inform form")
    @DisplayName("SP-T95: Save data to the Inform form")
    void testSaveDataInFormForm() {
        String itemName = "Automation Form";
        String textFieldName = "Text";
        String textFieldNumber = "Number";
        String text = "Record at SP-T95: " + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        String number = DataGenerator.getRandomNumber();

        //Open exist InForm Form
        MyIntelliBoardPage
                .init()
                .viewItem(LibraryItemTypeEnum.INFORM, itemName);

        InFormViewPage
                .init()
                .inputTextArea(textFieldName, text)
                .inputInput(textFieldNumber, number)
                .saveFormData();

        assertThat(NotificationPopUpElement.init().getPopUpText().equals("Data Saved"))
                .withFailMessage("Notification for Save Data in InForm failed")
                .isTrue();
    }

    @Test
    @Tags(value = {@Tag("smoke"), @Tag("SP-T96"), @Tag("smoke_inform")})
    @Description("Saved in Inform data is visible in 'Show' page in Inform table")
    @DisplayName("SP-T96: Saved in Inform data is visible in 'Show' page in Inform table")
    void testShowsSavedDataInFormForm() {
        String itemName = "Automation Form";
        String itemTable = "Automation InFormTable";
        String textFieldName = "Text";
        String textFieldNumber = "Number";
        String text = "Record at SP-T96: " + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        String number = DataGenerator.getRandomNumber();

        //Open exist InForm Form
        MyIntelliBoardPage
                .init()
                .viewItem(LibraryItemTypeEnum.INFORM, itemName);

        InFormViewPage
                .init()
                .inputTextArea(textFieldName, text)
                .inputInput(textFieldNumber, number)
                .saveFormData();

        assertThat(NotificationPopUpElement.init().getPopUpText().equals("Data Saved"))
                .withFailMessage("Notification for Save Data in InForm failed")
                .isTrue();

        HeaderObject
                .init()
                .openApp(HeaderAppsItemEnum.INFORM);

        assertThat(InFormPage
                .init()
                .searchInfoTable(itemTable)
                .openShows(itemTable)
                .isRecordExist(text))
                .withFailMessage("Record with item: %s is not existed", text)
                .isTrue();

        InFormRecordsShowPage
                .init()
                .deleteRow(text);
    }

    @Test
    @Tags(value = {@Tag("smoke"), @Tag("SP-T347"), @Tag("smoke_inform")})
    @Description("Imported data are visible in the 'Show' option")
    @DisplayName("SP-T347: Imported data are visible in the 'Show' option - XLS")
    void testImportedXLSAreVisible() throws InterruptedException {

        //Open InForm Import
        String inFormTableName = "SP-T347_XLS_" + DataGenerator.getRandomString();
        String record = "Triss Marigold";

        HeaderObject
                .init()
                .openApp(HeaderAppsItemEnum.INFORM);

        InFormPage
                .init()
                .openImportFileList()
                .importFile()
                .selectTable("New Table")
                .fillInTableName(inFormTableName)
                .uploadFile(ProjectFilesEnum.INFORM_IMPORT_XLS)
                .proceedNext();

        InFormXLSSheetSelector
                .init()
                .saveSheetConfiguration()
                .saveInform()
                .waitingProcessingComplete();

        open(IBNextURLs.INFORM_LIST_PAGE);

        assertThat(
                InFormPage
                        .init()
                        .searchInfoTable(inFormTableName)
                        .isTableExist(inFormTableName))
                .withFailMessage("Imported InForm Table: %s does not existed", inFormTableName)
                .isTrue();

        assertThat(InFormPage
                .init()
                .openShows(inFormTableName)
                .isRecordExist(record))
                .withFailMessage("Record: %s is not exist", record)
                .isTrue();

        HeaderObject
                .init()
                .openApp(HeaderAppsItemEnum.INFORM);

        InFormPage
                .init()
                .deleteTable(inFormTableName);
    }

    @Test
    @Tags(value = {@Tag("smoke"), @Tag("SP-T100"), @Tag("smoke_inform")})
    @Description("Delete all data from 'Show' page")
    @DisplayName("SP-T100: Delete all data from 'Show' page")
    void testDeleteSavedDataInFormForm() {
        String itemName = "Automation Form";
        String itemTable = "Automation InFormTable";
        String textFieldName = "Text";
        String textFieldNumber = "Number";
        String text = "Record at SP-T100: " + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        String number = DataGenerator.getRandomNumber();

        //Open exist InForm Form
        MyIntelliBoardPage
                .init()
                .viewItem(LibraryItemTypeEnum.INFORM, itemName);

        InFormViewPage
                .init()
                .inputTextArea(textFieldName, text)
                .inputInput(textFieldNumber, number)
                .saveFormData();

        assertThat(NotificationPopUpElement.init().getPopUpText().equals("Data Saved"))
                .withFailMessage("Notification for Save Data in InForm failed")
                .isTrue();

        HeaderObject
                .init()
                .openApp(HeaderAppsItemEnum.INFORM);

        assertThat(InFormPage
                .init()
                .searchInfoTable(itemTable)
                .openShows(itemTable)
                .isRecordExist(text))
                .withFailMessage("Record with item: %s is not existed", text)
                .isTrue();

        InFormRecordsShowPage
                .init()
                .deleteAllRecords();

        assertThat(TableElementBody.init().isTableEmpty())
                .withFailMessage("InForm Table is not empty")
                .isTrue();
    }
}
