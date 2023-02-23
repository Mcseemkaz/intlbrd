package net.intelliboard.next.services.pages.library;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import net.intelliboard.next.services.pages.connections.ConnectionsTypeEnum;
import net.intelliboard.next.services.pages.elements.spinners.PageSpinner;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class LibraryMainPage {

    int sleepTime = 2000;


    @Step("Library Page init")
    public static LibraryMainPage init() {
        PageSpinner.waitPreloader();
        PageSpinner.waitSpinner();
        $x("//div[contains (@class, 'data-library-wrapper')]").shouldBe(Condition.visible,
                Duration.ofSeconds(90));
        return new LibraryMainPage();
    }

    @Step("Search Library item")
    public LibraryMainPage searchLibraryItem(String searchItem) {
        $x("//div[@class='library-categories-search']/input")
                .setValue(searchItem)
                .sendKeys(Keys.ENTER);
        PageSpinner.waitPreloader();
        PageSpinner.waitSpinner();
        Selenide.sleep(sleepTime);
        return this;
    }

    public int getLibraryItemsNumberByType(LibraryItemTypeEnum type) {
        return $$x("//div[@class='data-library-list' and .//h2[contains (text(), '" + type.value + "')]]//li")
                .size();
    }

    @Step("Like Library item")
    public LibraryMainPage likeItem(String itemName) {
        if (
                $x("//li[ .//h4[contains (text(), '" + itemName + "')]]//ion-icon")
                        .getAttribute("name").equals("heart-outline")
        ) {
            $x("//li[ .//h4[contains (text(), '" + itemName + "')]]//span[@class='action-item']")
                    .click();
        }
        Selenide.sleep(sleepTime);
        return this;
    }

    @Step("Unlike Library item")
    public LibraryMainPage unlikeItem(String itemName) {
        if (
                !$x("//li[ .//h4[contains (text(), '" + itemName + "')]]//ion-icon")
                        .getAttribute("name").equals("heart-outline")
        ) {
            $x("//li[ .//h4[contains (text(), '" + itemName + "')]]//span[@class='action-item']")
                    .click();
        }
        Selenide.sleep(sleepTime);
        return this;
    }

    public int getNumberOfLikesItem(String itemName) {
        return Integer.parseInt($x("//li[ .//h4[contains (text(), '" + itemName + "')]]//div[@class='data-library-item-date']").getText());
    }

    @Step("Order Library item By Type")
    public LibraryMainPage orderItemsBy(LibraryOrderTypeEnum name) {
        $x("//div[contains (@class, 'data-sets-order')]//a[contains (text(),'" + name.value + "')]")
                .click();
        $x("//div[contains (@class, 'data-sets-order')]//a[contains (text(),'" + name.value + "')]/span")
                .shouldBe(Condition.visible);
        Selenide.sleep(sleepTime);
        return this;
    }

    public String getLibraryItemName(LibraryItemTypeEnum type, int numberOfItem) {
        return $x("//div[@class='data-library-list' and .//h2[contains (text(), '" + type.value + "')]]//li[" + numberOfItem + "]//h4[@class='title']")
                .getText();
    }

    @Step("Set Active report for connection")
    public LibraryMainPage setActiveReportsForConnection(ConnectionsTypeEnum connection) {
        $x("//button[contains (@class, 'data-sets-panel')]")
                .click();
        $x("//button[contains (@class, 'tree-choice')]")
                .click();
        $x("//li//strong[text()='" + connection.value + "']")
                .click();
        $x("//div[contains (@class,'data-filters-body')]/following-sibling::button[@type='submit']")
                .click();
        Selenide.sleep(sleepTime);
        return this;
    }

    @Step("Searh Library item by Tag")
    public LibraryMainPage searchByTag(String tagName) {
        SelenideElement tag = $x("//li[ ./a//span[@class='title' and contains (text(),'" + tagName + "')]]");
        if (tag.isDisplayed()) {
            tag.click();
        } else {
            while (!tag.isDisplayed()) {
                $x("//div[@class='library-categories-items']//button[@aria-label='Next slide']").click();
                Selenide.sleep(sleepTime);
            }
        }
        Selenide.sleep(sleepTime);
        return this;
    }

    @Step("Check that Tag is presented on the particular item")
    public boolean checkTagPresentsInItem(LibraryItemTypeEnum type, int numberOfItem, String tagName) {
        Selenide
                .actions()
                .moveToElement($x("//div[@class='data-library-list' and .//h2[contains (text(), '" + type.value + "')]]//li[" + numberOfItem + "]"))
                .perform();

        return $x("//div[@class='data-library-list' and .//h2[contains (text(), '" + type.value + "')]]//li[" + numberOfItem + "]//div[contains (@class, 'data-library-info-popup')]//span[contains (text(), '" + tagName + "')]").exists();
    }
}
