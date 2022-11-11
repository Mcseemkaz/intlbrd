package net.intelliboard.next.tests.builder.library;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Feature;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.IBNextURLs;
import net.intelliboard.next.services.pages.library.LibraryItemTypeEnum;
import net.intelliboard.next.services.pages.library.LibraryMainPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Feature("Library")
@Tag("Library")
public class LibraryMainTest extends IBNextAbstractTest {

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T428")})
    @DisplayName("SP-T428: Search on Library page - Name")
    void testSearchByNameLibraryPage() {

        String libraryItemName = "Needs Grading";

        open(IBNextURLs.LIBRARY_MAIN);

        LibraryMainPage libraryMainPage = LibraryMainPage.init();

        libraryMainPage.searchLibraryItem(libraryItemName);

        Selenide.sleep(2000);

        assertThat(libraryMainPage.getLibraryItemsNumberByType(LibraryItemTypeEnum.REPORTS) == 3)
                .withFailMessage("Number of found reports is not 3")
                .isTrue();
    }

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T428")})
    @DisplayName("SP-T428: Search on Library page - Tag")
    void testSearchByTagLibraryPage() {

        String libraryItemName = "Grading";

        open(IBNextURLs.LIBRARY_MAIN);

        LibraryMainPage libraryMainPage = LibraryMainPage.init();

        libraryMainPage.searchLibraryItem(libraryItemName);

        Selenide.sleep(2000);

        assertThat(libraryMainPage.getLibraryItemsNumberByType(LibraryItemTypeEnum.REPORTS) == 4)
                .withFailMessage("Number of found reports is not 3")
                .isTrue();
    }
}