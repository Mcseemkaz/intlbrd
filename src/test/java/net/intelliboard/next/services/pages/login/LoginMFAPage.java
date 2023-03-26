package net.intelliboard.next.services.pages.login;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import net.intelliboard.next.services.pages.elements.spinners.PageSpinner;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class LoginMFAPage {

    public static LoginMFAPage init() {
        $x("//h1[contains (text(), 'Step Verification')]")
                .should(Condition.visible);
        return new LoginMFAPage();
    }

    @Step("Fill in Auth Code")
    public LoginMFAPage fillInAuthCode(char[] authCode) {
        ElementsCollection inputs = $$x("//input[@class='form-control-single-number']");

        int i = 0;
        for (SelenideElement input : inputs) {
            input.setValue(String.valueOf(authCode[i]));
            i++;
        }
        return this;
    }

    public void submitForm() {
        $x("//button[@type='submit']").click();
        PageSpinner.waitPreloader();
        PageSpinner.waitSpinner();
    }
}
