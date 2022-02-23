package net.intelliboard.next.services.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import net.intelliboard.next.services.IBNextURLs;

import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class ConnectionDataPage {

    private SelenideElement buttonDelete = $x("//div //ul /li /a[contains(text(), 'Delete')]");

    public void deleteConnection(String lmsNameField) {
        String pageUrl = IBNextURLs.ALL_CONNECTIONS + "?page=";
        List<String> pagination = Arrays.asList($x("//nav //ul[contains (@class,'pagination')]")
                .getText().split("[‹\\n›]"));
        if (pagination.size() > 0) {
            for (int i = 0; i < pagination.size(); i++) {
                if (!pagination.get(i).isEmpty()
                        && pagination.get(i).matches("\\d+")) {
                    open(pageUrl + pagination.get(i));
                    SelenideElement isConnection = $x("//a[contains(text(),'" + lmsNameField + "')]" +
                            "/ancestor-or-self::tr//button[contains (@class,'dropdown-toggle')]");
                    if (isConnection.exists()) {
                        isConnection.click();
                        buttonDelete.click();
                        Selenide.confirm();
                        $x("//p[contains (text(), 'Success Delete')]").shouldBe(Condition.visible);
                    }
                }
            }

        }
    }
}
