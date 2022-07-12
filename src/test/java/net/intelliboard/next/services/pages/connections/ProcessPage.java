package net.intelliboard.next.services.pages.connections;

import com.codeborne.selenide.Condition;
import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class ProcessPage {

    public static ProcessPage init(){
        $x("//div[@class='progress-tracker']")
                .shouldBe(Condition.visible, Duration.ofSeconds(100));
        return new ProcessPage();
    }

    public boolean isProcess100Percent(){
        return $x("//div[@class='progress-tracker']//h4")
                .getText()
                .contains("100%");
    }

    public boolean isCompleted(){
        return $x("(//div[@class='stat-item'])[1]/strong")
                .getText()
                .contains("Completed");
    }

    public ProcessPage waitingProcessingComplete() throws InterruptedException {
        while(!(isCompleted())){
            Thread.sleep(200);
        }
        return this;
    }
}