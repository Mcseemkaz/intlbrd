package net.intelliboard.next.services.pages.signup;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
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

    public SignUpFormPage fillInFormField(SignUpFormFieldTypeEnum fieldType, String value) {
        if (fieldType.value.contains("select")) {
            $x(fieldType.value).selectOption(value);
        } else {
            $x(fieldType.value).setValue(value);
        }
        return this;
    }

    public void submitForm() {
        $x("//button[@type='submit']").click();
        $x("//div[contains(@class, 'congrats')]//h2[contains(@class, 'text-success')]")
                .shouldBe(Condition.visible);
    }

    public SignUpFormPage agreeTermsPolicy() {
        $x("//input[@id='terms_of_use_agreement']").click();
        return this;
    }
}
