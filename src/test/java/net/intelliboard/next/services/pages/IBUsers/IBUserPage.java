package net.intelliboard.next.services.pages.IBUsers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
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

    public IBUserPage acceptPolicy(IBUserPolicyEnum policy) {

        SelenideElement policyButton =
                $x("//tr[.//a[contains(text(),'" + policy.value + "')]]//a[contains (@class, 'btn')]");

        if (policyButton.getText().contains("Accept")) {
            policyButton.click();
            policyButton.shouldNot(Condition.text("Accept"), Duration.ofSeconds(30));
        }
        return this;
    }

    public UserAuditLogsPage openAuditLogs() {
        AuditLogsButton.click();
        return UserAuditLogsPage.init();
    }

    public UserLoginLogsPage openLoginLogs() {
        LoginLogsButton.click();
        return UserLoginLogsPage.init();
    }

    public boolean isAuditLogsButtonExist() {
        return AuditLogsButton.exists();
    }

    public String getUserName() {
        return $x("//div[contains (@class,'profile-content-header')]//h1/strong")
                .getText();
    }

    public IBUserEditPage openEditProfilePage() {
        $x("//a[contains (@href, '/profile/edit') and contains (@class, 'app-button')]")
                .click();
        return IBUserEditPage.init();
    }

    public String getFirstName() {
        return StringUtils.substringBefore(getUserName(), " ");
    }

    public String getLastName() {
        return StringUtils.substringAfter(getUserName(), " ");
    }

    public String getCity() {
        return $x("//div[@class='card-body']//div[@class='row' and .//strong[contains (text(),'City')]]/div[@class='col-auto']")
                .getText();
    }

    public String getZIP() {
        return $x("//div[@class='card-body']//div[@class='row' and .//strong[contains (text(),'Zip')]]/div[@class='col-auto']")
                .getText();
    }

    public String getAddress() {
        return $x("//div[@class='card-body']//div[@class='row' and .//strong[contains (text(),'Address')]]/div[@class='col-auto']")
                .getText();
    }

    public String getState() {
        return $x("//div[@class='card-body']//div[@class='row' and .//strong[contains (text(),'State')]]/div[@class='col-auto']")
                .getText();
    }

    public int getConnectionsNumber(){
        return Integer.parseInt($x("//span[@class='h4' and ./following-sibling::span[contains (text(),'Connections')]]")
                .getText());
    }

    public void deleteUserAccount(){
        $x("//button[contains (text(),'Delete Account')]").
                click();
        $x("//a[contains (@href,'/profile/deactivate')]")
                .click();
    }
}