package net.intelliboard.next.services.pages.connections;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class EditConnectionPage {

    SelenideElement buttonProcessData =
            $x("//div[@class='content-header']//a[contains (@href,'/process') and contains (@class,'success')]");

    public static EditConnectionPage init() {
        $x("//form[contains (@id,'create-connection-form')]")
                .shouldBe(Condition.exist, Duration.ofSeconds(60));
        return new EditConnectionPage();
    }

    public ProcessPage processData(){
        buttonProcessData.click();
        return ProcessPage.init();
    }
}
