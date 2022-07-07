package net.intelliboard.next.services.pages.connections;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import net.intelliboard.next.IBNextAbstractTest;

import java.time.Duration;
import java.util.NoSuchElementException;

import static com.codeborne.selenide.Selenide.$x;
import static net.intelliboard.next.AbstractTest.WAIT_TIMEOUT_LONG;

public class EditConnectionPage {

    SelenideElement buttonProcessData =
            $x("//div[@class='content-header']//a[contains (@href,'/process') and contains (@class,'success')]");
    SelenideElement ellucianBlockChevronIcon = $x("//div[contains (@class,'card') and .//div[contains (text(),'Ellucian Tokens')]]/div/ion-icon");
    SelenideElement ellucianBlock = $x("//div[contains (@class,'card') and .//div[contains (text(),'Ellucian Tokens')]]");
    SelenideElement ellucianBlockDeleteButton = $x("//div[contains (@class,'card') and .//div[contains (text(),'Ellucian Tokens')]]//a[contains (@href,'/delete')]");

    public static EditConnectionPage init() {
        $x("//form[contains (@id,'create-connection-form')]")
                .shouldBe(Condition.exist, Duration.ofSeconds(WAIT_TIMEOUT_LONG));
        return new EditConnectionPage();
    }

    public ProcessPage processData() {
        buttonProcessData.click();
        return ProcessPage.init();
    }

    public EditConnectionPage expandEllucianSubConnectionArea() {
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

    public EditConnectionPage deleteEllucianSubConnection() {
        ellucianBlockDeleteButton.click();
        IBNextAbstractTest.waitPage();
        return this;
    }
}
