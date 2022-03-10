package net.intelliboard.next.services.pages.header;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$x;

public class HeaderObject {

    public static HeaderObject init() {
        $x("//header//div[contains(@class, 'left-header-section')]")
                .shouldBe(Condition.visible);
        return new HeaderObject();
    }

    public HeaderDropDownMenu openDropDownMenu() {
        $x("//ul[contains (@class, 'user-info')][2]").click();
        return HeaderDropDownMenu.init();
    }
}
