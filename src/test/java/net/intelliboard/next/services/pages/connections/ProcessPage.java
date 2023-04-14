package net.intelliboard.next.services.pages.connections;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import net.intelliboard.next.services.pages.connections.connection.ConnectionConnectionSettingsMainPage;
import net.intelliboard.next.services.pages.elements.spinners.PageSpinner;
import net.intelliboard.next.services.pages.myintelliboard.MyIntelliBoardPage;
import org.openqa.selenium.TimeoutException;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static com.codeborne.selenide.Selenide.$x;

public class ProcessPage {

    public static ProcessPage init() {
        PageSpinner.waitPreloader();
        PageSpinner.waitSpinner();
        $x("//div[@class='progress-tracker']")
                .shouldBe(Condition.visible, Duration.ofSeconds(100));
        return new ProcessPage();
    }

    public boolean isProcess100Percent() {
        return $x("//div[@class='progress-tracker']//div[contains (@class,'panel-left')]//section[not (@class)]//p")
                .getText()
                .contains("100%");
    }

    public boolean isCompleted() {
        return $x("(//div[@class='stat-item'])[1]/strong")
                .getText()
                .contains("Completed");
    }

    @Step("Wait that Process is completed")
    public ProcessPage waitingProcessingComplete() {

        int waitingTime = 15;
        int shortWaitingTime = 4;

        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime currentTime;

        while (!isCompleted()) {
            if (LocalDateTime.now().isAfter(startTime.plus(waitingTime, ChronoUnit.MINUTES))) {
                throw new TimeoutException("The processing is timeout exceed");
            } else {
                System.out.println("-------Waiting processing : from " + startTime + " to " + startTime.plus(waitingTime, ChronoUnit.MINUTES) + " now is " + LocalDateTime.now());
            }
            currentTime = LocalDateTime.now();
            while (!isCompleted()) {
                Selenide.sleep(500);
                if (LocalDateTime.now().isAfter(currentTime.plus(shortWaitingTime, ChronoUnit.MINUTES))) {
                    break;
                }
            }
            Selenide.refresh();
        }
        return this;
    }

    public ConnectionConnectionSettingsMainPage backToConnectionMainPage() {
        $x("//li[@class='breadcrumb-item']//a[contains (@href, '/edit-connection-settings')]")
                .click();
        return ConnectionConnectionSettingsMainPage.init();
    }

    @Step("Go back to Dashboard")
    public MyIntelliBoardPage backToDashBoardConnectionList() {
        $x("//a[contains (@class,'success')]").click();
        return MyIntelliBoardPage.init();
    }
}
