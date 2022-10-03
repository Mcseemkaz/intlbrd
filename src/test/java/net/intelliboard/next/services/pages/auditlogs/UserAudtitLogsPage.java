package net.intelliboard.next.services.pages.auditlogs;

import com.codeborne.selenide.Condition;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class UserAudtitLogsPage {

    public static UserAudtitLogsPage init() {
        $x("//div[contains (@class, 'audit-table')]")
                .should(Condition.visible, Duration.ofSeconds(90));
        return new UserAudtitLogsPage();
    }
}
