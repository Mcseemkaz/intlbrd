package net.intelliboard.next.connection;

import com.codeborne.selenide.Condition;
import net.intelliboard.next.IBNextAbstractTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class AddConnectionMoodleTest extends IBNextAbstractTest {
    @Test
    @DisplayName("Check LMS Name field is visible")
    public void testCheckLmsNameIsVisible() {
        loginAppUI(USER_LOGIN, USER_PASS);
        open(MAIN_URL.concat("/connections/create/1"));
        $x("//input[@id='lmsName']").shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("Check Token field is visible")
    public void testCheckTokenIsVisible() {
        loginAppUI(USER_LOGIN, USER_PASS);
        open(MAIN_URL.concat("/connections/create/1"));
        $x("//input[@id='clientId']").shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("Check URL field is visible")
    public void testCheckURLIsVisible() {
        loginAppUI(USER_LOGIN, USER_PASS);
        open(MAIN_URL.concat("/connections/create/1"));
        $x("//input[@id='lmsUrl']").shouldBe(Condition.visible);
    }
}
