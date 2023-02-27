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
class LibraryMainOneTest extends IBNextAbstractTest {

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T428")})
    @DisplayName("SP-T428: Search on Library page - Name")
    void testSearchByNameLibraryPage() {

        String libraryItemName = "Needs Grading";
        int size = 1;

        open(LIBRARY_MAIN);

        LibraryMainPage
                .init()
                .searchLibraryItem(libraryItemName);

        assertThat(LibraryMainPage
                .init()
                .getLibraryItemsNumberByType(LibraryItemTypeEnum.REPORTS) == size)
                .withFailMessage("Number of found reports is not %s", size)
                .isTrue();
    }

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T428")})
    @DisplayName("SP-T428: Search on Library page - Tag")
    void testSearchByTagLibraryPage() {

        String libraryItemName = "Grading";
        int size = 2;

        open(LIBRARY_MAIN);

        LibraryMainPage
                .init()
                .searchLibraryItem(libraryItemName);

        assertThat(LibraryMainPage
                .init()
                .getLibraryItemsNumberByType(LibraryItemTypeEnum.REPORTS) == size)
                .withFailMessage("Number of found reports is not %s", size)
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

        LibraryMainPage
                .init()
                .searchLibraryItem(libraryItemName);

        likesBefore = LibraryMainPage
                .init()
                .getNumberOfLikesItem(libraryItemName);

        LibraryMainPage
                .init()
                .likeItem(libraryItemName);

        likesAfter = LibraryMainPage
                .init()
                .getNumberOfLikesItem(libraryItemName);

        assertThat(likesAfter == likesBefore + 1)
                .withFailMessage("Number of likes do not equal")
                .isTrue();

        LibraryMainPage
                .init()
                .unlikeItem(libraryItemName);

        likesAfter = LibraryMainPage
                .init()
                .getNumberOfLikesItem(libraryItemName);

        assertThat(likesAfter == likesBefore)
                .withFailMessage("Number of likes do not equal")
                .isTrue();
    }
}