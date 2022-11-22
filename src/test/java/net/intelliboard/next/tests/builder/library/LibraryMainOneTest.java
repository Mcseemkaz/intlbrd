package net.intelliboard.next.tests.builder.library;

import io.qameta.allure.Feature;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.pages.library.LibraryItemTypeEnum;
import net.intelliboard.next.services.pages.library.LibraryMainPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static net.intelliboard.next.services.IBNextURLs.LIBRARY_MAIN;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Feature("Library")
@Tag("Library")
public class LibraryMainOneTest extends IBNextAbstractTest {

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T428")})
    @DisplayName("SP-T428: Search on Library page - Name")
    void testSearchByNameLibraryPage() {

        String libraryItemName = "Needs Grading";

        open(LIBRARY_MAIN);

        LibraryMainPage libraryMainPage = LibraryMainPage.init();

        libraryMainPage.searchLibraryItem(libraryItemName);

        assertThat(libraryMainPage.getLibraryItemsNumberByType(LibraryItemTypeEnum.REPORTS) == 3)
                .withFailMessage("Number of found reports is not 3")
                .isTrue();
    }

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T428")})
    @DisplayName("SP-T428: Search on Library page - Tag")
    void testSearchByTagLibraryPage() {

        String libraryItemName = "Grading";

        open(LIBRARY_MAIN);

        LibraryMainPage libraryMainPage = LibraryMainPage.init();

        libraryMainPage.searchLibraryItem(libraryItemName);

        assertThat(libraryMainPage.getLibraryItemsNumberByType(LibraryItemTypeEnum.REPORTS) == 4)
                .withFailMessage("Number of found reports is not 3")
                .isTrue();
    }

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T444")})
    @DisplayName("SP-T444: Like in report")
    void testLikeReportLibraryPage() {
        String libraryItemName = "Grading";
        int likesBefore;
        int likesAfter;

        open(LIBRARY_MAIN);

        LibraryMainPage libraryMainPage = LibraryMainPage.init();

        libraryMainPage.searchLibraryItem(libraryItemName);

        likesBefore = libraryMainPage.getNumberOfLikesItem(libraryItemName);
        libraryMainPage.likeItem(libraryItemName);
        likesAfter = libraryMainPage.getNumberOfLikesItem(libraryItemName);

        assertThat(likesAfter == likesBefore + 1)
                .withFailMessage("Number of likes do not equal")
                .isTrue();

        libraryMainPage.unlikeItem(libraryItemName);

        likesAfter = libraryMainPage.getNumberOfLikesItem(libraryItemName);

        assertThat(likesAfter == likesBefore)
                .withFailMessage("Number of likes do not equal")
                .isTrue();
    }
}