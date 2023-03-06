package net.intelliboard.next.services.pages.myintelliboard.modals;

import com.codeborne.selenide.Condition;
import net.intelliboard.next.services.pages.elements.spinners.PageSpinner;
import net.intelliboard.next.services.pages.myintelliboard.MyIntelliBoardPage;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class DashboardDeleteModalPage {
    public static DashboardDeleteModalPage init() {
        PageSpinner.waitPreloader();
        PageSpinner.waitSpinner();
        $x("//div[@class='modal-content']").shouldBe(Condition.visible, Duration.ofSeconds(30));
        return new DashboardDeleteModalPage();
    }

    public DashboardDeleteModalPage checkDeleteOption(DeleteOptionsEnum option, boolean checkStatus) {
        $x("//div[contains (@class,'delete-confirmation-modal')]//li [ .//label[contains (text(),'" + option.value + "')]]//input[@type='checkbox']")
                .setSelected(checkStatus);
        return this;
    }

    public MyIntelliBoardPage confirmDeletion() {
        $x("//a[@class='app-button error']")
                .click();
        $x("//div[@class='modal-content']")
                .should(Condition.not(Condition.visible), Duration.ofSeconds(120));
        return MyIntelliBoardPage.init();
    }
}
