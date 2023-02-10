package net.intelliboard.next.services.pages.elements.spinners;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$x;

public class PageSpinner {

    @Getter
    static SelenideElement spinner = $x("//div[@class='loader-inner']");
}
