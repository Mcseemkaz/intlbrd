package net.intelliboard.next.services.pages.report.builder;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class BuilderReportFormulaSavingModal {
    public static BuilderReportFormulaSavingModal init() {
        $x("//div[@class='modal-content']//div[@class='app-modal-body']").shouldBe(Condition.visible);
        return new BuilderReportFormulaSavingModal();
    }

    public BuilderReportFormulaSavingModal fillInTitle(String title) {
        SelenideElement formulaTitle = $x("//input[@id='formulaTitle']");
        Selenide.sleep(1000);
        formulaTitle.click();
        formulaTitle.setValue(title);
        return this;
    }

    public BuilderReportFormulaSavingModal fillInDescription(String title) {
        Selenide.sleep(1000);
        $x("//textarea[@name='description']").sendKeys(title);
        return this;
    }

    public void submitFormula() {
        $x("//div[@class='app-modal-body']//button[@type='submit']").click();
        $x("//div[@class='modal-content']//div[@class='app-modal-body']")
                .shouldBe(Condition.not(Condition.visible));
    }
}
