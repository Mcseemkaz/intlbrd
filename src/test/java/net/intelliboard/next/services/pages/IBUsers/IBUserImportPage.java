package net.intelliboard.next.services.pages.IBUsers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.IBNextURLs;
import net.intelliboard.next.services.ProjectFilesEnum;
import net.intelliboard.next.services.pages.connections.ConnectionsTypeEnum;

import java.io.File;

import static com.codeborne.selenide.Selenide.$x;

public class IBUserImportPage {

    public static IBUserImportPage init() {
        IBNextAbstractTest ibNextAbstractTest = new IBNextAbstractTest();
        ibNextAbstractTest.waitForPageLoaded();
        $x("//div[@class='content-body']//form[contains (@action,'/import')]").shouldBe(Condition.visible);
        ibNextAbstractTest.checkPageURL(IBNextURLs.USERS_IMPORT_PAGE);
        return new IBUserImportPage();
    }

    /**
     * @param connection that will be selected
     * @return IBUserImportPage.class
     */
    @Step("Select LMS")
    public IBUserImportPage selectLMS(ConnectionsTypeEnum connection) {

        SelenideElement lmsDropdownDown =
                $x("//div[@name='lms_type']//ion-icon[@name='chevron-down-outline']");

        if (lmsDropdownDown.isDisplayed()) {
            lmsDropdownDown.click();
        }
        $x("//li[./*/label[@title='" + connection.value + "']]").click();
        return this;
    }

    /**
     * @param role that will be selected
     * @return IBUserImportPage.class
     */
    @Step("Select Role")
    public IBUserImportPage selectRole(IBUsersRolesTypeEnum role) {

        if (!$x("//div[contains (@label,'Role')]//span[contains (text(),'" + role.value + "')]").exists()) {
            $x("//div[contains (@label,'Role')]")
                    .click();
            $x("//div[contains (@label,'Role')]//li[.//label[contains (@title,'" + role.value + "')]]")
                    .click();
        }
        return this;
    }

    /**
     * @param connectionName Connection Name that will be associated with IBUser
     * @return IBUserImportPage.class
     */
    @Step("Select Connection")
    public IBUserImportPage selectConnection(String connectionName) {

        SelenideElement connection = $x("//input[contains (@id, 'connections') and contains (@id, '" + connectionName + "')]");
        if (!connection.isSelected()) {
            connection.click();
        }
        return this;
    }

    /**
     * @param filePath CSV file path
     * @return IBUserImportPage.class
     */
    @Step("Upload Import CSV File")
    public IBUserImportPage uploadImportCSVFile(ProjectFilesEnum filePath) {
        File file = new File(filePath.path);
        SelenideElement chooseFile = $x("//input[@id='file']");
        chooseFile.uploadFile(file);
        return this;
    }

    @Step("Submit Form")
    public IBUsersPage submitForm() {
        $x("//button[@type='submit']").click();
        return IBUsersPage.init();
    }
}
