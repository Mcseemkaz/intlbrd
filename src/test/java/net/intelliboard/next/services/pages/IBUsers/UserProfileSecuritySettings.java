package net.intelliboard.next.services.pages.IBUsers;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class UserProfileSecuritySettings {
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

    @Step("Save Settings")
    public void saveSettings() {
        $x("//div[contains (@class, 'card') and .//div[contains (text(),'Change Password')]]//button[@type='submit']")
                .click();
    }
}
