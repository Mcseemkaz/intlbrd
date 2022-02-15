package net.intelliboard.next.services.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class CreateConnectionPage {
    private SelenideElement lmsNameField = $x("//input[@id=\"lmsName\"]");
    private SelenideElement clientIdField = $x("//input[@id=\"clientId\"]");
    private SelenideElement lmsUrlField = $x("//input[@id=\"lmsUrl\"]");
    private SelenideElement clientSecretField = $x("//input[@id=\"clientSecret\"]");
    private SelenideElement dataClientIdField = $x("//input[@id=\"dataClientId\"]");
    private SelenideElement dataClientSecretField = $x("//input[@id=\"dataClientSecret\"]");
    private SelenideElement buttonContinue = $x("//button[@type=\"submit\"]");

    public void createMoodleConnection(String lmsName, String clientId, String lmsUrl) {
        lmsNameField.setValue(lmsName);
        clientIdField.setValue(clientId);
        lmsUrlField.setValue(lmsUrl);
        buttonContinue.click();
    }

    public void createCanvasConnection(String lmsName, String clientId, String lmsUrl, String clientSecret,
                                       String dataClientId, String dataClientSecret) {
        lmsNameField.setValue(lmsName);
        clientIdField.setValue(clientId);
        lmsUrlField.setValue(lmsUrl);
        clientSecretField.setValue(clientSecret);
        dataClientIdField.setValue(dataClientId);
        dataClientSecretField.setValue(dataClientSecret);
        buttonContinue.click();
    }
}
