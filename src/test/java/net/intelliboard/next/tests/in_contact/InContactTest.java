package net.intelliboard.next.tests.in_contact;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Feature;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.helpers.DataGenerator;
import net.intelliboard.next.services.pages.connections.ConnectionsListPage;
import net.intelliboard.next.services.pages.connections.ConnectionsTypeEnum;
import net.intelliboard.next.services.pages.connections.CreateConnectionPage;
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

import static com.codeborne.selenide.Selenide.open;
import static net.intelliboard.next.services.IBNextURLs.*;
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

    @Test
    @Tags(value = {@Tag("critical"), @Tag("SP-T85"), @Tag("smoke")})
    @DisplayName("SP-T85: Turn on InContact and Bucket in the connection")
    void testTurnInContactBucketInConnection() throws InterruptedException {

        //Create a connection
        String connectionName = "SP-T85_" + DataGenerator.getRandomString();
        open(CREATE_BLACKBOARD_CONNECTION);

        CreateConnectionPage
                .init()
                .createBlackboardConnection(
                        connectionName,
                        CreateConnectionPage.BLACKBOARD_CLIENT_ID,
                        CreateConnectionPage.BLACKBOARD_LMS_URL)
                .saveFilterSettings()
                .setActiveConnection(connectionName, true)
                .editConnection(connectionName)
                .processData()
                .waitingProcessingComplete()
                .backToDashBoardConnectionList();

        HeaderConnectionManager
                .expandOpenConnectionManager()
                .selectConnection(connectionName);

        HeaderObject
                .init()
                .openApp(HeaderAppsItemEnum.INCONTACT);
        //Clean up - delete the connection
        open(ALL_CONNECTIONS);
        ConnectionsListPage
                .init()
                .deleteConnection(connectionName);
    }

    @Test
    @Tags(value = {@Tag("high"), @Tag("SP-T110"), @Tag("smoke")})
    @DisplayName("SP-T110: Deleting a note in the List")
    void testDeleteNoteInListContact() throws IOException {

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