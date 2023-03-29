package net.intelliboard.next.tests.core.connection;

import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.helpers.DataGenerator;
import net.intelliboard.next.services.pages.connections.CreateConnectionPage;
import net.intelliboard.next.services.pages.connections.LoginCanvasPage;
import net.intelliboard.next.services.pages.connections.connection.ConnectionFiltersActiveStateEnum;
import net.intelliboard.next.services.pages.connections.connection.ConnectionTabsEnum;
import net.intelliboard.next.services.pages.connections.connection.blackboard.ConnectionFilterSettingsBlackBoardPage;
import net.intelliboard.next.services.pages.connections.connection.blackboard.CreateBlackBoardConnectionPage;
import net.intelliboard.next.services.pages.connections.connection.canvas.ConnectionFilterSettingsCanvasPage;
import net.intelliboard.next.services.pages.connections.connection.d2l.ConnectionFilterSettingsD2LPage;
import net.intelliboard.next.services.pages.connections.connection.ilias.ConnectionFiltersSettingsIliasPage;
import net.intelliboard.next.services.pages.connections.connection.moodle.ConnectionFilterSettingsMoodlePage;
import net.intelliboard.next.services.pages.connections.connection.sakai.ConnectionFilterSettingsSakaiPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static net.intelliboard.next.services.IBNextURLs.*;

