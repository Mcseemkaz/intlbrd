package net.intelliboard.next.tests.core.connection;

import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.helpers.DataGenerator;
import net.intelliboard.next.services.pages.connections.CreateConnectionPage;
import net.intelliboard.next.services.pages.connections.LoginCanvasPage;
import net.intelliboard.next.services.pages.connections.connection.ConnectionFiltersActiveStateEnum;
import net.intelliboard.next.services.pages.connections.connection.ConnectionTabsEnum;
import net.intelliboard.next.services.pages.connections.connection.blackboard.ConnectionFilterSettingsBlackBoardPage;
import net.intelliboard.next.services.pages.connections.connection.canvas.ConnectionFilterSettingsCanvasPage;
import net.intelliboard.next.services.pages.connections.connection.d2l.ConnectionFilterSettingsD2LPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static net.intelliboard.next.services.IBNextURLs.*;

public class ConnectionFiltersTest extends IBNextAbstractTest {

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T1345")})
    @DisplayName("SP-T1345: Editing Course activity filter on Canvas")
    public void testEditingConnectionFiltersCanvas() {

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
    public void testEditingConnectionFiltersBlackBoard() {

        String connectionName = "AQA_SP-T866_" + DataGenerator.getRandomString();

        open(CREATE_BLACKBOARD_CONNECTION);

        CreateConnectionPage
                .init()
                .createBlackboardConnection(
                        connectionName,
                        CreateConnectionPage.BLACKBOARD_CLIENT_ID,
                        CreateConnectionPage.BLACKBOARD_LMS_URL)
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
    public void testEditingConnectionFiltersCoursesCanvas() {

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
    public void testCreateD2LConnection() {

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
}
