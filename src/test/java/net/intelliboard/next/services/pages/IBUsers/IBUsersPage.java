package net.intelliboard.next.services.pages.IBUsers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.IBNextURLs;

import static com.codeborne.selenide.Selenide.$x;
import static org.assertj.core.api.Assertions.assertThat;

public class IBUsersPage {

    public static IBUsersPage init() {
        IBNextAbstractTest ibNextAbstractTest = new IBNextAbstractTest();
        ibNextAbstractTest.waitForPageLoaded();
        $x("//div[@class='content-body']").shouldBe(Condition.visible);
        ibNextAbstractTest.checkPageURL(IBNextURLs.USERS_PAGE);
//        assertThat(WebDriverRunner.getWebDriver().getCurrentUrl()).isEqualTo(IBNextURLs.USERS_PAGE);
        return new IBUsersPage();
    }

    public IBUsersCreatePage openIBUserCreatePage(){
        $x("//button[@type=\'submit\']").click();
        $x("//li//a[contains (@href,'"+IBNextURLs.USERS_CREATE_PAGE+"')]")
                .shouldBe(Condition.visible).click();
        return IBUsersCreatePage.init();
    }
}
