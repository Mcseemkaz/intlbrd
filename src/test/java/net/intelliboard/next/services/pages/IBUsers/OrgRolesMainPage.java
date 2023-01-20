package net.intelliboard.next.services.pages.IBUsers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selenide.$x;

public class OrgRolesMainPage {
    public static OrgRolesMainPage init() {
        $x("//main//h1[contains (text(),'Roles')]")
                .shouldBe(Condition.visible);
        return new OrgRolesMainPage();
    }

    public OrgRoleCreatePage openAddRole() {
        $x("//a[contains (@href, '/roles/create')]").click();
        return OrgRoleCreatePage.init();
    }

    public boolean isOrgRoleExist(String orgRoleName) {
        return $x("//tr[./td[.//a[@title='Settings' and contains (text(),'" + orgRoleName + "')]]]")
                .exists();
    }

    public OrgRolesMainPage deleteOrgRole (String orgRoleName) {
        $x("//tr[./td[.//a[@title='Settings' and contains (text(),'" +
                orgRoleName + "')]]]/td[contains (@class,'actions-cell')]//button").click();

        $x("//tr[./td[.//a[@title='Settings' and contains (text(),'" +
                orgRoleName + "')]]]/td[contains (@class,'actions-cell')]//a[contains (text(),'Delete')]").click();

        $x("//div[contains (@class,'modal-content')]//a[contains (@class,'app-button')]")
                .click();
        return this;
    }
}
