package net.intelliboard.next.tests.core.connection;

import com.codeborne.selenide.Condition;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.helpers.DataGenerator;
import net.intelliboard.next.services.pages.CreateConnectionPage;
import net.intelliboard.next.services.pages.LmsFilterSettingPage;
import net.intelliboard.next.services.pages.ConnectionsListPage;
import net.intelliboard.next.services.pages.LoginCanvasPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;
import static net.intelliboard.next.services.IBNextURLs.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CreateConnectionTest extends IBNextAbstractTest {
    @Test
    @Tags(value = {@Tag("high"), @Tag("SP-T83")})
    @DisplayName("SP-T83: Creating of Moodle connection")
    public void testCreateMoodleConntectionSPT83() {
        loginAppUI(USER_LOGIN, USER_PASS);

        CreateConnectionPage createConnectionPage = new CreateConnectionPage();
        LmsFilterSettingPage lmsFilterSettingPage = new LmsFilterSettingPage();
        ConnectionsListPage connectionsListPage = new ConnectionsListPage();

        String lmsMoodleName = DataGenerator.getRandomString();
        open(CREATE_MOODLE_CONNECTION);
        createConnectionPage.createMoodleConnection(lmsMoodleName, CreateConnectionPage.MOODLE_CLIENT_ID, CreateConnectionPage.MOODLE_LMS_URL);
        $x("//header").shouldBe(Condition.visible);
        lmsFilterSettingPage.saveFilterSettings();
        $x("//a[contains (text(), '" + lmsMoodleName + "')]").exists();
        connectionsListPage.deleteConnection(lmsMoodleName);
    }

    @Test
    @Tags(value = {@Tag("high"), @Tag("SP-T89")})
    @DisplayName("SP-T89: Creating of Canvas connection")
    public void testCreateCanvasConntectionSPT89() {
        loginAppUI(USER_LOGIN, USER_PASS);

        CreateConnectionPage createConnectionPage = new CreateConnectionPage();
        ConnectionsListPage connectionsListPage = new ConnectionsListPage();

        String lmsCanvasName = DataGenerator.getRandomString();

        open(CREATE_CANVAS_CONNECTION);
        createConnectionPage.createCanvasConnection(lmsCanvasName, CreateConnectionPage.CANVAS_CLIENT_ID, CreateConnectionPage.CANVAS_LMS_URL,
                CreateConnectionPage.CANVAS_CLIENT_SECRET, CreateConnectionPage.CANVAS_DATA_CLIENT_ID, CreateConnectionPage.CANVAS_DATA_CLIENT_SECRET);
        $x("//a[contains (text(), '" + lmsCanvasName + "')]").exists();

        LoginCanvasPage.init()
                .fillEmail(CreateConnectionPage.CANVAS_USER_LOGIN)
                .fillPassword(CreateConnectionPage.CANVAS_USER_PASS)
                .loginInCanvas()
                .confirmAuthorize()
                .saveFilterSettings();

        connectionsListPage.deleteConnection(lmsCanvasName);
    }

    @Test
    @Tags(value = {@Tag("high"), @Tag("SP-T599")})
    @DisplayName("SP-T599: Creating a Classroom connection")
    public void testCreateBlackboardConnection() {
        loginAppUI(USER_LOGIN, USER_PASS);

        CreateConnectionPage createConnectionPage = new CreateConnectionPage();
        ConnectionsListPage connectionsListPage = new ConnectionsListPage();
        LmsFilterSettingPage lmsFilterSettingPage = new LmsFilterSettingPage();

        String lmsBlackboardName = "CLASSROOM_" + DataGenerator.getRandomString();

        open(CREATE_BLACKBOARD_CONNECTION);

        createConnectionPage.createBlackboardConnection(lmsBlackboardName, CreateConnectionPage.BLACKBOARD_CLIENT_ID, CreateConnectionPage.BLACKBOARD_LMS_URL);
        $x("//header").shouldBe(Condition.visible);
        lmsFilterSettingPage.saveFilterSettings();
        $x("//a[contains (text(), '" + lmsBlackboardName + "')]").exists();

        connectionsListPage.deleteConnection(lmsBlackboardName);
    }

    @Test
    @Tags(value = {@Tag("high"), @Tag("SP-T106")})
    @DisplayName("SP-T106: Creating of Zoom independent connection")
    public void testCreateZoomConnection() {

        loginAppUI(USER_LOGIN, USER_PASS);
        open(CREATE_ZOOM_CONNECTION);
        String lmsZoomName = "Zoom_" + DataGenerator.getRandomString();
        CreateConnectionPage.init()
                .createZoomConnection(lmsZoomName, CreateConnectionPage.ZOOM_TOKEN, CreateConnectionPage.ZOOM_SECRET);

        ConnectionsListPage connectionsListPage = ConnectionsListPage.init();

        connectionsListPage.findConnectionByName(lmsZoomName);

        assertThat(connectionsListPage.isConnectionExist(lmsZoomName))
                .isTrue()
                .as(String.format("Connection : %s is not existed", lmsZoomName));

        connectionsListPage
                .deleteConnection(lmsZoomName);
    }
}
