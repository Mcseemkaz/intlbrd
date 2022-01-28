package net.intelliboard.next.services.pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;
import static net.intelliboard.next.IBNextAbstractTest.MAIN_URL;

public class ConnectionIntegrationPage {
    public SelenideElement linkConnect = $x("//a[@href='" + MAIN_URL.concat("/connections/create/1") + "' and contains (@class, \"app-button primary-outline\")]");
}