@Tag("Connection_Settings")
class ConnectionFiltersTest extends IBNextAbstractTest {

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T1345")})
    @DisplayName("SP-T1345: Editing Course activity filter on Canvas")
    void testEditingConnectionFiltersCanvas() {

        String connectionName = "AQA_SP-T1345_" + DataGenerator.getRandomString();

        open(CREATE_CANVAS_CONNECTION);
        CreateConnectionPage
                .init()
                .createCanvasConnection(
                        connectionName,
                        CreateConnectionPage.CANVAS_CLIENT_ID,
                        CreateConnectionPage.CANVAS_LMS_URL,
                        CreateConnectionPage.CANVAS_CLIENT_SECRET,
                        CreateConnectionPage.CANVAS_DATA_CLIENT_ID,
                        CreateConnectionPage.CANVAS_DATA_CLIENT_SECRET);

        LoginCanvasPage.init()
                .fillEmail(CreateConnectionPage.CANVAS_USER_LOGIN)
                .fillPassword(CreateConnectionPage.CANVAS_USER_PASS)
                .loginInCanvas()
                .confirmAuthorize()
                .saveFilterSettings()
                .editConnection(connectionName)
                .openSettingsTab(ConnectionTabsEnum.FILTERS_SETTINGS);

        ConnectionFilterSettingsCanvasPage
                .init()
                .setFilterActivityState(ConnectionFiltersActiveStateEnum.ALL_OPTIONS)
                .saveConnectionSettings()
                .deleteConnection(connectionName);
    }

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T866")})
    @DisplayName("SP-T866: Editing Course filters on Blackboard")
    void testEditingConnectionFiltersBlackBoard() {

        String connectionName = "AQA_SP-T866_" + DataGenerator.getRandomString();

        open(CREATE_BLACKBOARD_CONNECTION);

        CreateBlackBoardConnectionPage
                .init()
                .createBlackboardConnection(
                        connectionName,
                        CreateBlackBoardConnectionPage.BLACKBOARD_CLIENT_ID,
                        CreateBlackBoardConnectionPage.BLACKBOARD_LMS_URL)
                .saveFilterSettings()
                .editConnection(connectionName)
                .openSettingsTab(ConnectionTabsEnum.FILTERS_SETTINGS);

        ConnectionFilterSettingsBlackBoardPage
                .init()
                .selectCourseStatusAllCourses()
                .selectCourseFilterTermFirst()
                .selectFilterNodeFirst()
                .saveConnectionSettings()
                .deleteConnection(connectionName);
    }

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T849")})
    @DisplayName("SP-T849: Editing Course filters on Canvas")
    void testEditingConnectionFiltersCoursesCanvas() {

        String connectionName = "AQA_SP-T849_" + DataGenerator.getRandomString();

        open(CREATE_CANVAS_CONNECTION);
        CreateConnectionPage
                .init()
                .createCanvasConnection(
                        connectionName,
                        CreateConnectionPage.CANVAS_CLIENT_ID,
                        CreateConnectionPage.CANVAS_LMS_URL,
                        CreateConnectionPage.CANVAS_CLIENT_SECRET,
                        CreateConnectionPage.CANVAS_DATA_CLIENT_ID,
                        CreateConnectionPage.CANVAS_DATA_CLIENT_SECRET);

        LoginCanvasPage.init()
                .fillEmail(CreateConnectionPage.CANVAS_USER_LOGIN)
                .fillPassword(CreateConnectionPage.CANVAS_USER_PASS)
                .loginInCanvas()
                .confirmAuthorize()
                .saveFilterSettings()
                .editConnection(connectionName)
                .openSettingsTab(ConnectionTabsEnum.FILTERS_SETTINGS);

        ConnectionFilterSettingsCanvasPage
                .init()
                .selectSubAccountAll()
                .selectFilterTermAll()
                .selectDisplayCoursesAll()
                .saveConnectionSettings()
                .deleteConnection(connectionName);
    }

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T929")})
    @DisplayName("SP-T929: Editing Course filters on D2L")
    void testEditConnectionFilteringD2L() {

        open(CREATE_D2L_CONNECTION);
        String connectionName = "AQA_SP-T929_" + DataGenerator.getRandomString();
        CreateConnectionPage
                .init()
                .createD2LConnection(
                        connectionName,
                        CreateConnectionPage.D2L_URL,
                        CreateConnectionPage.D2L_CLIENT_ID,
                        CreateConnectionPage.D2L_CLIENT_SECRET,
                        CreateConnectionPage.D2L_USER_LOGIN,
                        CreateConnectionPage.D2L_USER_PASS)
                .editConnection(connectionName)
                .openSettingsTab(ConnectionTabsEnum.FILTERS_SETTINGS);

        ConnectionFilterSettingsD2LPage
                .init()
                .selectCourseStatusAllCourses()
                .selectFilterSemestrAll()
                .selectFilterOrgUnitAll()
                .saveConnectionSettings()
                .deleteConnection(connectionName);
    }

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T1386")})
    @DisplayName("SP-T1386: Editing Course filters on ilias")
    void testEditConnectionFilteringIlias() {

        open(CREATE_ILIAS_CONNECTION);
        String connectionName = "AQA_SP-T1386_" + DataGenerator.getRandomString();
        CreateConnectionPage
                .init()
                .createILIASConnection(
                        connectionName,
                        CreateConnectionPage.ILIAS_URL,
                        CreateConnectionPage.ILIAS_TOKEN,
                        CreateConnectionPage.ILIAS_KEY)
                .editConnection(connectionName)
                .openSettingsTab(ConnectionTabsEnum.FILTERS_SETTINGS);

        ConnectionFiltersSettingsIliasPage
                .init()
                .selectCourseStatusAllCourses()
                .selectSubAccountAll()
                .saveConnectionSettings()
                .deleteConnection(connectionName);
    }

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T860")})
    @DisplayName("SP-T860: Editing Course filters on Moodle")
    void testCreateMWPConnectionMoodle() {
        CreateConnectionPage createConnectionPage = new CreateConnectionPage();
        String connectionName = "AQA_SP-T860_" + DataGenerator.getRandomString();

        open(CREATE_MWP_MOODLE_CONNECTION);

        createConnectionPage.createMoodleConnection(
                        connectionName,
                        CreateConnectionPage.MWP_KEY,
                        CreateConnectionPage.MWP_URL)
                .saveFilterSettings()
                .editConnection(connectionName)
                .openSettingsTab(ConnectionTabsEnum.FILTERS_SETTINGS);

        ConnectionFilterSettingsMoodlePage
                .init()
                .selectFilterCategoryAll()
                .saveConnectionSettings()
                .deleteConnection(connectionName);
    }

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T1390")})
    @DisplayName("SP-T1390: Editing Course filters on Sakai")
    void testCreateSAKAIConnection() {

        open(CREATE_SAKAI_CONNECTION);
        String connectionName = "AQA_SP-T1390_" + DataGenerator.getRandomString();
        CreateConnectionPage
                .init()
                .createSAKAIConnection(
                        connectionName,
                        CreateConnectionPage.SAKAI_URL,
                        CreateConnectionPage.SAKAI_TOKEN
                )
                .editConnection(connectionName)
                .openSettingsTab(ConnectionTabsEnum.FILTERS_SETTINGS);

        ConnectionFilterSettingsSakaiPage
                .init()
                .selectCourseStatusAllCourses()
                .saveConnectionSettings()
                .deleteConnection(connectionName);
    }
}
