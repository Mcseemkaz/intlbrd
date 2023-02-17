package net.intelliboard.next.services.api.connectors;

import net.intelliboard.next.services.api.connectors.mailtramp.MailTrapServiceImpl;
import net.intelliboard.next.services.api.connectors.onesecmail.OneSecMailServiceImpl;

public class MailServiceBuilder {

    public static MailService build() {

        MailService mailService;

        if (System.getProperty("TestEnvironment").contains("stage") || System.getProperty("TestEnvironment").contains("dev")) {
            mailService = new MailTrapServiceImpl();
        } else if (System.getProperty("TestEnvironment").contains("prod")) {
            mailService = new OneSecMailServiceImpl();
        } else mailService = null;

        return mailService;
    }
}
