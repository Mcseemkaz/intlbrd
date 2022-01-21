package net.intelliboard.next.smoke;

import net.intelliboard.next.IBNextAbstractTest;
import org.junit.jupiter.api.Test;

public class DemoTest extends IBNextAbstractTest {

    @Test
    public void testLoginApp() {
        loginAppUI(USER_LOGIN, USER_PASS);
    }
}
