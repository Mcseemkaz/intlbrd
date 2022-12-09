package net.intelliboard.next.services.pages.IBUsers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import net.intelliboard.next.services.pages.auditlogs.UserAuditLogsPage;
import org.apache.commons.lang3.StringUtils;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class IBUserPage {

    SelenideElement AuditLogsButton = $x("//a[contains (@href, '/audit-logs')]//h3");

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
}
