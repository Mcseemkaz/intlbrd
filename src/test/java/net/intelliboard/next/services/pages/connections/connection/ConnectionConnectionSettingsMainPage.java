package net.intelliboard.next.services.pages.connections.connection;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.pages.connections.ProcessPage;

import java.time.Duration;
import java.util.NoSuchElementException;

import static com.codeborne.selenide.Selenide.$x;
import static net.intelliboard.next.AbstractTest.WAIT_TIMEOUT_LONG;

public class ConnectionConnectionSettingsMainPage extends MainConnectionPage {

    SelenideElement buttonProcessData =
            $x("//div[@class='content-header']//a[contains (@href,'/process') and contains (@class,'success')]");
    SelenideElement ellucianBlockChevronIcon = $x("//div[contains (@class,'card') and .//div[contains (text(),'Ellucian Tokens')]]/div/ion-icon");
    SelenideElement ellucianBlock = $x("//div[contains (@class,'card') and .//div[contains (text(),'Ellucian Tokens')]]");
    SelenideElement ellucianBlockDeleteButton = $x("//div[contains (@class,'card') and .//div[contains (text(),'Ellucian Tokens')]]//a[contains (@href,'/delete')]");

    public static ConnectionConnectionSettingsMainPage init() {
        $x("//form[contains (@id,'create-connection-form')]")
                .shouldBe(Condition.exist, Duration.ofSeconds(WAIT_TIMEOUT_LONG));
        return new ConnectionConnectionSettingsMainPage();
    }

    public ProcessPage processData() {
        buttonProcessData.click();
        return ProcessPage.init();
    }

    public ConnectionConnectionSettingsMainPage expandEllucianSubConnectionArea() {
        if (!isEllucianConnectionExist()) {
            throw new NoSuchElementException();
        }
        ellucianBlockChevronIcon.click();
        ellucianBlock.shouldHave(Condition.attribute("class", "card open"));
        return this;
    }

    public boolean isEllucianConnectionExist() {
        return ellucianBlock.exists();
    }

    public ConnectionConnectionSettingsMainPage deleteEllucianSubConnection() {
        ellucianBlockDeleteButton.click();
        IBNextAbstractTest.waitPage();
        return this;
    }
}
