package net.intelliboard.next.services.pages.connections.connection.zoom;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import net.intelliboard.next.services.IBNextURLs;
import net.intelliboard.next.services.pages.connections.ConnectionsListPage;
import net.intelliboard.next.services.pages.connections.CreateConnectionPage;
import net.intelliboard.next.services.pages.connections.connection.ConnectionProcessingFrequencyTypeEnum;

import java.io.IOException;
import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;
import static net.intelliboard.next.AbstractTest.propertiesGetValue;

public class CreateZoomConnectionPage extends CreateConnectionPage {

    private final SelenideElement zoomTokenField = $x("//input[@name='zoom_token']");
    private final SelenideElement zoomTokenSecret = $x("//input[@name='zoom_secret']");

    public static String ZOOM_TOKEN;
    public static String ZOOM_SECRET;
    public static String ZOOM_INDEPENDENT_CONNECTION_NAME = "Independent Connection";

    static {
        try {
            ZOOM_TOKEN = propertiesGetValue.getPropertyValue("zoom_token");
            ZOOM_SECRET = propertiesGetValue.getPropertyValue("zoom_secret");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static CreateZoomConnectionPage init() {
        $x("//form[contains (@action,'" + IBNextURLs.CREATE_ZOOM_CONNECTION + "')]")
                .shouldBe(Condition.visible, Duration.ofSeconds(60));
        return new CreateZoomConnectionPage();
    }

    @Step("Create Zoom connection with AutoProcessing")
    public ConnectionsListPage createZoomConnection(
            String zoomConnectionName,
            String mainConnectionName,
            String zoomToken,
            String zoomSecret,
            ConnectionProcessingFrequencyTypeEnum type,
            int time) {
        selectConnection(mainConnectionName);
        connectionNameField.setValue(zoomConnectionName);
        zoomTokenField.setValue(zoomToken);
        zoomTokenSecret.setValue(zoomSecret);
        super.selectProcessingFrequency(type);
        super.selectProcessingTime(time);
        submitForm();
        return ConnectionsListPage.init();
    }
    @Step("Create Zoom connection without AutoProcessing")
    public ConnectionsListPage createZoomConnection(
            String zoomConnectionName,
            String mainConnectionName,
            String zoomToken,
            String zoomSecret) {
        selectConnection(mainConnectionName);
        connectionNameField.setValue(zoomConnectionName);
        zoomTokenField.setValue(zoomToken);
        zoomTokenSecret.setValue(zoomSecret);
        submitForm();
        return ConnectionsListPage.init();
    }
}
