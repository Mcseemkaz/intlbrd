package net.intelliboard.next.services.pages.connections;

import com.codeborne.selenide.Condition;
import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class EllucianConnectionPage extends CreateConnectionPage {

    public static EllucianConnectionPage init() {
        $x("//form[contains(@action,'/11') or contains (@action, '/12')]")
                .shouldBe(Condition.visible, Duration.ofSeconds(60));
        return new EllucianConnectionPage();
    }

    public EllucianConnectionPage fillInEllucianToken(String token) {
        $x("//input[@id='ellution_token_id' or @id='ellution_token_secret']").setValue(token);
        return this;
    }

    public EllucianConnectionPage selectConnection(String connectionName) {
        $x("//div[contains(@class, 'intelli-dropdown')]//button[@class='tree-choice']")
                .click();
        $x("//div[contains(@class, 'tree-drop')]//li[.//strong[text()='" + connectionName + "']]")
                .click();
        $x("//div[contains(@class, 'intelli-dropdown')]//button[@class='tree-choice']/span")
                .shouldBe(Condition.visible, Duration.ofSeconds(5))
                .shouldHave(Condition.text(connectionName));
        return this;
    }

}
