package net.intelliboard.next.services.pages.library;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class LibraryMainPage {

    int sleepTime = 2000;

    public static LibraryMainPage init() {
        $x("//div[contains (@class, 'data-library-wrapper')]").shouldBe(Condition.visible,
                Duration.ofSeconds(90));
        return new LibraryMainPage();
    }

    public LibraryMainPage searchLibraryItem(String searchItem) {
        $x("//div[@class='library-categories-search']/input")
                .sendKeys(searchItem);
        Selenide.sleep(sleepTime);
        return this;
    }

    public int getLibraryItemsNumberByType(LibraryItemTypeEnum type) {
        return $$x("//div[@class='data-library-list' and .//h2[contains (text(), '" + type.value + "')]]//li")
                .size();
    }

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
}
