package net.intelliboard.next.tests.in_contact;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Feature;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.helpers.DataGenerator;
import net.intelliboard.next.services.pages.connections.ConnectionsTypeEnum;
import net.intelliboard.next.services.pages.header.HeaderAppsItemEnum;
import net.intelliboard.next.services.pages.header.HeaderConnectionManager;
import net.intelliboard.next.services.pages.header.HeaderObject;
import net.intelliboard.next.services.pages.incontact.InContactMainPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Tag("InContact")
@Feature("InContact")
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
    void testCreateNoteInListContact() throws IOException {

        String eventName = "SP-T108_" + DataGenerator.getRandomString();
        LocalDateTime date = LocalDateTime.now();

        HeaderConnectionManager
                .expandOpenConnectionManager()
                .selectConnection(ConnectionsTypeEnum.CANVAS.defaultName);

        HeaderObject
                .init()
                .openApp(HeaderAppsItemEnum.INCONTACT)
                .addNewMenu("Kena",
                        date,
                        eventName
                );

        assertThat(
                InContactMainPage
                        .init()
                        .checkEventExist(eventName, date))
                .withFailMessage("Event %s is not existed", eventName)
                .isTrue();

        InContactMainPage
                .init()
                .deleteEvent(eventName, date);

        Selenide.sleep(Long.parseLong(propertiesGetValue.getPropertyValue("sleep_time")));

        assertThat(
                InContactMainPage
                        .init()
                        .checkEventExist(eventName, date))
                .withFailMessage("Event %s is still existed", eventName)
                .isFalse();
    }
}
