package net.intelliboard.next;

import com.codeborne.selenide.Condition;
import net.intelliboard.next.services.pages.LoginPage;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class IBNextAbstractTest extends AbstractTest {

    protected static String MAIN_URL;
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
}
