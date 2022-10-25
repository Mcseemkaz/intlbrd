package net.intelliboard.next.services.pages.connections;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class AuditMainPage {

    public static AuditMainPage init(){
        $x("//div[@class='content-body' and ./div[contains (@class,'audit-progress')]]//div[@class='panel']")
                .shouldBe(Condition.visible, Duration.ofSeconds(120));
        return new AuditMainPage();
    }

    public AuditMainPage searchConnectionByName(String connectionName){
        $x("//input[contains(@class, 'search-input') and (@placeholder='Search')]")
                .setValue(connectionName)
                .sendKeys(Keys.ENTER);
        return this;
    }

    public boolean isConnectionExist(String connectionName){
        searchConnectionByName(connectionName);
        return $x("//table//td[contains (text(),'"+connectionName+"')]")
                .exists();
    }
}