package net.intelliboard.next.services.database;

import com.codeborne.selenide.WebDriverRunner;
import org.apache.commons.lang3.StringUtils;

public class DBMainManagingService {

    public static String getDBIdFromConnectionSettingsUrl(){
        return StringUtils.substringBetween(WebDriverRunner.url(), "/connections/", "/edit-connection-settings");
    }
}
