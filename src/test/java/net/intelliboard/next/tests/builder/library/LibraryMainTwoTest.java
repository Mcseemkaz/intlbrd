package net.intelliboard.next.tests.builder.library;

import io.qameta.allure.Feature;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.pages.connections.ConnectionsTypeEnum;
import net.intelliboard.next.services.pages.library.LibraryItemTypeEnum;
import net.intelliboard.next.services.pages.library.LibraryMainPage;
import net.intelliboard.next.services.pages.library.LibraryOrderTypeEnum;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static net.intelliboard.next.services.IBNextURLs.LIBRARY_MAIN;

@Feature("Library")
@Tag("Library")
public class LibraryMainTwoTest extends IBNextAbstractTest {

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T438")})
    @DisplayName("SP-T438:  Order By in Library")
    void testOrderByLibraryPage() {

        open(LIBRARY_MAIN);
        String itemName;
        SoftAssertions softly = new SoftAssertions();
        LibraryMainPage libraryMainPage = LibraryMainPage.init();

        libraryMainPage.orderItemsBy(LibraryOrderTypeEnum.NAME);
        itemName = libraryMainPage.getLibraryItemName(LibraryItemTypeEnum.REPORTS, 1);
        softly.assertThat(itemName.startsWith("A"))
                .withFailMessage("Items has wrong order - selected item is %s", itemName)
                .isTrue();

        libraryMainPage.orderItemsBy(LibraryOrderTypeEnum.CREATION_DATE);
        itemName = libraryMainPage.getLibraryItemName(LibraryItemTypeEnum.REPORTS, 1);
        softly.assertThat(itemName.startsWith("C"))
                .withFailMessage("Items has wrong order - selected item is %s", itemName)
                .isTrue();

        libraryMainPage.orderItemsBy(LibraryOrderTypeEnum.LAST_UPDATE);
        itemName = libraryMainPage.getLibraryItemName(LibraryItemTypeEnum.REPORTS, 1);
        softly.assertThat(itemName.startsWith("I"))
                .withFailMessage("Items has wrong order - selected item is %s", itemName)
                .isTrue();

        libraryMainPage.orderItemsBy(LibraryOrderTypeEnum.MOST_VISITED);
        itemName = libraryMainPage.getLibraryItemName(LibraryItemTypeEnum.REPORTS, 1);
        softly.assertThat(itemName.startsWith("P"))
                .withFailMessage("Items has wrong order - selected item is %s", itemName)
                .isTrue();

        softly.assertAll();
    }

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T434")})
    @DisplayName("SP-T434:  Availability in 'View Active reports' in Library - CANVAS")
    void testAvailabilityItemsByConnectionTypeLibraryPageCANVAS() {
        open(LIBRARY_MAIN);
        LibraryMainPage libraryMainPage = LibraryMainPage.init();

        libraryMainPage.setActiveReportsForConnection(ConnectionsTypeEnum.CANVAS);


        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(libraryMainPage.getLibraryItemsNumberByType(LibraryItemTypeEnum.REPORTS) == 32)
                .withFailMessage("Reports size is mismatch %s %s", LibraryItemTypeEnum.REPORTS.value, 32)
                .isTrue();

        softly.assertThat(libraryMainPage.getLibraryItemsNumberByType(LibraryItemTypeEnum.DASHBOARDS) == 15)
                .withFailMessage("Reports size is mismatch %s %s", LibraryItemTypeEnum.DASHBOARDS.value, 15)
                .isTrue();

        softly.assertAll();
    }

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T434")})
    @DisplayName("SP-T434:  Availability in 'View Active reports' in Library - D2L")
    void testAvailabilityItemsByConnectionTypeLibraryPageD2L() {
        open(LIBRARY_MAIN);
        LibraryMainPage libraryMainPage = LibraryMainPage.init();

        libraryMainPage.setActiveReportsForConnection(ConnectionsTypeEnum.D2L);
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(libraryMainPage.getLibraryItemsNumberByType(LibraryItemTypeEnum.REPORTS) == 29)
                .withFailMessage("Reports size is mismatch %s %s", LibraryItemTypeEnum.REPORTS.value, 29)
                .isTrue();

        softly.assertThat(libraryMainPage.getLibraryItemsNumberByType(LibraryItemTypeEnum.DASHBOARDS) == 15)
                .withFailMessage("Reports size is mismatch %s %s", LibraryItemTypeEnum.DASHBOARDS.value, 15)
                .isTrue();

        softly.assertAll();
    }
}
