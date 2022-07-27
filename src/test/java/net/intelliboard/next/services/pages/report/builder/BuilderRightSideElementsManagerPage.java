package net.intelliboard.next.services.pages.report.builder;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class BuilderRightSideElementsManagerPage {

    private static SelenideElement displayElementsBlock = $x("(//section[@class='h-100'])[1]");

    public static BuilderRightSideElementsManagerPage init() {
        displayElementsBlock.shouldBe(Condition.visible);
        return new BuilderRightSideElementsManagerPage();
    }

    public BuilderRightSideElementsManagerPage addDisplayElement(ReportBuilderDisplayElementsMainEnum menuElement, ReportBuilderDisplayElementEnum displayElement) {
        openDisplayElementMain(menuElement);
        $x("//a[ .//span[contains (text(),'" + displayElement.value + "')]]").click();
        $x("//li[@title='" + displayElement.value + "' or ./a[contains (text(),'" + displayElement.value + "')]]")
                .shouldBe(Condition.visible, Duration.ofSeconds(30));
        return this;
    }

    private BuilderRightSideElementsManagerPage openDisplayElementMain(ReportBuilderDisplayElementsMainEnum menuElement) {
        SelenideElement displayMainElement = $x("//a[ ./strong[contains (text(),'" + menuElement.value + "')]]");
        SelenideElement elementUpArrowIcon = $x("//a[ ./strong[contains (text(),'" + menuElement.value + "')]]/span[@class='up-Arrow']");
        Selenide.actions().moveToElement(displayMainElement);
        if (!elementUpArrowIcon.isDisplayed()) {
            displayMainElement.click();
            elementUpArrowIcon.shouldBe(Condition.visible);
        }
        return this;
    }

    public Object returnToLayoutMainPage() {
        $x("//div[@class='data-controls-controller']/a/ion-icon[@name='chevron-back']").click();
        displayElementsBlock.shouldBe(Condition.not(Condition.visible));
        return this;
    }
}