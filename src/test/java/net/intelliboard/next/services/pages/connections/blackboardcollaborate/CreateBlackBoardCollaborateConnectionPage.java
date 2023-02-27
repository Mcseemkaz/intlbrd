package net.intelliboard.next.services.pages.connections.blackboardcollaborate;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import net.intelliboard.next.services.IBNextURLs;
import net.intelliboard.next.services.pages.connections.ConnectionsListPage;
import net.intelliboard.next.services.pages.connections.CreateConnectionPage;
import net.intelliboard.next.services.pages.connections.connection.ConnectionProcessingFrequencyTypeEnum;

import java.io.IOException;
import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;
import static net.intelliboard.next.AbstractTest.propertiesGetValue;

public class CreateBlackBoardCollaborateConnectionPage extends CreateConnectionPage {

    private final SelenideElement blackBoardCollaborateAPIKey = $x("//input[@id='bb_collaborate_api_key']");
    private final SelenideElement blackBoardCollaborateSecret = $x("//input[@id='bb_collaborate_secret']");
    private final SelenideElement blackBoardCollaborateEndpoint = $x("//input[@id='bb_collaborate_api_endpoint']");

    public static String BLACK_BOARD_COLLABORATE_API_KEY;
    public static String BLACK_BOARD_COLLABORATE_SECRET;
    public static String BLACK_BOARD_COLLABORATE_URL;
    public static String BLACK_BOARD_COLLABORATE_INDEPENDENT_CONNECTION_NAME = "Independent Connection";

    static {
        try {
            BLACK_BOARD_COLLABORATE_API_KEY = propertiesGetValue.getPropertyValue("blackboard_collaborate_api_key");
            BLACK_BOARD_COLLABORATE_SECRET = propertiesGetValue.getPropertyValue("blackboard_collaborate_secret");
            BLACK_BOARD_COLLABORATE_URL = propertiesGetValue.getPropertyValue("blackboard_collaborate_url");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static CreateBlackBoardCollaborateConnectionPage init() {
        $x("//form[contains (@action,'" + IBNextURLs.CREATE_BLACKBOARD_COLLABORATE_CONNECTION + "')]")
                .shouldBe(Condition.visible, Duration.ofSeconds(60));
        return new CreateBlackBoardCollaborateConnectionPage();
    }

    public ConnectionsListPage createBBCollaborateConnection(String connectionName,
                                                             String mainConnectionName,
                                                             String blackBoardCollaborateApiKey,
                                                             String blackBoardCollaborateSecretValue,
                                                             String blackBoardCollaborateURL,
                                                             ConnectionProcessingFrequencyTypeEnum frequencyTypeEnum,
                                                             int processingTime) {
        selectConnection(mainConnectionName);
        if (mainConnectionName.contains("Independent Connection")) {
            connectionNameField.setValue(connectionName);
        }
        blackBoardCollaborateAPIKey.setValue(blackBoardCollaborateApiKey);
        blackBoardCollaborateSecret.setValue(blackBoardCollaborateSecretValue);
        blackBoardCollaborateEndpoint.setValue(blackBoardCollaborateURL);
        selectProcessingFrequency(frequencyTypeEnum);
        selectProcessingTime(processingTime);
        submitForm();
        return ConnectionsListPage.init();
    }

    public ConnectionsListPage createBBCollaborateConnection(String connectionName,
                                                             String mainConnectionName,
                                                             String blackBoardCollaborateApiKey,
                                                             String blackBoardCollaborateSecretValue,
                                                             String blackBoardCollaborateURL
    ) {
        selectConnection(mainConnectionName);
        if (mainConnectionName.contains("Independent Connection")) {
            connectionNameField.setValue(connectionName);
        }
        blackBoardCollaborateAPIKey.setValue(blackBoardCollaborateApiKey);
        blackBoardCollaborateSecret.setValue(blackBoardCollaborateSecretValue);
        blackBoardCollaborateEndpoint.setValue(blackBoardCollaborateURL);
        submitForm();
        return ConnectionsListPage.init();
    }
}
