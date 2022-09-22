package net.intelliboard.next.services.pages.connections.categories;

import com.codeborne.selenide.Condition;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class CreateConnectionCategoryPage {
    public static CreateConnectionCategoryPage init() {
        $x("//form[contains (@action,'/categories')]")
                .shouldBe(Condition.visible, Duration.ofSeconds(90));
        return new CreateConnectionCategoryPage();
    }

    public CreateConnectionCategoryPage fillInCategoryName(String categoryName){
        $x("//input[@id='connectionCategoryName']")
                .sendKeys(categoryName);
        return this;
    }

    public ConnectionCategoriesListPage saveCategory(){
        $x("//button[@type='submit']")
                .click();
        return ConnectionCategoriesListPage.init();
    }
}
