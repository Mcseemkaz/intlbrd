package net.intelliboard.next.services.pages.report.builder;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class BuilderRightSideBarLayoutPage {

    private SelenideElement displayElementsBlock = $x("(//section[@class='h-100'])[1]");

    public static BuilderRightSideBarLayoutPage init() {
        return new BuilderRightSideBarLayoutPage();
    }

    private BuilderRightSideBarLayoutPage openDisplayElementsList() {
        if (!displayElementsBlock.isDisplayed()) {
            $x("//a[contains (text(),'Add Field')]").click();
            displayElementsBlock.shouldBe(Condition.visible, Duration.ofSeconds(30));
        }
        return this;
    }

    private BuilderRightSideBarLayoutPage openDisplayElementMain(ReportBuilderDisplayElementsMainEnum menuElement) {
        SelenideElement displayMainElement = $x("//a[ ./strong[contains (text(),'" + menuElement.value + "')]]");
        SelenideElement elementUpArrowIcon = $x("//a[ ./strong[contains (text(),'" + menuElement.value + "')]]/span[@class='up-Arrow']");
        Selenide.actions().moveToElement(displayMainElement);
        if (!elementUpArrowIcon.isDisplayed()) {
            displayMainElement.click();
            elementUpArrowIcon.shouldBe(Condition.visible);
        }
        return this;
    }

    public BuilderRightSideBarLayoutPage addDisplayElement(ReportBuilderDisplayElementsMainEnum menuElement, ReportBuilderDisplayElementEnum displayElement) {
        openDisplayElementsList();
        openDisplayElementMain(menuElement);
        $x("//a[ .//span[contains (text(),'" + displayElement.value + "')]]").click();
        $x("//div[@class='select-list-wrapper']//li[./a[contains(text(),'" + displayElement.value + "')]]")
                .shouldBe(Condition.visible, Duration.ofSeconds(30));
        return this;
    }
}
