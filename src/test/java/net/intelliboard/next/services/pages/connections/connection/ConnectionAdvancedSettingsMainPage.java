package net.intelliboard.next.services.pages.connections.connection;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import net.intelliboard.next.services.pages.connections.ConnectionsListPage;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class ConnectionAdvancedSettingsMainPage extends MainConnectionPage {

    public static ConnectionAdvancedSettingsMainPage init() {
        ibNextAbstractTest.waitForPageLoaded();
        $x("//li[.//a[contains (text(),'" + ConnectionTabsEnum.ADVANCED_SETTINGS.value + "')]]")
                .shouldHave(Condition.cssClass("active"), Duration.ofSeconds(120));
        return new ConnectionAdvancedSettingsMainPage();
    }

    private void openDefaultGradingScheme() {
        SelenideElement defaultGradeSchemaBlock =
                $x("//div[contains(@class,'card') and .//div[contains (text(),'Default Grading Scheme')]]//div[@class='card-body']");
        if (!(defaultGradeSchemaBlock).isDisplayed()) {
            $x("//div[contains(@class,'card') and .//div[contains (text(),'Default Grading Scheme')]]//div[contains(@class,'card-header')]")
                    .click();
        }
        defaultGradeSchemaBlock.should(Condition.visible);
    }

    @Step("Change Grade Rate")
    //TODO [MO] Finished with Grades list & Move Grade to Enum
    public ConnectionAdvancedSettingsMainPage changeGradeRate(String grade, String rate) {
        openDefaultGradingScheme();

        String numberOfRow = null;

        switch (grade.toUpperCase()) {
            case ("A"): {
                numberOfRow = "0";
                break;
            }
            case ("A+"): {
                numberOfRow = "1";
                break;
            }
            case ("B+"): {
                numberOfRow = "2";
                break;
            }
        }

        $x("//input[@type='number' and contains(@name,'[" + numberOfRow + "]')]")
                .setValue(rate);
        return this;
    }

    @Step("Set Incontact")
    public ConnectionAdvancedSettingsMainPage setIncontact() {
        openIncontactBlock();
        if (!$x("//input[@id='inContact']").isSelected()) {
            $x("//div [ ./label[@for='inContact' and @class='default']]").click();
        }
        return this;
    }

    private void openIncontactBlock() {
        SelenideElement inContactBlock = $x("//div[contains(@class,'card') and .//div[contains (text(),'InContact')]]//div[@class='card-body']");
        if (!(inContactBlock).isDisplayed()) {
            $x("//div[contains(@class,'card') and .//div[contains (text(),'InContact')]]//div[contains(@class,'card-header')]")
                    .click();
        }
        inContactBlock.shouldBe(Condition.visible);
    }

    @Step("Save Connection Settings")
    @Override
    public ConnectionsListPage saveConnectionSettings() {
        saveButton.click();
        ibNextAbstractTest.waitForPageLoaded();
        Selenide.sleep(10000);
        saveButton.click();
        return ConnectionsListPage.init();
    }
}
