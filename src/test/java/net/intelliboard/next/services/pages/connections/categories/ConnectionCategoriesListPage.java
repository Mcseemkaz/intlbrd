package net.intelliboard.next.services.pages.connections.categories;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import net.intelliboard.next.IBNextAbstractTest;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class ConnectionCategoriesListPage extends IBNextAbstractTest {

    public static ConnectionCategoriesListPage init() {
        $x("//div[contains (@class,'page-body')]//h1[contains (text(),'Connection Categories')]")
                .shouldBe(Condition.visible, Duration.ofSeconds(120));
        return new ConnectionCategoriesListPage();
    }

    @Step("Open Connection Category")
    public CreateConnectionCategoryPage openConnectionCategory() {
        $x("//a[contains (@href,'/categories/create')]").click();
        return CreateConnectionCategoryPage.init();
    }

    @Step("Create Category")
    public ConnectionCategoriesListPage createCategory(String categoryName) {
        return openConnectionCategory()
                .fillInCategoryName(categoryName)
                .saveCategory();
    }

    @Step("Search Category")
    //TODO MO - Need refactoring - extract Search element as a separate for multi using places
    public ConnectionCategoriesListPage searchCategory(String categoryName) {
        $x("//input[contains (@class, 'search-input') and (@placeholder='Search')]")
                .setValue(categoryName)
                .sendKeys(Keys.ENTER);
        return this;
    }

    public boolean isCategoryExist(String categoryName) {
        return $x("//tr[.//a[contains (text(), '" + categoryName + "')]]")
                .exists();
    }

    @Step("Select Category")
    public ConnectionCategoriesListPage selectCategory(String categoryName, boolean setSelected) {
        SelenideElement checkbox = $x("//a[contains(text(),'" + categoryName + "')]//ancestor-or-self::tr//input[@type='checkbox']");
        if (setSelected == true && checkbox.isSelected() == false) {
            checkbox.click();
            checkbox.should(Condition.selected);
        } else if (setSelected == false && checkbox.isSelected() == true) {
            checkbox.click();
            checkbox.should(Condition.not(Condition.selected));

        }
        return this;
    }

    @Step("Delete selected categories by Action menu")
    public ConnectionCategoriesListPage deleteSelectedCategoriesByActionDropdown() {
        openActionMenu();
        $x("//div[contains(@class, 'intelli-dropdown')][.//strong[contains(text(), 'Action')]]//div[contains(@class, 'dropdown-menu')]//ul//li//a[contains(text(),'Delete Selected')]")
                .click();
        waitForPageLoaded();
        return ConnectionCategoriesListPage.init();
    }

    private ConnectionCategoriesListPage openActionMenu() {
        SelenideElement actionMenuDropdown = $x("//div[contains(@class, 'intelli-dropdown')][.//strong[contains(text(), 'Action')]]");
        actionMenuDropdown.click();
        $x("//div[contains(@class, 'intelli-dropdown')][.//strong[contains(text(), 'Action')]]//div[contains(@class, 'dropdown-menu')]//ul")
                .shouldBe(Condition.visible);
        return this;
    }
}
