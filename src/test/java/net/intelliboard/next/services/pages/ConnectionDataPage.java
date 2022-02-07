package net.intelliboard.next.services.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class ConnectionDataPage {

    private SelenideElement buttonDelete = $x("//div //ul /li /a[contains(text(), 'Delete')]");

    public void deleteConnection(String lmsNameField) {
        $x("//a[contains(text(),'" + lmsNameField + "')]/ancestor-or-self::tr//button[contains " +
                "(@class,'dropdown-toggle')]").click();
        buttonDelete.click();
        Selenide.confirm();
        $x("//p[contains (text(), 'Success Delete')]").shouldBe(Condition.visible);
    }
}
