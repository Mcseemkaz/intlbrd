package net.intelliboard.next.tests.core.connection;

import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.IBNextURLs;
import net.intelliboard.next.services.helpers.DataGenerator;
import net.intelliboard.next.services.pages.connections.ConnectionsListPage;
import net.intelliboard.next.services.pages.connections.categories.ConnectionCategoriesListPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Tag("Connection_Categories")
public class ConnectionCategoriesTest extends IBNextAbstractTest {

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T997")})
    @DisplayName("SP-T997: Adding category to group connections")
    public void testEditDefaultGradingSchemaCanvas() {

        String categoryName = "SP-T997_" + DataGenerator.getRandomString();

        open(IBNextURLs.ALL_CONNECTIONS);
        ConnectionsListPage
                .init()
                .openConnectionCollectionsList()
                .createCategory(categoryName);

        assertThat(
                ConnectionCategoriesListPage
                        .init()
                        .searchCategory(categoryName)
                        .isCategoryExist(categoryName))
                .withFailMessage(String.format("Category: %s is no exist", categoryName))
                .isTrue();

        ConnectionCategoriesListPage
                .init()
                .selectCategory(categoryName, true)
                .deleteSelectedCategoriesByActionDropdown();
    }
}
