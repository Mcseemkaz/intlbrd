package net.intelliboard.next.tests.core.connection;

import com.codeborne.selenide.Condition;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.helpers.DataGenerator;
import net.intelliboard.next.services.pages.CreateConnectionPage;
import net.intelliboard.next.services.pages.LmsFilterSettingPage;
import net.intelliboard.next.services.pages.ConnectionDataPage;
import net.intelliboard.next.services.pages.LoginCanvasPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;
import static net.intelliboard.next.services.IBNextURLs.CREATE_MOODLE_CONNECTION;
import static net.intelliboard.next.services.IBNextURLs.CREATE_CANVAS_CONNECTION;

public class CreateConnectionTest extends IBNextAbstractTest {
    @Test
    @Tags(value = {@Tag("high"), @Tag("SP-T83")})
    @DisplayName("SP-T83: Creating of Moodle connection")
    public void testCreateMoodleConntectionSPT83() {
        loginAppUI(USER_LOGIN, USER_PASS);

        CreateConnectionPage createConnectionPage = new CreateConnectionPage();
        LmsFilterSettingPage lmsFilterSettingPage = new LmsFilterSettingPage();
        ConnectionDataPage connectionDataPage = new ConnectionDataPage();

        String lmsMoodleName = DataGenerator.getRandomString();
        open(CREATE_MOODLE_CONNECTION);
        createConnectionPage.createMoodleConnection(lmsMoodleName, MOODLE_CLIENT_ID, MOODLE_LMS_URL);
        $x("//header").shouldBe(Condition.visible);
        lmsFilterSettingPage.saveFilterSettings();
        $x("//a[contains (text(), '" + lmsMoodleName + "')]").exists();
        connectionDataPage.deleteConnection(lmsMoodleName);
    }

    @Test
    @Tags(value = {@Tag("high"), @Tag("SP-T89")})
    @DisplayName("SP-T89: Creating of Canvas connection")
    public void testCreateCanvasConntectionSPT89() {
        loginAppUI(USER_LOGIN, USER_PASS);

        CreateConnectionPage createConnectionPage = new CreateConnectionPage();
        ConnectionDataPage connectionDataPage = new ConnectionDataPage();

        String lmsCanvasName = DataGenerator.getRandomString();

        open(CREATE_CANVAS_CONNECTION);
        createConnectionPage.createCanvasConnection(lmsCanvasName, CANVAS_CLIENT_ID, CANVAS_LMS_URL,
                CANVAS_CLIENT_SECRET, CANVAS_DATA_CLIENT_ID, CANVAS_DATA_CLIENT_SECRET);
        $x("//a[contains (text(), '" + lmsCanvasName + "')]").exists();

        LoginCanvasPage.init()
                .fillEmail(CANVAS_USER_LOGIN)
                .fillPassword(CANVAS_USER_PASS)
                .loginInCanvas()
                .confirmAuthorize()
                .saveFilterSettings();

        connectionDataPage.deleteConnection(lmsCanvasName);
    }
}
