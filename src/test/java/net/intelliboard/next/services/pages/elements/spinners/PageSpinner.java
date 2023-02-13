package net.intelliboard.next.services.pages.elements.spinners;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import java.time.LocalDateTime;

import static com.codeborne.selenide.Selenide.$x;

public class PageSpinner {

    @Getter
    static SelenideElement spinner = $x("//div[@class='loader-inner']");
//    static SelenideElement spinner = $x("//div[@class='loader-container']");

    public static void waitSpinner() {
        while ($x("(//div[@class='loader-inner'])[2]").isDisplayed()) {
            Selenide.sleep(500);
        }
    }

    public static void waitPreloader() {

        LocalDateTime startWaitTime = LocalDateTime.now();

        while (
                !$x("//div[@id='preloader'][contains (@class, 'loaded')]").exists()
        ) {
            Selenide.sleep(500);
            System.err.println("----------WAIT FOR PRELOADER--------------");

            if (LocalDateTime.now().isAfter(startWaitTime.plusSeconds(200))) {
                System.err.println("----------- BREAK WAITER AFTER 300 seconds------------------");
                break;
            }
        }
    }
}
