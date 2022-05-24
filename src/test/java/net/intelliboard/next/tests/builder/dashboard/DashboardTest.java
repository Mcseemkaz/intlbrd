package net.intelliboard.next.tests.builder.dashboard;

import com.codeborne.selenide.Condition;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.helpers.DataGenerator;
import net.intelliboard.next.services.pages.dashboard.CreateDashboardPage;
import net.intelliboard.next.services.pages.dashboard.DashboardPage;
import net.intelliboard.next.services.pages.header.HeaderObject;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;

public class DashboardTest extends IBNextAbstractTest {

    @Test
    @Tags(value = {@Tag("regression"), @Tag("normal"), @Tag("SP-T143")})
    @DisplayName("SP-T143: Create Dashboard Change Name & Description")
    public void testCreateDashboardChangeName() {
        String dashboardName = DataGenerator.getRandomString();
        String dashboardDescription = DataGenerator.getRandomString();

        loginAppUI(USER_LOGIN, USER_PASS);
        HeaderObject.init()
                .openMyIntelliBoardPage();
        HeaderObject.init()
                .openCreateDashboard()
                .changeNameAndDescription(dashboardName, dashboardDescription);
    }

    @Test
    @Tags(value = {@Tag("regression"), @Tag("normal"), @Tag("SP-T144")})
    @DisplayName("SP-T144: Create Dashboard Name Mandatory")
    public void testCreateDashboardCheckNameMandatory() {
        String dashboardDescription = DataGenerator.getRandomString();

        loginAppUI(USER_LOGIN, USER_PASS);
        HeaderObject.init()
                .openMyIntelliBoardPage();
        HeaderObject.init()
                .openCreateDashboard()
                .openSettingsModal()
                .fillInName("")
                .fillInDescription(dashboardDescription);

        CreateDashboardPage.init()
                .getSavePublishButton()
                .shouldBe(Condition.disabled);
    }

    @Test
    @Tags(value = {@Tag("regression"), @Tag("normal"), @Tag("SP-T143")})
    @DisplayName("SP-T143: Create Dashboard Description Mandatory")
    public void testCreateDashboardCheckDescriptionMandatory() {
        String dashboardName = DataGenerator.getRandomString();
        String dashboardDescription = DataGenerator.getRandomString();

        loginAppUI(USER_LOGIN, USER_PASS);
        HeaderObject.init()
                .openMyIntelliBoardPage();
        HeaderObject.init()
                .openCreateDashboard()
                .openSettingsModal()
                .fillInName(dashboardName)
                .fillInDescription("");

        CreateDashboardPage.init()
                .getSavePublishButton()
                .shouldBe(Condition.disabled);
    }

    @Disabled //TODO Need to fix drag&drop
    @Test
    @Tags(value = {@Tag("regression"), @Tag("critical"), @Tag("SP-T143")})
    @DisplayName("SP-T143: Create Dashboard")
    public void testCreateDashboard() {
        String dashboardName = DataGenerator.getRandomString();
        String dashboardDescription = DataGenerator.getRandomString();

        loginAppUI(USER_LOGIN, USER_PASS);
        HeaderObject.init()
                .openMyIntelliBoardPage();
        HeaderObject.init()
                .openCreateDashboard()
                .changeNameAndDescription(dashboardName, dashboardDescription)
                .addTextBlock()
                .saveAndPublishDashboard();

        assertThat(DashboardPage.init().getDashboardTitle().equals(dashboardName));
    }

    @Test
    @Tags(value = {@Tag("regression"), @Tag("normal"), @Tag("SP-T162")})
    @DisplayName("SP-T162: Delete Dashboard from Edit Page")
    public void testDeleteDashboard() {
        String dashboardName = DataGenerator.getRandomString();
        String dashboardDescription = DataGenerator.getRandomString();

        loginAppUI(USER_LOGIN, USER_PASS);
        HeaderObject.init()
                .openMyIntelliBoardPage();
        HeaderObject.init()
                .openCreateDashboard()
                .changeNameAndDescription(dashboardName, dashboardDescription)
                .addTextBlock()
                .saveAndPublishDashboard();

        DashboardPage dashboardPage = DashboardPage.init();

        assertThat(dashboardPage.getDashboardTitle().equals(dashboardName));

        dashboardPage
                .openDashboardforEdditing()
                .openSettingsModal()
                .deleteDashboard()
                .isDashboardPresentsByName(dashboardName);
    }
}
