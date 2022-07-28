package net.intelliboard.next.services.pages.report.builder;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$x;

public class BuilderReportFormulaElement {
    public static BuilderReportFormulaElement init() {
        $x("//div[@class='vue-codemirror']").shouldBe(Condition.visible);
        return new BuilderReportFormulaElement();
    }

    public BuilderReportFormulaElement addFormulaText(String formulaText){
        $x("//div[@class='vue-codemirror']//div[contains (@class,'intelliboard')]//textarea")
                .setValue(formulaText);
        return this;
    }

    public BuilderReportFormulaElement addFormulaText(ReportBuilderDisplayElementsMainEnum mainElement,ReportBuilderDisplayElementEnum element){
        BuilderRightSideBarTableLayoutPage.init().addDisplayElement(mainElement, element);
        return this;
    }

    public BuilderReportFormulaSavingModal saveFormula(){
        $x("//div[@class='data-actions']//a[contains (text(),'Save')]").click();
        return BuilderReportFormulaSavingModal.init();
    }
}
