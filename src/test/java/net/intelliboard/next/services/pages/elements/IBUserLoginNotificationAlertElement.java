package net.intelliboard.next.services.pages.elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class IBUserLoginNotificationAlertElement {

    static SelenideElement alertBody = $x("//li[@class='user-alert']");

    public static IBUserLoginNotificationAlertElement init() {
        alertBody.should(Condition.visible);
        return new IBUserLoginNotificationAlertElement();
    }

    @Step("Log out from IBUser")
    public void logOut() {
        $x("//a[contains(@href,'/impersonate/leave')]")
                .click();
        alertBody.shouldNot(Condition.visible);
    }
}
