package net.intelliboard.next.services.pages.IBUsers;

import com.codeborne.selenide.Condition;
import net.intelliboard.next.services.helpers.UnitedStatesListEnum;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class IBUserEditPage {

    public static IBUserEditPage init() {
        $x("//form[contains (@action, '/profile')]").shouldBe(Condition.visible, Duration.ofSeconds(90));
        return new IBUserEditPage();
    }

    public IBUserEditPage setFirstName(String firstName) {
        $x("//input[@id='first_name']").setValue(firstName);
        return this;
    }

    public IBUserEditPage setLastName(String lastName) {
        $x("//input[@id='last_name']").setValue(lastName);
        return this;
    }

    public IBUserEditPage setCity(String cityName) {
        $x("//input[@id='city']").setValue(cityName);
        return this;
    }

    public IBUserEditPage setZIP(String zip) {
        $x("//input[@id='zip']").setValue(zip);
        return this;
    }

    public IBUserEditPage setAddress(String address) {
        $x("//input[@id='address']").setValue(address);
        return this;
    }

    public IBUserPage submitForm() {
        $x("//button[@type='submit' and contains (text(),'Save')]").click();
        return IBUserPage.init();
    }

    public IBUserEditPage setState(UnitedStatesListEnum state) {
        $x("//div[contains (@class,'form-group') and @name='state']//button").click();
        $x("//li//strong[contains (text(),'"+state.fullName+"')]").click();
        return this;
    }
}
