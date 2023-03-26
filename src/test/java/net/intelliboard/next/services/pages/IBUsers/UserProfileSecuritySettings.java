package net.intelliboard.next.services.pages.IBUsers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import net.intelliboard.next.services.pages.elements.spinners.PageSpinner;

import static com.codeborne.selenide.Selenide.$x;

public class UserProfileSecuritySettings {

    SelenideElement mfaButton = $x("//a[contains (@href,'/switch-2fa')]");

    @Step("User Profile Settings Init")
    public static UserProfileSecuritySettings init() {
        return new UserProfileSecuritySettings();
    }

    @Step("Set Current Password")
    public UserProfileSecuritySettings setCurrentPassword(String currentPassword) {
        $x("//input[@id='current_password']")
                .setValue(currentPassword);
        return this;
    }

    @Step("Set New Password")
    public UserProfileSecuritySettings setNewPassword(String newPassword) {
        $x("//input[@id='new_password']")
                .setValue(newPassword);
        return this;
    }

    @Step("Set Confirm New Password")
    public UserProfileSecuritySettings setConfirmNewPassword(String newPassword) {
        $x("//input[@id='confirm_password']")
                .setValue(newPassword);
        return this;
    }

    @Step("Turn on MFA")
    public UserProfileSecuritySettings turnMFA() {
        if (mfaButton.getText().contains("Enable")) {
            mfaButton.click();
            PageSpinner.waitPreloader();
            PageSpinner.waitSpinner();
        }
        return this;
    }

    @Step("Turn on Email MFA")
    public UserProfileSecuritySettings turnMFAEmail() {
        SelenideElement mfaToggle =
                $x("//div[@class='list-group-item' and .//h5[@class='mb-1' and contains (text(),'Email')]]//input[@id='setup']");
        if(!mfaToggle.is(Condition.checked)) {
            mfaToggle.click();
            PageSpinner.waitPreloader();
            PageSpinner.waitSpinner();
        }

        return this;
    }

    @Step("Save Settings")
    public void saveSettings() {
        $x("//div[contains (@class, 'card') and .//div[contains (text(),'Change Password')]]//button[@type='submit']")
                .click();
    }
}
