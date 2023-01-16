package net.intelliboard.next.tests.in_form;

import io.qameta.allure.Feature;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.IBNextURLs;
import net.intelliboard.next.services.helpers.DataGenerator;
import net.intelliboard.next.services.pages.header.HeaderObject;
import net.intelliboard.next.services.pages.inform.InFormColumnType;
import net.intelliboard.next.services.pages.inform.InFormPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Feature("InForm")
@Tag("InForm")
public class InFromTest extends IBNextAbstractTest {

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
    @Tags(value = {@Tag("smoke"), @Tag("SP-T94")})
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
}
