package net.intelliboard.next.services.pages.inform;

import com.codeborne.selenide.Condition;
import net.intelliboard.next.services.pages.elements.spinners.PageSpinner;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class InFormViewPage {

    public static InFormViewPage init() {
        PageSpinner.waitSpinner();
        PageSpinner.waitSpinner();
        $x("//div[@class='in-form-form-wrapper']")
                .should(Condition.visible, Duration.ofSeconds(60));
        return new InFormViewPage();
    }

    public InFormViewPage inputTextArea(String fieldName, String value) {
        $x("//div[@class='in-form-form-item'][.//label[contains (text(),'"+fieldName+"')]]//textarea")
                .setValue(value);
        return this;
    }

    public InFormViewPage inputInput(String fieldName, String value) {
        $x("//div[@class='in-form-form-item'][.//label[contains (text(),'"+fieldName+"')]]//input")
                .setValue(value);
        return this;
    }

    public void saveFormData() {
        $x("//div[contains (@class,'form-action')]//button[@type='submit']")
                .click();
    }
}
