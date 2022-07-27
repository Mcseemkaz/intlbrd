package net.intelliboard.next.services.pages.report.builder;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class BuilderRightSideBarPaiChartLayoutPage {

    public static BuilderRightSideBarPaiChartLayoutPage init() {
        $x("//div[@class='data-controls-section']").shouldBe(Condition.visible);
        return new BuilderRightSideBarPaiChartLayoutPage();
    }

    private BuilderRightSideBarPaiChartLayoutPage openDisplayElementsList(BuilderRightSideBarPaiChartValuesTypeEnum valueType) {
        SelenideElement addCategoriesListLabel = $x("//a[ preceding-sibling::span[contains(text(),'" + valueType.value + "')]]");
        if (addCategoriesListLabel.isDisplayed()) {
            addCategoriesListLabel.click();
        }
        return this;
    }

    public BuilderRightSideBarPaiChartLayoutPage addDisplayElement(ReportBuilderDisplayElementsMainEnum menuElement, ReportBuilderDisplayElementEnum displayElement, BuilderRightSideBarPaiChartValuesTypeEnum valueType) {
        openDisplayElementsList(valueType);
        BuilderRightSideElementsManagerPage
                .init()
                .addDisplayElement(menuElement, displayElement);
        return this;
    }

    public BuilderRightSideBarPaiChartLayoutPage goBackToMainPageOnBar() {
        BuilderRightSideElementsManagerPage.init().returnToLayoutMainPage();
        return this;
    }
}
