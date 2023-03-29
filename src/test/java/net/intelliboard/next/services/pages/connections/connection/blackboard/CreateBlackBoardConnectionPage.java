package net.intelliboard.next.services.pages.connections.connection.blackboard;

import io.qameta.allure.Step;
import net.intelliboard.next.services.pages.connections.CreateConnectionPage;
import net.intelliboard.next.services.pages.connections.LmsFilterSettingPage;

import java.io.IOException;

import static net.intelliboard.next.AbstractTest.propertiesGetValue;

public class CreateBlackBoardConnectionPage extends CreateConnectionPage {

    public static String BLACKBOARD_CLIENT_ID;
    public static String BLACKBOARD_LMS_URL;
    public static String BLACKBOARD_ULTRA_CLIENT_ID;
    public static String BLACKBOARD_ULTRA_LMS_URL;

    static {
        try {
            CreateBlackBoardConnectionPage.BLACKBOARD_LMS_URL = propertiesGetValue.getPropertyValue("blackboard_learn2_url");
            CreateBlackBoardConnectionPage.BLACKBOARD_ULTRA_LMS_URL = propertiesGetValue.getPropertyValue("blackboard_ultra_url");
            CreateBlackBoardConnectionPage.BLACKBOARD_CLIENT_ID = propertiesGetValue.getPropertyValue("blackboard_learn2_client_id");
            CreateBlackBoardConnectionPage.BLACKBOARD_ULTRA_CLIENT_ID = propertiesGetValue.getPropertyValue("blackboard_ultra_client_id");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Step("Create BB Connection Page init")
    public static CreateBlackBoardConnectionPage init() {
        CreateConnectionPage.init();
        return new CreateBlackBoardConnectionPage();
    }

    @Step("Create BlackBoard Connection")
    public LmsFilterSettingPage createBlackboardConnection(String lmsName, String clientId, String lmsUrl) {
        connectionNameField.setValue(lmsName);
        clientIdField.setValue(clientId);
        lmsUrlField.setValue(lmsUrl);
        submitForm();
        return LmsFilterSettingPage.init();
    }
}
