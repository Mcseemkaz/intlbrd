package net.intelliboard.next.services.pages.connections.connection;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.pages.connections.ProcessPage;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;
import static net.intelliboard.next.AbstractTest.WAIT_TIMEOUT_LONG;

public class ConnectionConnectionSettingsMainPage extends MainConnectionPage {

    SelenideElement buttonProcessData =
            $x("//div[@class='content-header']//a[contains (@href,'/process') and contains (@class,'success')]");
    SelenideElement ellucianBlockChevronIcon = $x("//div[contains (@class,'card') and not (contains(@class,'header')) and .//div[contains (text(),'Ellucian Tokens')]]/div/ion-icon");
    SelenideElement ellucianBlock = $x("//div[contains (@class,'card') and not(contains(@class,'header')) and .//div[contains (text(),'Ellucian Tokens')]]");
    SelenideElement ellucianBlockDeleteButton = $x("//div[contains (@class,'card') and not(contains(@class,'header')) and .//div[contains (text(),'Ellucian Tokens')]]//button[contains (@class,'error')]");

    public static ConnectionConnectionSettingsMainPage init() {
        $x("//form[contains (@id,'create-connection-form')]")
                .shouldBe(Condition.exist, Duration.ofSeconds(WAIT_TIMEOUT_LONG));
        return new ConnectionConnectionSettingsMainPage();
    }

    @Step("Execute Process Data")
    public ProcessPage processData() {
        buttonProcessData.click();
        return ProcessPage.init();
    }

    @Step("Expand Ellucian Block")
    public ConnectionConnectionSettingsMainPage expandEllucianSubConnectionArea() {
        if (!ellucianBlock.has(Condition.cssClass ("card open"))) {
            ellucianBlockChevronIcon.click();
        }
        ellucianBlock.shouldHave(Condition.attribute("class", "card open"));
        return this;
    }

    public boolean isEllucianConnectionExist() {
        return ellucianBlock.exists();
    }

    @Step("Delete Ellucian Sub Connection")
    public ConnectionConnectionSettingsMainPage deleteEllucianSubConnection() {
        expandEllucianSubConnectionArea();
        ellucianBlockDeleteButton.click();
        $x("//div[@class='modal-content']//a[contains (@class,'error')]").click();
        IBNextAbstractTest.waitPage();
        return this;
    }
}
