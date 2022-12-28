package net.intelliboard.next.tests.in_contact;

import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.helpers.DataGenerator;
import net.intelliboard.next.services.pages.connections.ConnectionsTypeEnum;
import net.intelliboard.next.services.pages.header.HeaderAppsItemEnum;
import net.intelliboard.next.services.pages.header.HeaderConnectionManager;
import net.intelliboard.next.services.pages.header.HeaderObject;
import net.intelliboard.next.services.pages.incontact.InContactActionEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

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

    @Test
    @Tags(value = {@Tag("critical"), @Tag("SP-T108"), @Tag("smoke")})
    @DisplayName("SP-T108: Creating a note in the List")
    void testCreateNoteInListContact() {

        HeaderConnectionManager
                .expandOpenConnectionManager()
                .selectConnection(ConnectionsTypeEnum.CANVAS.defaultName);

        HeaderObject
                .init()
                .openApp(HeaderAppsItemEnum.INCONTACT)
                .addNewMenu("Kena",
                        LocalDateTime.now(),
                        "SP-T108_" + DataGenerator.getRandomString());


        System.out.println("Test OK!");


    }
}
