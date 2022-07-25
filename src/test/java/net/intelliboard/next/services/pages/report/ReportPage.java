package net.intelliboard.next.services.pages.report;

import com.codeborne.selenide.Condition;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class ReportPage {
    public static ReportPage init() {
        $x("//div[contains (@class, 'data-set') and contains (@class, 'report')]")
                .shouldBe(Condition.visible, Duration.ofSeconds(60));
        return new ReportPage();
    }
}
