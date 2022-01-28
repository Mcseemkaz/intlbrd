package net.intelliboard.next;

import com.codeborne.selenide.Condition;
import net.intelliboard.next.services.pages.*;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class IBNextAbstractTest extends AbstractTest {

    public static String MAIN_URL;
    protected static String USER_LOGIN;
    protected static String USER_PASS;

    static {
        try {
            MAIN_URL = propertiesGetValue.getPropertyValue("base_url");
            USER_LOGIN = propertiesGetValue.getPropertyValue("user_login");
            USER_PASS = propertiesGetValue.getPropertyValue("user_pass");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loginAppUI(String userLogin, String userPass) {

        LoginPage loginPage = new LoginPage();

        open(MAIN_URL + "/login");
        loginPage.loginField.setValue(userLogin);
        loginPage.buttonSubmit.click();
        loginPage.passwordField.shouldBe(Condition.visible).setValue(userPass);
        loginPage.buttonSubmit.click();
        $x("//header").shouldBe(Condition.visible);
    }

    public static void connectionMoodleUI() {
        ConnectionIntegrationPage connectionIntegrationPage = new ConnectionIntegrationPage();

        open(MAIN_URL + "/connections/integrations");
        connectionIntegrationPage.linkConnect.shouldBe(Condition.visible);
        connectionIntegrationPage.linkConnect.click();
        $x("//header").shouldBe(Condition.visible);
        $x("//input[@id='lmsName']").exists();
    }

    public static void addConnectionMoodleUI() {
        CreateConnectionPage createConnectionPage = new CreateConnectionPage();
        LmsFilterSettingPage lmsFilterSettingPage = new LmsFilterSettingPage();

        String lmsNameField = "Moodle Smoke Test";
        String clientIdField = "0fcec3f8173cc06f6ed421e3e28a7d68";
        String lmsUrlField = "https://moodle.intelliboard.net";

        open(MAIN_URL + "/connections/create/1");
        createConnectionPage.lmsNameField.setValue(lmsNameField);
        createConnectionPage.clientIdField.setValue(clientIdField);
        createConnectionPage.lmsUrlField.setValue(lmsUrlField);
        createConnectionPage.buttonContinue.click();
        $x("//header").shouldBe(Condition.visible);

        lmsFilterSettingPage.buttonSave.shouldBe(Condition.visible);
        lmsFilterSettingPage.buttonSave.click();

        $x("//a[contains (text(), '" + lmsNameField + "')]").exists();

        $x("//a[contains(text(),'" + lmsNameField + "')]/ancestor-or-self::tr//button[contains (@class,'dropdown-toggle')]").click();
        $x("//div //ul /li /a[contains(text(), 'Delete')]").click();
    }
}
