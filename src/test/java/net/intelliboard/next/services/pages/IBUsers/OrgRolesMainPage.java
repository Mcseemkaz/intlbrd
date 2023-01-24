package net.intelliboard.next.services.pages.IBUsers;

import com.codeborne.selenide.Condition;
import net.intelliboard.next.IBNextAbstractTest;

import static com.codeborne.selenide.Selenide.$x;

public class OrgRolesMainPage {
    static IBNextAbstractTest ibNextAbstractTest = new IBNextAbstractTest();

    public static OrgRolesMainPage init() {
        ibNextAbstractTest.waitForPageLoaded();
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

    public OrgRolesMainPage deleteOrgRole(String orgRoleName) {
        openActionMenu(orgRoleName);

        $x("//tr[./td[.//a[@title='Settings' and contains (text(),'" +
                orgRoleName + "')]]]/td[contains (@class,'actions-cell')]//a[contains (text(),'Delete')]").click();

        $x("//div[contains (@class,'modal-content')]//a[contains (@class,'app-button')]")
                .click();
        return this;
    }

    private static void openActionMenu(String orgRoleName) {
        $x("//tr[./td[.//a[@title='Settings' and contains (text(),'" +
                orgRoleName + "')]]]/td[contains (@class,'actions-cell')]//button")
                .click();
    }

    public OrgRolePage editOrgRole(String orgRoleName) {
        openActionMenu(orgRoleName);
        $x("//tr[./td[.//a[@title='Settings' and contains (text(),'" +
                orgRoleName + "')]]]/td[contains (@class,'actions-cell')]//a[contains (text(),'Edit')]").click();
        return OrgRolePage.init();
    }
}