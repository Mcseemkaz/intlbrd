package net.intelliboard.next.services.pages.inform;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import net.intelliboard.next.services.pages.elements.spinners.PageSpinner;

import java.time.LocalDateTime;

import static com.codeborne.selenide.Selenide.$x;

public class InFormImportProcessingPage {
    public static InFormImportProcessingPage init() {
        PageSpinner.waitPreloader();
        PageSpinner.waitPreloader();
        $x("//div[@class='progress-tracker']").should(Condition.visible);
        return new InFormImportProcessingPage();
    }

    private boolean isCompleted() {
        return $x("(//div[@class='stat-item'])[1]/strong")
                .getText()
                .contains("Completed");
    }

    @Step("Wait that InForm Process is completed")
    public InFormImportProcessingPage waitingProcessingComplete() throws InterruptedException {
        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime currentTime;
        LocalDateTime deltaTimeOverall = startTime.plusMinutes(15);

        while (!isCompleted()) {
            currentTime = LocalDateTime.now();
            LocalDateTime deltaTime = currentTime.plusMinutes(5);
            while (!isCompleted()) {
                Thread.sleep(200);
                currentTime = LocalDateTime.now();
                if (currentTime.isAfter(deltaTime)) {
                    break;
                }
            }
            Selenide.refresh();
            if (currentTime.isAfter(deltaTimeOverall)) {
                break;
            }
        }
        return this;
    }
}
