package net.intelliboard.next.tests.core.login;

import io.qameta.allure.Feature;
import io.qameta.allure.Flaky;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.pages.header.HeaderObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

@Feature("Login")
@Tag("Login")
public class LogoutTest extends IBNextAbstractTest {
    @Flaky
    @Test
    @DisplayName("SP-TXXX: Logout from the app")
    @Tags(value = {@Tag("smoke"), @Tag("critical"), @Tag("SP-TXXX"), @Tag("health")})
    public void testLogout() {
        HeaderObject
                .init()
                .openDropDownMenu()
                .logOut();
    }
}
