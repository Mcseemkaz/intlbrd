package net.intelliboard.next.tests.builder.my_intelliboard;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Feature;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.helpers.DataGenerator;
import net.intelliboard.next.services.pages.dashboard.CreateDashboardPage;
import net.intelliboard.next.services.pages.header.HeaderObject;
import net.intelliboard.next.services.pages.myintelliboard.MyIntelliBoardPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$x;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@Feature("MyIntelliboard")
@Tag("MyIntelliboard")
public class MyIntelliboardTest extends IBNextAbstractTest {

    @Test
    @Tags(value = {@Tag("regression"), @Tag("normal"), @Tag("SP-T589")})
    @DisplayName("SP-T589: Report title")
    public void checkReportTitle() {

        HeaderObject.init().openMyIntelliBoardPage();

        assertThat(MyIntelliBoardPage.init().isDashboardPresentsByOrderNumber(1))
                .isTrue()
                .as(String.format("Dashboard %s is not presented", 1));
    }

    @Test
    @Tags(value = {@Tag("regression"), @Tag("normal"), @Tag("SP-T617")})
    @DisplayName("SP-T617: Search by title")
    public void checkSearchByTitle() throws InterruptedException {

        String dashboardName = "Untitled";

        MyIntelliBoardPage my = MyIntelliBoardPage.init();
        HeaderObject.init().openMyIntelliBoardPage().searchDashboard(dashboardName);

        Thread.sleep(3000);

        assertThat(my.isDashboardPresentsByName(dashboardName))
                .isFalse()
                .as(String.format("Dashboard %s is not presented", dashboardName));
    }

    @Test
    @Tags(value = {@Tag("regression"), @Tag("normal"), @Tag("SP-T617-1")})
    @DisplayName("SP-T617-1: Search by title - No result")
    public void checkSearchByTitleNoResult() throws InterruptedException {

        String dashboardName = DataGenerator.getRandomString();

        MyIntelliBoardPage my = MyIntelliBoardPage.init();
        HeaderObject.init().openMyIntelliBoardPage().searchDashboard(dashboardName);

        Thread.sleep(3000);

        $x("//div[contains (@class,'library-empty')]").shouldBe(Condition.exist);

        assertThat(my.isDashboardPresentsByName(dashboardName))
                .isFalse()
                .as(String.format("Dashboard %s is not presented", dashboardName));
    }

    @Test
    @Tags(value = {@Tag("regression"), @Tag("normal"), @Tag("SP-T629")})
    @DisplayName("SP-T629: Favorite / Unfavorite Dashboard")
    public void checkFavoriteUnfavoriteDashboard() {
        int numberOfDashboard = 1;
        HeaderObject.init().openMyIntelliBoardPage();
        MyIntelliBoardPage my = MyIntelliBoardPage.init();

        //Set favorite
        String dashboardName = my.getNameofDasnboardByOrderNumber(numberOfDashboard);
        my.setDashboardFavorite(dashboardName);
        waitForPageLoaded();
        Selenide.sleep(3000);

        assertThat(my.isDashboardPresentsInFavorite(dashboardName))
                .withFailMessage("Dashboard %s is not a favorite", dashboardName)
                .isTrue();

        my.setDashboardFavorite(dashboardName);
        waitForPageLoaded();
        Selenide.sleep(3000);

        assertThat(my.isDashboardPresentsInFavorite(dashboardName))
                .withFailMessage("Dashboard %s is still favorite", dashboardName)
                .isFalse();
    }

    @Test
    @Tags(value = {@Tag("regression"), @Tag("normal"), @Tag("SP-T621")})
    @DisplayName("SP-T621: Check \"View\" in action button on MyIntelliboard page\n")
    public void checkViewByActionButton() {
        int numberOfDashboard = 1;

        HeaderObject.init().openMyIntelliBoardPage();
        MyIntelliBoardPage my = MyIntelliBoardPage.init();

        //Open Dashboard
        String dashboardName = my.getNameofDasnboardByOrderNumber(numberOfDashboard);
        my.openView(numberOfDashboard);
        IBNextAbstractTest ibNextAbstractTest = new IBNextAbstractTest();
        ibNextAbstractTest.waitForPageLoaded();

        assertThat($x("//div[contains (@class,'data-set-title')]").getText().equals(dashboardName))
                .isTrue();
    }

    @Test
    @Tags(value = {@Tag("regression"), @Tag("normal"), @Tag("SP-T620")})
    @DisplayName("SP-T620: \"Set as default\" in actions on MyIntelliboard page")
    public void checkSetAsDefaultActionButton() {
        int numberOfDashboard = 1;

        HeaderObject.init().openMyIntelliBoardPage();
        MyIntelliBoardPage my = MyIntelliBoardPage.init();

        //Open Dashboard
        String dashboardName = my.getNameofDasnboardByOrderNumber(numberOfDashboard);
        my.openView(numberOfDashboard);
        IBNextAbstractTest ibNextAbstractTest = new IBNextAbstractTest();
        ibNextAbstractTest.waitForPageLoaded();

        assertThat($x("//div[contains (@class,'data-set-title')]").getText().equals(dashboardName))
                .isTrue();
    }

    @Test
    @Tags(value = {@Tag("regression"), @Tag("normal"), @Tag("SP-T621")})
    @DisplayName("SP-T621: Check \"Edit\" in action button on MyIntelliboard page\n")
    public void checkEditByActionButton() {
        int numberOfDashboard = 1;

        HeaderObject.init().openMyIntelliBoardPage();
        MyIntelliBoardPage my = MyIntelliBoardPage.init();

        //Open Dashboard
        String dashboardName = my.getNameofDasnboardByOrderNumber(numberOfDashboard);
        my.openEdit(numberOfDashboard);
        IBNextAbstractTest ibNextAbstractTest = new IBNextAbstractTest();
        ibNextAbstractTest.waitForPageLoaded();

        String ag = CreateDashboardPage.init().getTitleName();

        assertThat(ag.equals(dashboardName))
                .isTrue();
    }
}
