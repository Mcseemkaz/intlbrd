package net.intelliboard.next.tests.core.connection;

import com.codeborne.selenide.Condition;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.pages.ConnectionDataPage;
import net.intelliboard.next.services.pages.CreateConnectionPage;
import net.intelliboard.next.services.pages.LmsFilterSettingPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class CreateConnectionTest extends IBNextAbstractTest {
    @Test
    @Tags(value = {@Tag("high"), @Tag("SP-T83")})
    @DisplayName("SP-T83: Creating of Moodle connection")
    public void testCreateMoodleConntectionSPT83() {
        loginAppUI(USER_LOGIN, USER_PASS);

        CreateConnectionPage createConnectionPage = new CreateConnectionPage();
        LmsFilterSettingPage lmsFilterSettingPage = new LmsFilterSettingPage();
        ConnectionDataPage connectionDataPage = new ConnectionDataPage();

        open(MAIN_URL + "/connections/create/1");
        createConnectionPage.createMoodleConnection(MOODLE_LMS_NAME, MOODLE_CLIENT_ID, MOODLE_LMS_URL);
        $x("//header").shouldBe(Condition.visible);
        lmsFilterSettingPage.save();
        $x("//a[contains (text(), '" + MOODLE_LMS_NAME + "')]").exists();
        connectionDataPage.deleteConnection(MOODLE_LMS_NAME);
    }
}
