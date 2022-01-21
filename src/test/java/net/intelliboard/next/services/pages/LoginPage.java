package net.intelliboard.next.services.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {

    public SelenideElement loginField = $x("//input[@id=\"login-email\"]");
    public SelenideElement passwordField = $x("//input[@id=\"login-password\"]");
    public SelenideElement buttonSubmit = $x("//button[@type=\"submit\"]");

}


