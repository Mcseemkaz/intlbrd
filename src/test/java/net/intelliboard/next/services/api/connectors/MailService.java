package net.intelliboard.next.services.api.connectors;

public interface MailService {

    String getRegistrationLink(String emailBoxName);

    String generateNewMailBoxes();
}
