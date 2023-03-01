package net.intelliboard.next.services.pages.IBUsers;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import net.intelliboard.next.services.helpers.UnitedStatesListEnum;
import net.intelliboard.next.services.pages.elements.DropdownElement;
import net.intelliboard.next.services.pages.elements.enums.DateFormatEnum;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class IBUserEditPage {

    public static IBUserEditPage init() {
        $x("//form[contains (@action, '/profile')]").shouldBe(Condition.visible, Duration.ofSeconds(90));
        return new IBUserEditPage();
    }

    @Step("Fill in First Name")
    public IBUserEditPage setFirstName(String firstName) {
        $x("//input[@id='first_name']").setValue(firstName);
        return this;
    }

    @Step("Fill in Last Name")
    public IBUserEditPage setLastName(String lastName) {
        $x("//input[@id='last_name']").setValue(lastName);
        return this;
    }

    @Step("Fill in City Name")
    public IBUserEditPage setCity(String cityName) {
        $x("//input[@id='city']").setValue(cityName);
        return this;
    }

    @Step("Fill in Zip")
    public IBUserEditPage setZIP(String zip) {
        $x("//input[@id='zip']").setValue(zip);
        return this;
    }

    @Step("Fill in Address")
    public IBUserEditPage setAddress(String address) {
        $x("//input[@id='address']").setValue(address);
        return this;
    }

    @Step("Submit Form")
    public IBUserPage submitForm() {
        $x("//button[@type='submit' and contains (text(),'Save')]").click();
        return IBUserPage.init();
    }

    @Step("Fill in State")
    public IBUserEditPage setState(UnitedStatesListEnum state) {
        $x("//div[contains (@class,'form-group') and @name='state']//button").click();
        $x("//li//strong[contains (text(),'" + state.fullName + "')]").click();
        return this;
    }

    @Step("Set Date Format")
    public IBUserEditPage setDateFormat(DateFormatEnum dateFormat) {
        DropdownElement.init("Date Format", 1)
                .selectOption(dateFormat.value);
        return this;
    }
}
