package net.intelliboard.next.services.pages.myintelliboard;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.IBNextURLs;
import net.intelliboard.next.services.pages.dashboard.DashboardPage;

import static com.codeborne.selenide.Selenide.$x;

public class MyIntelliBoardPage {

    public static MyIntelliBoardPage init() {
        IBNextAbstractTest ibNextAbstractTest = new IBNextAbstractTest();
        ibNextAbstractTest.waitForPageLoaded();
        $x("//div[@class='content-body']").shouldBe(Condition.visible);
        ibNextAbstractTest.checkPageURL(IBNextURLs.MY_INTELLIBOARD_PAGE);
        return new MyIntelliBoardPage();
    }

    public boolean isDashboardPresentsByOrderNumber(int number){
        return $x("//div[contains(@class,'cards-view')]//li["+number+"]//div[contains (@class,'data-library-info')]//h4")
                .exists();
    }

    public boolean isDashboardPresentsByName(String dashboardName){
        return $x("//div[contains (@class,'data-library-info')]//h4[contains (text(),'"+dashboardName+"')]")
                .exists();
    }

    public MyIntelliBoardPage searchDashboard(String searchString) {
        $x("//input[contains (@class,'input-search')]").setValue(searchString);
        return this;
    }

    public MyIntelliBoardPage setDashboardFavorite(int numberDashboard) {
        Selenide.actions()
                .moveToElement($x("//div[@class='data-library-list']//li["+numberDashboard+"]//div[contains (@class,'data-library-item-wrapper')]"))
                .click($x("(//div[@class='data-library-list']//li["+numberDashboard+"]//div[contains (@class,'data-library-item')]//span[@class='action-item'])[1]"))
                .perform();
        return this;
    }

    public MyIntelliBoardPage openView(int numberDashboard) {
        Selenide.actions()
                .moveToElement($x("//div[@class='data-library-list']//li["+numberDashboard+"]//div[contains (@class,'data-library-item-wrapper')]"))
                .click($x("//div[@class='data-library-list']//li["+numberDashboard+"]//div[contains (@class,'data-library-item')]//span[@class='dropdown-trigger']//span[@class='action-item']"))
                .click($x("//div[contains (@class,'dropdown-body')]//a[ .//ion-icon[@name='eye-outline']]"))
                .perform();
        return this;
    }

    public DashboardPage openEdit(int numberDashboard) {
        Selenide.actions()
                .moveToElement($x("//div[@class='data-library-list']//li["+numberDashboard+"]//div[contains (@class,'data-library-item-wrapper')]"))
                .click($x("//div[@class='data-library-list']//li["+numberDashboard+"]//div[contains (@class,'data-library-item')]//span[@class='dropdown-trigger']//span[@class='action-item']"))
                .click($x("//div[contains (@class,'dropdown-body')]//a[ .//ion-icon[@name='create-outline']]"))
                .perform();
        return DashboardPage.init();
    }

    public String getNameofDasnboardByOrderNumber(int number) {
        return $x("//div[contains(@class,'cards-view')]//li["+number+"]//div[contains (@class,'data-library-item')]//div[contains(@class,'data-library-item-info')]//h4")
                .getText();
    }

    public boolean isDashboardPresentsInFavorite(String dashboardName) {
        return $x("//div[@class='data-library-list' and  .//h2[contains (text(),'Favorites')]]//h4[@class='title' and contains (text(),'"+dashboardName+"')]")
                .exists();
    }
}
