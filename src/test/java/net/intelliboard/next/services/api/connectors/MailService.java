package net.intelliboard.next.services.api.connectors;

import io.qameta.allure.Step;

public interface MailService {

    @Step("Check Email Box and get registration link")
    String getRegistrationLink(String emailBoxName);

    @Step("Generate Email Box")
    String generateNewMailBox();
}
