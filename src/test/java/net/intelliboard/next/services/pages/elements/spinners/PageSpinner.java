package net.intelliboard.next.services.pages.elements.spinners;

import com.codeborne.selenide.Selenide;

import java.time.LocalDateTime;

import static com.codeborne.selenide.Selenide.$x;

public class PageSpinner {

    public static void waitSpinner() {
        while ($x("(//div[@class='loader-inner'])[2]").isDisplayed()) {
            System.err.println("---------------WAIT SPINNER------------");
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
