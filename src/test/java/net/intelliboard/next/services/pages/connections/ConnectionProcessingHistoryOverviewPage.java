package net.intelliboard.next.services.pages.connections;

import com.codeborne.selenide.Condition;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class ConnectionProcessingHistoryOverviewPage {
    public static ConnectionProcessingHistoryOverviewPage init() {
        $x("//div[contains (@class,'audit-totals')]")
                .shouldBe(Condition.visible, Duration.ofSeconds(180));
        return new ConnectionProcessingHistoryOverviewPage();
    }
}