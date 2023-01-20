package net.intelliboard.next.services.pages.IBUsers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import net.intelliboard.next.services.pages.elements.DropdownElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class OrgRoleCreatePage {
    public static OrgRoleCreatePage init() {
        $x("//form[contains (@action, '/roles')]")
                .shouldBe(Condition.visible, Duration.ofSeconds(120));
        return new OrgRoleCreatePage();
    }

    public OrgRoleCreatePage fillInOrgRoleName(String orgRoleName) {
        $x("//input[@id='roleName']").setValue(orgRoleName);
        return this;
    }

    public OrgRoleCreatePage setStatus(OrgRoleStatusEnum statusEnum) {
        DropdownElement
                .init("Status", 1)
                .selectOption(statusEnum.value);
        return this;
    }

    public OrgRoleCreatePage selectConnection(String connectionName) {
        SelenideElement connection =
                $x("//input[contains (@id, 'connections') and following-sibling::label[contains (text(),'" + connectionName + "')]]");
        if (!connection.isSelected()) {
            connection.click();
        }
        return this;
    }

    public OrgRolesMainPage submitUserCreateForm() {
        Selenide.sleep(5000);
        $x("//button[@type='submit']")
                .click();
        return OrgRolesMainPage.init();
    }
}
