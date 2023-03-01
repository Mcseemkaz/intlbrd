package net.intelliboard.next.services.pages.connections;

import com.codeborne.selenide.Condition;
import net.intelliboard.next.services.pages.elements.spinners.PageSpinner;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class ConnectionProcessingHistoryOverviewPage {
    public static ConnectionProcessingHistoryOverviewPage init() {
        PageSpinner.waitPreloader();
        PageSpinner.waitSpinner();
        $x("//div[contains (@class,'audit-totals')]")
                .shouldBe(Condition.visible, Duration.ofSeconds(60));
        return new ConnectionProcessingHistoryOverviewPage();
    }
}