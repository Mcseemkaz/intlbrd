package net.intelliboard.next.tests.core.connection;

import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.helpers.DataGenerator;
import net.intelliboard.next.services.pages.connections.CreateConnectionPage;
import net.intelliboard.next.services.pages.connections.LoginCanvasPage;
import net.intelliboard.next.services.pages.connections.connection.ConnectionAdvancedSettingsMainPage;
import net.intelliboard.next.services.pages.connections.connection.ConnectionTabsEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static net.intelliboard.next.services.IBNextURLs.*;

@Tag("Connection_Settings")
public class ConnectionAdvanceSettingsTest extends IBNextAbstractTest {

    private String grade = "A";
    private String rate = "70";


    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T851")})
    @DisplayName("SP-T851: Editing Default Grading Scheme in filters on Canvas")
    public void testEditDefaultGradingSchemaCanvas() {

        String connectionName = "AQA_SP-T851_" + DataGenerator.getRandomString();

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
                .openSettingsTab(ConnectionTabsEnum.ADVANCED_SETTINGS);

        ConnectionAdvancedSettingsMainPage
                .init()
                .changeGradeRate(grade, rate)
                .saveConnectionSettings()
                .deleteConnection(connectionName);
    }

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T862")})
    @DisplayName("SP-T862: Editing Default Grading Scheme in filters on Moodle")
    public void testEditDefaultGradingSchemaMoodle() {

        String connectionName = "AQA_SP-T862_" + DataGenerator.getRandomString();
        open(CREATE_MOODLE_CONNECTION);
        CreateConnectionPage
                .init()
                .createMoodleConnection(
                        connectionName,
                        CreateConnectionPage.MOODLE_CLIENT_ID,
                        CreateConnectionPage.MOODLE_LMS_URL)
                .saveFilterSettings()
                .editConnection(connectionName)
                .openSettingsTab(ConnectionTabsEnum.ADVANCED_SETTINGS);

        ConnectionAdvancedSettingsMainPage
                .init()
                .changeGradeRate(grade, rate)
                .saveConnectionSettings()
                .deleteConnection(connectionName);
    }

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T1382")})
    @DisplayName("SP-T1382: Editing Default Grading Scheme in filters on D2L")
    public void testEditDefaultGradingSchemaD2L() {
        open(CREATE_D2L_CONNECTION);
        String connectionName = "AQA_SP-T1382_" + DataGenerator.getRandomString();
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
                .openSettingsTab(ConnectionTabsEnum.ADVANCED_SETTINGS);

        ConnectionAdvancedSettingsMainPage
                .init()
                .changeGradeRate(grade, rate)
                .saveConnectionSettings()
                .deleteConnection(connectionName);
    }

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T868")})
    @DisplayName("SP-T868: Editing Default Grading Scheme in filters on Blackboard")
    public void testEditDefaultGradingSchemaBlackboard() {

        String connectionName = "AQA_SP-T868_" + DataGenerator.getRandomString();
        open(CREATE_BLACKBOARD_CONNECTION);

        CreateConnectionPage
                .init()
                .createBlackboardConnection(
                        connectionName,
                        CreateConnectionPage.BLACKBOARD_CLIENT_ID,
                        CreateConnectionPage.BLACKBOARD_LMS_URL)
                .saveFilterSettings()
                .editConnection(connectionName)
                .openSettingsTab(ConnectionTabsEnum.ADVANCED_SETTINGS);

        ConnectionAdvancedSettingsMainPage
                .init()
                .changeGradeRate(grade, rate)
                .saveConnectionSettings()
                .deleteConnection(connectionName);
    }

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T1388")})
    @DisplayName("SP-T1388: Editing Default Grading Scheme in filters on ilias")
    public void testEditDefaultGradingSchemaIlias() {

        String connectionName = "AQA_SP-T1388_" + DataGenerator.getRandomString();
        open(CREATE_ILIAS_CONNECTION);

        CreateConnectionPage
                .init()
                .createILIASConnection(
                        connectionName,
                        CreateConnectionPage.ILIAS_URL,
                        CreateConnectionPage.ILIAS_TOKEN,
                        CreateConnectionPage.ILIAS_KEY)
                .editConnection(connectionName)
                .openSettingsTab(ConnectionTabsEnum.ADVANCED_SETTINGS);

        ConnectionAdvancedSettingsMainPage
                .init()
                .changeGradeRate(grade, rate)
                .saveConnectionSettings()
                .deleteConnection(connectionName);
    }

    @Test
    @Tags(value = {@Tag("normal"), @Tag("SP-T1393")})
    @DisplayName("SP-T1393: Editing Default Grading Scheme in filters on Sakai")
    public void testEditDefaultGradingSchemaSakai() {

        String connectionName = "AQA_SP-T1393_" + DataGenerator.getRandomString();
        open(CREATE_SAKAI_CONNECTION);

        CreateConnectionPage
                .init()
                .createSAKAIConnection(
                        connectionName,
                        CreateConnectionPage.SAKAI_URL,
                        CreateConnectionPage.SAKAI_TOKEN,
                        CreateConnectionPage.SAKAI_KEY)
                .editConnection(connectionName)
                .openSettingsTab(ConnectionTabsEnum.ADVANCED_SETTINGS);

        ConnectionAdvancedSettingsMainPage
                .init()
                .changeGradeRate(grade, rate)
                .saveConnectionSettings()
                .deleteConnection(connectionName);
    }
}
