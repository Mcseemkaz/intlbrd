package net.intelliboard.next.services.pages.IBUsers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import net.intelliboard.next.services.pages.auditlogs.UserAuditLogsPage;
import net.intelliboard.next.services.pages.loginlogs.UserLoginLogsPage;
import org.apache.commons.lang3.StringUtils;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class IBUserPage {

    SelenideElement AuditLogsButton = $x("//a[contains (@href, '/audit-logs')]//h3");
    SelenideElement LoginLogsButton = $x("//a[contains (@href, '/login-logs')]//h3");

    public static IBUserPage init() {
        $x("//div[contains(@class, 'profile-content-header')]")
                .shouldBe(Condition.visible);
        return new IBUserPage();
    }

    @Step("Accept Policy")
    public IBUserPage acceptPolicy(IBUserPolicyEnum policy) {

        SelenideElement policyButton =
                $x("//tr[.//a[contains(text(),'" + policy.value + "')]]//a[contains (@class, 'btn')]");

        if (policyButton.getText().contains("Accept")) {
            policyButton.click();
            policyButton.shouldNot(Condition.text("Accept"), Duration.ofSeconds(30));
        }
        return this;
    }

    @Step("Open Audit logs")
    public UserAuditLogsPage openAuditLogs() {
        AuditLogsButton.click();
        return UserAuditLogsPage.init();
    }

    @Step("Open Login logs")
    public UserLoginLogsPage openLoginLogs() {
        LoginLogsButton.click();
        return UserLoginLogsPage.init();
    }

    public boolean isAuditLogsButtonExist() {
        return AuditLogsButton.exists();
    }

    @Step("Get User Name")
    public String getUserName() {
        return $x("//div[contains (@class,'profile-content-header')]//h1/strong")
                .getText();
    }

    @Step("Open Edit Profile")
    public IBUserEditPage openEditProfilePage() {
        $x("//a[contains (@href, '/profile/edit') and contains (@class, 'app-button')]")
                .click();
        return IBUserEditPage.init();
    }

    @Step("Get First Name")
    public String getFirstName() {
        return StringUtils.substringBefore(getUserName(), " ");
    }

    @Step("Get Last Name")
    public String getLastName() {
        return StringUtils.substringAfter(getUserName(), " ");
    }

    @Step("Get City Name")
    public String getCity() {
        return getProfileValue("City");
    }

    @Step("Get Zip")
    public String getZIP() {
        return getProfileValue("Zip");
    }

    @Step("Get Address")
    public String getAddress() {
        return getProfileValue("Address");
    }

    @Step("Get State")
    public String getState() {
        return getProfileValue("State");
    }

    @Step("Get Date Format")
    public String getDateFormat() {
        return getProfileValue("Date Format");
    }

    @Step("Get Connection Number")
    public int getConnectionsNumber() {
        return Integer.parseInt($x("//span[@class='h4' and ./following-sibling::span[contains (text(),'Connections')]]")
                .getText());
    }

    @Step("Delete User Account")
    public void deleteUserAccount() {
        $x("//button[contains (text(),'Delete Account')]").
                click();
        $x("//a[contains (@href,'/profile/deactivate')]")
                .click();
    }

    private String getProfileValue(String valueName) {
        return $x("//div[@class='card-body']//div[@class='row' and .//strong[contains (text(),'" + valueName + "')]]/div[@class='col-auto']")
                .getText();
    }
}