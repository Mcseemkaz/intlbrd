package net.intelliboard.next.services.pages.dashboard;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import net.intelliboard.next.services.pages.myintelliboard.MyIntelliBoardPage;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class CreateDashboardPage {

    @Getter
    public SelenideElement savePublishButton =   $x("//div[@class='vue-portal-target']//button[contains(@class,'primary')]");

    private SelenideElement dashboardTitle = $x("//div[contains(@class, 'left-sub-menu')]//h3");
    private SelenideElement textBlock = $x("//div[@class='tabs']//div[@class='list-group']//li[1]//span[@class='library-description']");
    private SelenideElement dashboardMain = $x("//div[@class='vue-grid-layout']");


    public static CreateDashboardPage init() {
        $x("//main").shouldBe(Condition.exist, Duration.ofSeconds(30));
        return new CreateDashboardPage();
    }

    public CreateDashboardPage changeNameAndDescription(String dashboardName, String dashboardDescription) {
        openSettingsModal();
        fillInName(dashboardName);
        fillInDescription(dashboardDescription);
        $x("//div[@class='modal-content']//button[@type='submit']")
                .click();
        dashboardTitle.shouldHave(Condition.text(dashboardName));
        return this;
    }

    public CreateDashboardPage openSettingsModal() {
        dashboardTitle.click();
        $x("//div[@class='modal-content']").shouldBe(Condition.visible);
        return this;
    }

    public CreateDashboardPage fillInName(String dashboardName) {
        $x("//div[@class='modal-content']//input[@id='name']").setValue(dashboardName);
        return this;
    }

    public CreateDashboardPage fillInDescription(String dashboardDescription) {
        $x("//div[@class='modal-content']//textarea[@name='description']")
                .setValue(dashboardDescription);
        return this;
    }

    //TODO [MO] Make it generic with enum as blocks
    public CreateDashboardPage addTextblock() {
        Selenide.actions()
                .dragAndDrop(textBlock, dashboardMain)
                .perform();
        return this;
    }

    public DashboardPage saveAndPublischDashboard() {
        $x("//div[@class='vue-portal-target']//button[contains(@class,'primary')]")
                .shouldBe(Condition.enabled)
                .click();
        return DashboardPage.init();
    }

    public String getTitileName() {
        return dashboardTitle.getText();
    }

    public MyIntelliBoardPage deleteDashboard() {
        $x("//div[contains(@class,'modal-content')]//a[contains(@href,'/force')]").click();
        Selenide.confirm();
        return MyIntelliBoardPage.init();
    }
}
