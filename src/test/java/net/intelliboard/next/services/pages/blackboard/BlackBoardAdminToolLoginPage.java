package net.intelliboard.next.services.pages.blackboard;


import com.codeborne.selenide.Condition;

import java.io.IOException;
import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class BlackBoardAdminToolLoginPage {

    public static BlackBoardAdminToolLoginPage init() {
        $x("//div[@id='loginPageContainer']").should(Condition.visible, Duration.ofSeconds(30));

        if ($x("//div[@id='containerdiv']").exists()) {
            $x("//button[@id='agree_button']").click();
            $x("//div[@id='containerdiv']")
                    .shouldNot(Condition.visible, Duration.ofSeconds(30));
        }
        return new BlackBoardAdminToolLoginPage();
    }

    public BlackBoardAdminToolLoginPage setUsername(String username) {
        $x("//input[@id='user_id']").setValue(username);
        return this;
    }

    public BlackBoardAdminToolLoginPage setPassword(String password) {
        $x("//input[@id='password']").setValue(password);
        return this;
    }

    public BlackBoardAdminToolMainPage submitForm() throws IOException {
        $x("//input[@id='entry-login']").pressEnter();
        return BlackBoardAdminToolMainPage.init();
    }
}
