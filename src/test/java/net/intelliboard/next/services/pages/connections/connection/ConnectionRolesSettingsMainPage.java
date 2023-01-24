package net.intelliboard.next.services.pages.connections.connection;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class ConnectionRolesSettingsMainPage extends MainConnectionPage {

    public static ConnectionRolesSettingsMainPage init() {
        ibNextAbstractTest.waitForPageLoaded();
        $x("//div[@class='content-body']//ul[contains (@class, 'user-menu')]//li[.//a[contains (text(),'"
                + ConnectionTabsEnum.ROLES_SETTINGS.value + "')]]")
                .shouldHave(Condition.cssClass("active"), Duration.ofSeconds(120));
        return new ConnectionRolesSettingsMainPage();
    }

    public ConnectionRolesSettingsMainPage setRoles(ConnectionRolesMainEnum connectionRolesMainEnum, ConnectionRolesBlockEnum rolesBlockEnum) {

        expandRolesBlock(rolesBlockEnum);
        if (connectionRolesMainEnum.value.equals(ConnectionRolesMainEnum.ALL.value)) {
            $$x("//div[contains (@class, 'card') and (not(contains (@class, 'card-header')))][.//div[contains (text(),'" +
                    rolesBlockEnum.value + "')]]//div[@class='card-body']//input[@name='"+rolesBlockEnum.xpath_input+"']")
                    .forEach(SelenideElement::click);
        } else {
            $x("//div[contains (@class, 'card') and (not(contains (@class, 'card-header')))][.//div[contains (text(),'" +
                    rolesBlockEnum.value + "')]]//div[@class='card-body']//input[ following-sibling::label[contains (text(),'" +
                    connectionRolesMainEnum.value + "')]]").click();
        }
        return this;
    }

    private void expandRolesBlock(ConnectionRolesBlockEnum connectionRolesBlockEnum) {
        SelenideElement rolesBlock = $x("//div[contains (@class, 'card') and (not(contains (@class, 'card-header')))][.//div[contains (text(),'"
                + connectionRolesBlockEnum.value + "')]]");

        if (!rolesBlock.has(Condition.cssClass("open"))) {
            rolesBlock.click();
            rolesBlock.shouldHave(Condition.cssClass("open"));
        }
    }
}
