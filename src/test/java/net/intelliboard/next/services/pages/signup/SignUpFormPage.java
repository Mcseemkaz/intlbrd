package net.intelliboard.next.services.pages.signup;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import net.intelliboard.next.services.IBNextURLs;

import static com.codeborne.selenide.Selenide.$x;
import static org.assertj.core.api.Assertions.assertThat;

public class SignUpFormPage {

    public static SignUpFormPage init() {
        $x("//div[contains (@class,'signup-page')]").shouldBe(Condition.visible);
        String currentURL = WebDriverRunner.getWebDriver().getCurrentUrl();
        assertThat(currentURL).isEqualTo(IBNextURLs.SIGNUP_PAGE);
        return new SignUpFormPage();
    }

    @Step("Fill in Form Field")
    public SignUpFormPage fillInFormField(SignUpFormFieldTypeEnum fieldType, String value) {
        if (fieldType.value.contains("select")) {
            $x(fieldType.value).selectOption(value);
        } else {
            $x(fieldType.value).setValue(value);
        }
        return this;
    }

    @Step("Submit Registration Form")
    public void submitForm() {
        String winHandleBefore = WebDriverRunner.getWebDriver().getWindowHandle();
        $x("//button[@type='submit']").click();
        WebDriverRunner.getWebDriver().switchTo().window(winHandleBefore);
        $x("//div[contains(@class, 'congrats')]//h2[text()]")
                .shouldBe(Condition.visible);
    }

    @Step("Agree Terms&Policy")
    public SignUpFormPage agreeTermsPolicy() {
        Selenide
                .actions()
                .moveToElement($x("//label[@class='custom-control-label']"))
                .moveByOffset(-50, 0)
                .click()
                .perform();
        return this;
    }
}
