package net.intelliboard.next.tests.in_form;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.IBNextURLs;
import net.intelliboard.next.services.helpers.DataGenerator;
import net.intelliboard.next.services.pages.header.HeaderAppsItemEnum;
import net.intelliboard.next.services.pages.header.HeaderObject;
import net.intelliboard.next.services.pages.inform.AddNewInFormTablePage;
import net.intelliboard.next.services.pages.inform.InFormColumnType;
import net.intelliboard.next.services.pages.inform.InFormPage;
import net.intelliboard.next.services.pages.myintelliboard.MyIntelliBoardPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.ArrayList;
import java.util.Arrays;

import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.in;

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
    void testAddAllDataTypesInFormTable() throws InterruptedException {

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
}
