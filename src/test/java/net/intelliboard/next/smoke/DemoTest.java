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
}
