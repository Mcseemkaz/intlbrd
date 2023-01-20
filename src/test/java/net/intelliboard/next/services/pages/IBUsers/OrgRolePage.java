package net.intelliboard.next.services.pages.IBUsers;

import com.codeborne.selenide.Condition;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class OrgRolePage extends OrgRoleCreatePage {

    public static OrgRolePage init(){
        $x("//form[contains (@action, '/roles')]")
                .shouldBe(Condition.visible, Duration.ofSeconds(120));
        return new OrgRolePage();
    }
}
