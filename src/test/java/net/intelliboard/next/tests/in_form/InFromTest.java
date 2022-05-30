package net.intelliboard.next.tests.in_form;

import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.IBNextURLs;
import net.intelliboard.next.services.helpers.DataGenerator;
import net.intelliboard.next.services.pages.inform.InFormColumnType;
import net.intelliboard.next.services.pages.inform.InFormPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class InFromTest extends IBNextAbstractTest {

    @ParameterizedTest
    @EnumSource(value = InFormColumnType.class)
    @Tags(value = {@Tag("smoke"), @Tag("normal"), @Tag("SP-T89")})
    @DisplayName("SP-T89: Create Inform table")
    public void createInformTableTest(InFormColumnType value) throws InterruptedException {

        String tableName = DataGenerator.getRandomString() + "TestAQA";

        open(IBNextURLs.INFORM_LIST_PAGE);

        InFormPage.init()
                .openAddTablePage()
                .fillInTableTitle(tableName)
                .addColumn(value, tableName)
                .saveInformTable()
                .searchInfoTable(tableName);

        InFormPage inFormPage = InFormPage.init();
        assertThat(inFormPage.isTableExist(tableName))
                .isTrue()
                .as(String.format("Table : %s ", tableName));

        inFormPage
                .searchInfoTable(tableName)
                .deleteTable(tableName);
    }
}
