package net.intelliboard.next.connection;

import net.intelliboard.next.IBNextAbstractTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class ConnectionIntegrationMoodleTest extends IBNextAbstractTest {
    @Test
    @DisplayName("Check Moodle connection exist")
    public void testCheckMoodleConnectionExist() {
        loginAppUI(USER_LOGIN, USER_PASS);
        open(MAIN_URL.concat("/connections/integrations"));
        $x("//a[@href='" + MAIN_URL.concat("/connections/create/1") + "' and contains (@class, \"app-button primary-outline\")]").exists();
    }
}
