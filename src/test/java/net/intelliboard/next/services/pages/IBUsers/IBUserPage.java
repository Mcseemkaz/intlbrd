package net.intelliboard.next.services.pages.IBUsers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import net.intelliboard.next.services.pages.auditlogs.UserAuditLogsPage;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class IBUserPage {

    SelenideElement AuditLogsButton = $x("//a[contains (@href, '/audit-logs')]//h5");

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
}
