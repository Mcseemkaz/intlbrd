package net.intelliboard.next.tests.builder.my_intelliboard;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Feature;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.helpers.DataGenerator;
import net.intelliboard.next.services.pages.dashboard.CreateDashboardPage;
import net.intelliboard.next.services.pages.header.HeaderObject;
import net.intelliboard.next.services.pages.library.LibraryItemTypeEnum;
import net.intelliboard.next.services.pages.myintelliboard.MyIntelliBoardPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.$x;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@Feature("MyIntelliboard")
@Tag("MyIntelliboard")
class MyIntelliboardTest extends IBNextAbstractTest {

    @Test
    @Tags(value = {@Tag("regression"), @Tag("normal"), @Tag("SP-T589")})
    @DisplayName("SP-T589: Report title")
    void checkReportTitle() {
        String dashboardName = "Untitled Dashboard";

        HeaderObject
                .init()
                .openMyIntelliBoardPage();

        assertThat(
                MyIntelliBoardPage
                        .init()
                        .getItemName(LibraryItemTypeEnum.DASHBOARDS, 1).equals(dashboardName))
                .withFailMessage("Dashboard %s is not presented", dashboardName)
                .isTrue();
    }

    @Test
    @Tags(value = {@Tag("regression"), @Tag("normal"), @Tag("SP-T617")})
    @DisplayName("SP-T617: Search by title")
    void checkSearchByTitle() throws IOException {

        String dashboardName = "Untitled";

        MyIntelliBoardPage my = MyIntelliBoardPage.init();
        HeaderObject.init().openMyIntelliBoardPage().searchDashboard(dashboardName);

        Selenide.sleep(SLEEP_TIMEOUT_SHORT);

        assertThat(my.isDashboardPresentsByName(dashboardName))
                .isFalse()
                .as(String.format("Dashboard %s is not presented", dashboardName));
    }

    @Test
    @Tags(value = {@Tag("regression"), @Tag("normal"), @Tag("SP-T617-1")})
    @DisplayName("SP-T617-1: Search by title - No result")
    void checkSearchByTitleNoResult() throws IOException {

        String dashboardName = DataGenerator.getRandomString();

        MyIntelliBoardPage my = MyIntelliBoardPage.init();
        HeaderObject.init().openMyIntelliBoardPage().searchDashboard(dashboardName);

        Selenide.sleep(SLEEP_TIMEOUT_SHORT);

        $x("//div[contains (@class,'library-empty')]").shouldBe(Condition.exist);

        assertThat(my.isDashboardPresentsByName(dashboardName))
                .isFalse()
                .as(String.format("Dashboard %s is not presented", dashboardName));
    }

    @Test
    @Tags(value = {@Tag("regression"), @Tag("normal"), @Tag("SP-T629")})
    @DisplayName("SP-T629: Favorite / Unfavorite Dashboard")
    void checkFavoriteUnfavoriteDashboard() throws IOException {
        int numberOfDashboard = 1;
        HeaderObject.init().openMyIntelliBoardPage();
        MyIntelliBoardPage my = MyIntelliBoardPage.init();

        //Set favorite
        String dashboardName = my.getNameofDasnboardByOrderNumber(numberOfDashboard);
        my.setDashboardFavorite(dashboardName);
        waitForPageLoaded();

        Selenide.sleep(SLEEP_TIMEOUT_SHORT);

        assertThat(my.isDashboardPresentsInFavorite(dashboardName))
                .withFailMessage("Dashboard %s is not a favorite", dashboardName)
                .isTrue();

        my.setDashboardFavorite(dashboardName);
        waitForPageLoaded();
        Selenide.sleep(SLEEP_TIMEOUT_SHORT);

        assertThat(my.isDashboardPresentsInFavorite(dashboardName))
                .withFailMessage("Dashboard %s is still favorite", dashboardName)
                .isFalse();
    }

    @Test
    @Tags(value = {@Tag("regression"), @Tag("normal"), @Tag("SP-T621")})
    @DisplayName("SP-T621: Check \"View\" in action button on MyIntelliboard page\n")
    void checkViewByActionButton() {
        int numberOfDashboard = 1;

        HeaderObject.init().openMyIntelliBoardPage();
        MyIntelliBoardPage my = MyIntelliBoardPage.init();

        //Open Dashboard
        String dashboardName = my.getItemName(LibraryItemTypeEnum.DASHBOARDS, numberOfDashboard);
        my.openView(numberOfDashboard);

        waitForPageLoaded();

        assertThat(
                $x("//div[@class='content-header']//h1")
                .getText().equals(dashboardName))
                .withFailMessage("The title is not match")
                .isTrue();
    }

    @Test
    @Tags(value = {@Tag("regression"), @Tag("normal"), @Tag("SP-T620")})
    @DisplayName("SP-T620: \"Set as default\" in actions on MyIntelliboard page")
    void checkSetAsDefaultActionButton() {
        int numberOfDashboard = 1;

        HeaderObject
                .init()
                .openMyIntelliBoardPage();

        MyIntelliBoardPage my = MyIntelliBoardPage.init();

        //Open Dashboard
        String dashboardName = my.getItemName(LibraryItemTypeEnum.DASHBOARDS, numberOfDashboard);
        my.openView(numberOfDashboard);

        waitForPageLoaded();

        assertThat($x("//div[@class='content-header']//h1")
                .getText())
                .isEqualTo(dashboardName);
    }

    @Test
    @Tags(value = {@Tag("regression"), @Tag("normal"), @Tag("SP-T624")})
    @DisplayName("SP-T624: Check \"Edit\" in action button on MyIntelliboard page\n")
    void checkEditByActionButton() {
        int numberOfDashboard = 1;

        HeaderObject.init().openMyIntelliBoardPage();
        MyIntelliBoardPage my = MyIntelliBoardPage.init();

        //Open Dashboard
        String dashboardName = my.getItemName(LibraryItemTypeEnum.DASHBOARDS, numberOfDashboard);
        my.openEdit(numberOfDashboard);

        waitForPageLoaded();

        String ag = CreateDashboardPage.init().getTitleName();

        assertThat(ag).isEqualTo(dashboardName);
    }
}
