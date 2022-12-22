package net.intelliboard.next.tests.in_contact;

import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.pages.connections.ConnectionsTypeEnum;
import net.intelliboard.next.services.pages.header.HeaderAppsItemEnum;
import net.intelliboard.next.services.pages.header.HeaderConnectionManager;
import net.intelliboard.next.services.pages.header.HeaderObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

public class InContactTest extends IBNextAbstractTest {

    /*
    Default connection is "Automation Canvans"
     */

    @Test
    @Tags(value = {@Tag("critical"), @Tag("SP-T87"), @Tag("smoke")})
    @DisplayName("SP-T87: List of students")
    void testListStudentsInContact() {

        HeaderConnectionManager
                .expandOpenConnectionManager()
                .selectConnection(ConnectionsTypeEnum.CANVAS.defaultName);

        HeaderObject
                .init()
                .openApp(HeaderAppsItemEnum.INCONTACT);

    }
}
