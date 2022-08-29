package net.intelliboard.next.services.pages.blackboard;

import net.intelliboard.next.services.IBNextURLs;
import net.intelliboard.next.services.PropertiesGetValue;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.open;

public class BlackBoardMigrationService {

    PropertiesGetValue propertiesGetValue = new PropertiesGetValue();
    private String BBmainUrl = IBNextURLs.BLACK_BOARD_MAIN_URL;
    private String BBUserName = propertiesGetValue.getPropertyValue("blackboard_learn2_username");
    private String BBUserPassword = propertiesGetValue.getPropertyValue("blackboard_learn2_password");

    public BlackBoardMigrationService() throws IOException {
    }

    public void performMigrationProcess() throws IOException {
        open(BBmainUrl);
        BlackBoardAdminToolLoginPage
                .init()
                .setUsername(BBUserName)
                .setPassword(BBUserPassword)
                .submitForm()
                .selectBlackBoardMenuItem(BlackBoardSideMenuOptionsEnum.ADMIN);

        BlackBoardAdminToolPage
                .init()
                .openBuildingBlocks()
                .selectInstalledTools()
                .openSettings()
                .performDump();
    }
}
