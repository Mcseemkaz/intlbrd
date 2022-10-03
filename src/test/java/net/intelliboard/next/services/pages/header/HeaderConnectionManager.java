package net.intelliboard.next.services.pages.header;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import net.intelliboard.next.IBNextAbstractTest;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class HeaderConnectionManager {

    public static HeaderConnectionManager expandOpenConnectionManager(){
      $x("//div[contains(@class, 'connection-selector')]").click();
      $x("//div[contains(@class, 'dropdown connections-folder')]")
              .shouldBe(Condition.visible);
      return new HeaderConnectionManager();
    }

    public HeaderConnectionManager selectConnection(String connectionName){
        Selenide.sleep(1000);
        $x("//div[contains(@class, 'connection-name') and contains (text(),'"+connectionName+"')]").click();
        IBNextAbstractTest ibNextAbstractTest = new IBNextAbstractTest();
        ibNextAbstractTest.waitForPageLoaded();
        $x("//span[contains (@class, 'selected-connection')]//strong")
                .shouldHave(Condition.text(connectionName), Duration.ofSeconds(30));
        return this;
    }
}
