package net.intelliboard.next.services.pages.dashboard;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.pages.myintelliboard.MyIntelliBoardPage;
import org.openqa.selenium.WebElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class CreateDashboardPage extends IBNextAbstractTest {

    @Getter
    public SelenideElement savePublishButton = $x("//div[@class='vue-portal-target']//button[contains(@class,'primary')]");

    private final SelenideElement dashboardTitle = $x("//div[contains(@class, 'left-sub-menu')]//button[contains (@class, 'title')]");
    private final WebElement textBlock = $x("//div[@class='tabs']//div[@class='list-group']//li[1]");
    private final SelenideElement dashboardMain = $x("//div[@class='vue-grid-layout']");


    public static CreateDashboardPage init() {
        $x("//main").shouldBe(Condition.exist, Duration.ofSeconds(30));
        return new CreateDashboardPage();
    }

    @Step("Change name and description")
    public CreateDashboardPage changeNameAndDescription(String dashboardName, String dashboardDescription) {
        openSettingsModal();
        fillInName(dashboardName);
        fillInDescription(dashboardDescription);
        $x("//div[@class='modal-content']//button[@type='submit']")
                .click();
        dashboardTitle.shouldHave(Condition.text(dashboardName));
        return this;
    }

    @Step("Open Settings Modal")
    public CreateDashboardPage openSettingsModal() {
        dashboardTitle.click();
        $x("//div[@class='modal-content']").shouldBe(Condition.visible);
        return this;
    }

    @Step("Fill in name")
    public CreateDashboardPage fillInName(String dashboardName) {
        $x("//div[@class='modal-content']//input[@id='name']").setValue(dashboardName);
        return this;
    }

    @Step("Fill in Description")
    public CreateDashboardPage fillInDescription(String dashboardDescription) {
        $x("//div[@class='modal-content']//textarea[@name='description']")
                .setValue(dashboardDescription);
        return this;
    }

    @Step("Add Text Block")
    //TODO [MO] Make it generic with enum as blocks
    public CreateDashboardPage addTextBlock() {

        Selenide.actions()
                .dragAndDropBy(textBlock, -300, 100)
                .perform();
        return this;
    }

    @Step("Save and publish")
    public DashboardPage saveAndPublishDashboard() {
        $x("//div[@class='vue-portal-target']//button[contains(@class,'primary')]")
                .shouldBe(Condition.enabled)
                .click();
        return DashboardPage.init();
    }

    @Step("Get title name")
    public String getTitleName() {
        return dashboardTitle.getText();
    }

    @Step("Delete Dashboard")
    public MyIntelliBoardPage deleteDashboard() {
        $x("//div[contains(@class,'modal-content')]//a[contains(@href,'/force')]").click();
        Selenide.confirm();
        return MyIntelliBoardPage.init();
    }
}
