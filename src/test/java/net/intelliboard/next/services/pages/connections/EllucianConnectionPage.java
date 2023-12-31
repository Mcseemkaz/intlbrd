package net.intelliboard.next.services.pages.connections;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import net.intelliboard.next.services.pages.connections.connection.ConnectionProcessingFrequencyTypeEnum;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class EllucianConnectionPage extends CreateConnectionPage {

    public static EllucianConnectionPage init() {
        $x("//form[contains(@action,'/11') or contains (@action, '/12')]")
                .shouldBe(Condition.visible, Duration.ofSeconds(60));
        return new EllucianConnectionPage();
    }

    @Step("Fill in Ellucian Token")
    public EllucianConnectionPage fillInEllucianToken(String token) {
        $x("//input[@id='ellution_token_id' or @id='ellution_token_secret' or @id='ellucian_banner_token_secret' or @id='ellucian_colleague_token_id']").setValue(token);
        return this;
    }

    @Step("Ellucian Select connection")
    public EllucianConnectionPage selectConnection(String connectionName) {
        super.selectConnection(connectionName);
        return this;
    }

    @Step("Ellucian Processing Frequency")
    public EllucianConnectionPage selectProcessingFrequency(ConnectionProcessingFrequencyTypeEnum type) {
        super.selectProcessingFrequency(type);
        return this;
    }

    @Step("Ellucian Processing Time")
    public EllucianConnectionPage selectProcessingTime(int time) {
        super.selectProcessingTime(time);
        return this;
    }
}
