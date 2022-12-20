package net.intelliboard.next.services.pages.IBUsers;

import com.codeborne.selenide.Condition;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class IBUserAssignmentsPage {

    public static IBUserAssignmentsPage init() {
        $x("//form[contains (@action ,'/assign')]")
                .shouldBe(Condition.visible, Duration.ofSeconds(30));
        return new IBUserAssignmentsPage();
    }

    
}
