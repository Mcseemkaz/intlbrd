package net.intelliboard.next.services.pages.report.builder;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class BuilderRightSideBarTableLayoutPage {

    private SelenideElement displayElementsBlock = $x("//section[@class='optional-block']");

    public static BuilderRightSideBarTableLayoutPage init() {
        return new BuilderRightSideBarTableLayoutPage();
    }

    private BuilderRightSideBarTableLayoutPage openDisplayElementsList() {
        if (!displayElementsBlock.isDisplayed()) {
            $x("//a[contains (text(),'Add Field')]").click();
            displayElementsBlock.shouldBe(Condition.visible, Duration.ofSeconds(30));
        }
        return this;
    }

    public BuilderRightSideBarTableLayoutPage addDisplayElement(ReportBuilderDisplayElementsMainEnum menuElement, ReportBuilderDisplayElementEnum displayElement) {
        openDisplayElementsList();
        BuilderRightSideElementsManagerPage
                .init()
                .addDisplayElement(menuElement, displayElement);
        return this;
    }

    public BuilderRightSideBarTableLayoutPage goBackToMainPageOnBar() {
        BuilderRightSideElementsManagerPage.init().returnToLayoutMainPage();
        return this;
    }

    public BuilderReportFormulaElement openAddFormulaEditor(){
        openDisplayElementsList();
        openFormulaEditor();
        return BuilderReportFormulaElement.init();
    }

    private void openFormulaEditor(){
        $x("//a[@class='formula-link']").click();
    }
}
