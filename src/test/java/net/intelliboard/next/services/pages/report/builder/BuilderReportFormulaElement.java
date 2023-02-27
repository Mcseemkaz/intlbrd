package net.intelliboard.next.services.pages.report.builder;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class BuilderReportFormulaElement {
    public static BuilderReportFormulaElement init() {
        $x("//div[@class='vue-codemirror']").shouldBe(Condition.visible);
        return new BuilderReportFormulaElement();
    }

    @Step("Add Formula")
    public BuilderReportFormulaElement addFormulaText(String formulaText) {
        $x("//pre[@class=' CodeMirror-line ']").click();
        $x("//div[contains(@class,'intelliboard')]//textarea")
                .sendKeys(formulaText);
        return this;
    }

    public BuilderReportFormulaElement addFormulaText(ReportBuilderDisplayElementsMainEnum mainElement, ReportBuilderDisplayElementEnum element) {
        BuilderRightSideBarTableLayoutPage.init().addDisplayElement(mainElement, element);
        return this;
    }

    @Step("Save Formula")
    public BuilderReportFormulaSavingModal saveFormula() {
        $x("//div[@class='data-actions']//a[contains (text(),'Save')]").click();
        return BuilderReportFormulaSavingModal.init();
    }
}
