package net.intelliboard.next.services.pages.report.builder;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;
import static net.intelliboard.next.AbstractTest.SLEEP_TIMEOUT_SHORT;

public class BuilderRightSideBarTableLayoutPage {

    private final SelenideElement displayElementsBlock = $x("//section[@class='optional-block']");

    public static BuilderRightSideBarTableLayoutPage init() {
        return new BuilderRightSideBarTableLayoutPage();
    }
    @Step("Open 'Add Display Element' Block")
    private BuilderRightSideBarTableLayoutPage openDisplayElementsList() {
        if (!displayElementsBlock.isDisplayed()) {
            $x("//a[contains (text(),'Add Field')]")
                    .click();
            displayElementsBlock.shouldBe(Condition.visible, Duration.ofSeconds(30));
        }
        return this;
    }

    @Step("Add display element")
    public BuilderRightSideBarTableLayoutPage addDisplayElement(
            ReportBuilderDisplayElementsMainEnum menuElement,
            ReportBuilderDisplayElementEnum displayElement) {
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

    @Step("Open Formula Editor")
    public BuilderReportFormulaElement openAddFormulaEditor() {
        openDisplayElementsList();
        Selenide.sleep(SLEEP_TIMEOUT_SHORT);
        openFormulaEditor();
        return BuilderReportFormulaElement.init();
    }

    private void openFormulaEditor() {
        Selenide
                .executeJavaScript("document.getElementsByClassName('formula-link')[0].click();");
    }
}
