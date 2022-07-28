package net.intelliboard.next.services.pages.report.builder;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$x;

public class BuilderReportFormulaSavingModal {
    public static BuilderReportFormulaSavingModal init() {
        $x("//div[@class='modal-content']//div[@class='app-modal-body']").shouldBe(Condition.visible);
        return new BuilderReportFormulaSavingModal();
    }

    public BuilderReportFormulaSavingModal fillInTitle(String title){
        $x("//input[@id='formulaTitle']").setValue(title);
        return this;
    }

    public BuilderReportFormulaSavingModal fillInDescription(String title){
        $x("//input[@id='formulaTitle']").setValue(title);
        return this;
    }

    public void submitFormula(){
        $x("//div[@class='app-modal-body']//button[@type='submit']").click();
    }
}
