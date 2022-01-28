package net.intelliboard.next.smoke;

import net.intelliboard.next.IBNextAbstractTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DemoTest extends IBNextAbstractTest {

    @Test
    @DisplayName("Test Demo SignIn")
    public void testLoginApp() {
        loginAppUI(USER_LOGIN, USER_PASS);
    }

    @Test
    @DisplayName("Test Demo ConnectionMoodle")
    public void testConnectionMoodle() {
        loginAppUI(USER_LOGIN, USER_PASS);
        connectionMoodleUI();
    }

    @Test
    @DisplayName("Test Demo Add ConnectionMoodle")
    public void testAddConnectionMoodle() {
        loginAppUI(USER_LOGIN, USER_PASS);
        addConnectionMoodleUI();
    }
}
