package net.intelliboard.next.services.pages.library;

import com.codeborne.selenide.Condition;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class LibraryMainPage {

    public static LibraryMainPage init() {
        $x("//div[contains (@class, 'data-library-wrapper')]").shouldBe(Condition.visible,
                Duration.ofSeconds(90));
        return new LibraryMainPage();
    }

    public LibraryMainPage searchLibraryItem(String searchItem) {
        $x("//div[@class='library-categories-search']/input")
                .sendKeys(searchItem);
        return this;
    }

    public int getLibraryItemsNumberByType(LibraryItemTypeEnum type){
        return $$x("//div[@class='data-library-list' and .//h2[contains (text(), '"+type.value+"')]]//li")
                .size();
    }
}
