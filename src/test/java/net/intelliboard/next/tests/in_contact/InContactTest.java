package net.intelliboard.next.tests.in_contact;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import net.intelliboard.next.IBNextAbstractTest;
import net.intelliboard.next.services.ProjectFilesEnum;
import net.intelliboard.next.services.helpers.DataGenerator;
import net.intelliboard.next.services.pages.connections.ConnectionsListPage;
import net.intelliboard.next.services.pages.connections.ConnectionsTypeEnum;
import net.intelliboard.next.services.pages.connections.connection.blackboard.CreateBlackBoardConnectionPage;
import net.intelliboard.next.services.pages.header.HeaderAppsItemEnum;
import net.intelliboard.next.services.pages.header.HeaderConnectionManager;
import net.intelliboard.next.services.pages.header.HeaderObject;
import net.intelliboard.next.services.pages.incontact.InContactFilterDurationEnum;
import net.intelliboard.next.services.pages.incontact.InContactMainPage;
import net.intelliboard.next.services.pages.incontact.InContactSeeUserContactInfoModal;
import net.intelliboard.next.services.pages.incontact.InContactViewOptionEnum;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static com.codeborne.selenide.Selenide.open;
import static net.intelliboard.next.services.IBNextURLs.ALL_CONNECTIONS;
import static net.intelliboard.next.services.IBNextURLs.CREATE_BLACKBOARD_CONNECTION;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Tag("InContact")
@Feature("InContact")
class InContactTest extends IBNextAbstractTest {

    /*
    Default connection is "Automation Canvans"
     */

    private final String accountName = "Automation testing";
    private final String userName = "Kena";
    private final LocalDateTime currentDate = LocalDateTime.now();

    @Test
    @Tags(value = {@Tag("critical"), @Tag("SP-T87"), @Tag("smoke"), @Tag("smoke_incontact")})
    @DisplayName("SP-T87: List of students")
    void testListStudentsInContact() {

        HeaderConnectionManager
                .expandOpenConnectionManager()
                .selectConnection(ConnectionsTypeEnum.CANVAS.defaultName);

        HeaderObject
                .init()
                .openApp(HeaderAppsItemEnum.INCONTACT);

        assertThat(InContactMainPage.init().getNumberOfContacts() > 0)
                .withFailMessage(" Contact list is empty")
                .isTrue();
    }

    @Test
    @Tags(value = {@Tag("critical"), @Tag("SP-T108"), @Tag("smoke"), @Tag("smoke_incontact")})
    @DisplayName("SP-T108: Creating a note in the List")
    void testCreateNoteInListContact() {

        String eventName = "SP-T108_" + DataGenerator.getRandomString();
        LocalDateTime date = LocalDateTime.now();

        HeaderConnectionManager
                .expandOpenConnectionManager()
                .selectConnection(ConnectionsTypeEnum.CANVAS.defaultName);

        HeaderObject
                .init()
                .openApp(HeaderAppsItemEnum.INCONTACT);

        InContactMainPage.init()
                .addNewMenu("Kena",
                        date,
                        eventName);

        assertThat(
                InContactMainPage
                        .init()
                        .checkEventExist(eventName, date))
                .withFailMessage("Event %s is not existed", eventName)
                .isTrue();

        InContactMainPage
                .init()
                .deleteEvent(eventName, date);

        assertThat(
                InContactMainPage
                        .init()
                        .checkEventExist(eventName, date))
                .withFailMessage("Event %s is still existed", eventName)
                .isFalse();
    }

    @Test
    @Tags(value = {@Tag("critical"), @Tag("SP-T85"), @Tag("smoke"), @Tag("smoke_incontact")})
    @DisplayName("SP-T85: Turn on InContact and Bucket in the connection")
    void testTurnInContactBucketInConnection() throws InterruptedException {

        //Create a connection
        String connectionName = "SP-T85_" + DataGenerator.getRandomString();
        open(CREATE_BLACKBOARD_CONNECTION);

        CreateBlackBoardConnectionPage
                .init()
                .createBlackboardConnection(
                        connectionName,
                        CreateBlackBoardConnectionPage.BLACKBOARD_CLIENT_ID,
                        CreateBlackBoardConnectionPage.BLACKBOARD_LMS_URL)
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
    @Tags(value = {@Tag("high"), @Tag("SP-T128"), @Tag("smoke"), @Tag("smoke_incontact")})
    @DisplayName("SP-T128: View a note in the list")
    void testViewNoteInListContact() {

        String eventName = "SP-T128_" + DataGenerator.getRandomString();
        LocalDateTime date = LocalDateTime.now();

        HeaderConnectionManager
                .expandOpenConnectionManager()
                .selectConnection(ConnectionsTypeEnum.CANVAS.defaultName);

        HeaderObject
                .init()
                .openApp(HeaderAppsItemEnum.INCONTACT);

        InContactMainPage.init()
                .addNewMenu("Kena",
                        date,
                        eventName);

        InContactMainPage inContactMainPage = InContactMainPage.init();

        Selenide.sleep(SLEEP_TIMEOUT_LONG);

        assertThat(
                inContactMainPage
                        .checkEventExist(eventName, date))
                .withFailMessage("Event %s is not existed", eventName)
                .isTrue();

        SoftAssertions softly = new SoftAssertions();

        softly.assertThat(
                        inContactMainPage
                                .checkAuthorOfEvent(eventName, accountName, date))
                .withFailMessage("Author is not match : - %s", accountName)
                .isTrue();

        softly.assertThat(
                        inContactMainPage
                                .checkUserOfEvent(eventName, userName, date))
                .withFailMessage("User is not match : - %s", userName)
                .isTrue();

        softly.assertThat(
                        inContactMainPage
                                .checkDateOfEvent(eventName, currentDate, date))
                .withFailMessage("Event creation date is not match : - %s", currentDate)
                .isTrue();

        softly.assertAll();

        inContactMainPage
                .deleteEvent(eventName, date);

        Selenide.sleep(SLEEP_TIMEOUT_LONG);

        assertThat(
                InContactMainPage
                        .init()
                        .checkEventExist(eventName, date))
                .withFailMessage("Event %s is still existed", eventName)
                .isFalse();
    }

    @Test
    @Tags(value = {@Tag("high"), @Tag("SP-T110"), @Tag("smoke")})
    @DisplayName("SP-T110: Deleting a note in the List")
    void testDeleteNoteInListContact() {

        String eventName = "SP-T110_" + DataGenerator.getRandomString();
        LocalDateTime date = LocalDateTime.now();

        HeaderConnectionManager
                .expandOpenConnectionManager()
                .selectConnection(ConnectionsTypeEnum.CANVAS.defaultName);

        HeaderObject
                .init()
                .openApp(HeaderAppsItemEnum.INCONTACT);

        InContactMainPage
                .init()
                .addNewMenu("Kena",
                        date,
                        eventName);

        assertThat(
                InContactMainPage
                        .init()
                        .checkEventExist(eventName, date))
                .withFailMessage("Event %s is not existed", eventName)
                .isTrue();

        InContactMainPage
                .init()
                .deleteEvent(eventName, date);

        Selenide.sleep(SLEEP_TIMEOUT_SHORT);

        assertThat(
                InContactMainPage
                        .init()
                        .checkEventExist(eventName, date))
                .withFailMessage("Event %s is still existed", eventName)
                .isFalse();
    }

    @Test
    @Tags(value = {@Tag("high"), @Tag("SP-T86"), @Tag("smoke"), @Tag("smoke_incontact")})
    @DisplayName("SP-T86: Course in Course dropdown")
    void testInContact() {

        String eventName = "SP-T86 Filtering Course";
        LocalDateTime date = LocalDateTime.of(2023, 2, 11, 0, 0);

        HeaderConnectionManager
                .expandOpenConnectionManager()
                .selectConnection(ConnectionsTypeEnum.CANVAS.defaultName);

        HeaderObject
                .init()
                .openApp(HeaderAppsItemEnum.INCONTACT);

        InContactMainPage
                .init()
                .openFilter()
                .setCourse("20th")
                .setDuration(InContactFilterDurationEnum.LAST_90_DAYS)
                .closeFilterModal();

        Selenide.sleep(SLEEP_TIMEOUT_SHORT);

        assertThat(
                InContactMainPage
                        .init()
                        .checkEventExist(eventName, date))
                .withFailMessage("Event %s is not existed", eventName)
                .isTrue();
    }

    @Test
    @Tags(value = {@Tag("high"), @Tag("SP-T107"), @Tag("smoke"), @Tag("smoke_incontact")})
    @DisplayName("SP-T107: Uploading CSV contacts")
    void testUploadInContactCSVContact() {

        HeaderConnectionManager
                .expandOpenConnectionManager()
                .selectConnection(ConnectionsTypeEnum.CANVAS.defaultName);

        HeaderObject
                .init()
                .openApp(HeaderAppsItemEnum.INCONTACT);

        InContactMainPage
                .init()
                .openFilter()
                .uploadCSVFile(ProjectFilesEnum.INCONTACT_IMPORT_CSV);
    }

    @Test
    @Tags(value = {@Tag("high"), @Tag("SP-T221"), @Tag("smoke"), @Tag("smoke_incontact")})
    @DisplayName("SP-T221: Adding contacts to students (pencil)")
    @Description("Adding contacts to students (pencil)")
    void testAddingContactsByPencil() {

        String key = "key_" + DataGenerator.getRandomString();
        String value = "value_" + DataGenerator.getRandomString();

        HeaderConnectionManager
                .expandOpenConnectionManager()
                .selectConnection(ConnectionsTypeEnum.CANVAS.defaultName);

        HeaderObject
                .init()
                .openApp(HeaderAppsItemEnum.INCONTACT);

        InContactMainPage
                .init()
                .addUserContactInformation(userName, key, value);

        assertThat(InContactMainPage.init().isUserDataExist(userName, key, value))
                .withFailMessage("User data - key: %s, value: %s for %s is not existed", key, value, userName)
                .isTrue();

        InContactSeeUserContactInfoModal
                .init()
                .closeModal()
                .deleteAllUserContactInformation(userName);
    }

    @Test
    @Tags(value = {@Tag("high"), @Tag("SP-T92"), @Tag("smoke"), @Tag("smoke_incontact")})
    @DisplayName("SP-T92: Contact type in Communication dropdown")
    @Description("Contact type in Communication dropdown")
    void testContactTypeCommunicationDropdown() {

        String contactType = "Email";
        String eventName = "SP-T86 Filtering Course";
        LocalDateTime date = LocalDateTime.of(2023, 2, 11, 0, 0);

        HeaderConnectionManager
                .expandOpenConnectionManager()
                .selectConnection(ConnectionsTypeEnum.CANVAS.defaultName);

        HeaderObject
                .init()
                .openApp(HeaderAppsItemEnum.INCONTACT);

        InContactMainPage
                .init()
                .openFilter()
                .setCommunication(contactType)
                .setDuration(InContactFilterDurationEnum.LAST_90_DAYS);

        assertThat(
        InContactMainPage
                .init()
                .checkEventExist(eventName, date))
                .withFailMessage("Event %s has not communication %s", eventName, contactType)
                .isTrue();
    }

    @Test
    @Tags(value = {@Tag("high"), @Tag("SP-T93"), @Tag("smoke"), @Tag("smoke_incontact")})
    @DisplayName("SP-T93: Adding data in the Multi Select")
    @Description("Adding data in the Multi Select")
    void testAddingDataMultiSelect() {

        String contactType = "Email";
        String course = "20th";
        String student = "Alona";
        String eventName = "SP-T92_" + DataGenerator.getRandomString();
        LocalDateTime date = LocalDateTime.now();

        HeaderConnectionManager
                .expandOpenConnectionManager()
                .selectConnection(ConnectionsTypeEnum.CANVAS.defaultName);

        HeaderObject
                .init()
                .openApp(HeaderAppsItemEnum.INCONTACT);

        InContactMainPage
                .init()
                .openFilter()
                .openMultiFilter()
                .setCourse(course)
                .setStudent(student)
                .setDate(date)
                .setTextMessage(eventName)
                .setOption("Communication", "[All Options]")
                .setOption("Behavior", "[All Options]")
                .submitForm();

        assertThat(InContactMainPage
                .init()
                .checkEventExist(eventName, date))
                .withFailMessage("Event %s has not communication %s", eventName, contactType)
                .isTrue();

        InContactMainPage
                .init()
                .deleteEvent(eventName, date);
    }

    @Test
    @Tags(value = {@Tag("high"), @Tag("SP-T261"), @Tag("smoke"), @Tag("smoke_incontact")})
    @DisplayName("SP-T261: Adding an event in the 'Stored Logs' pop-up")
    @Description("Adding an event in the 'Stored Logs' pop-up")
    void testAddingStoreLogEvent() {

        String sisIds = "45454d";
        String eventName = "SP-T261_" + DataGenerator.getRandomString();
        String course = "LTI";
        LocalDateTime date = LocalDateTime.now();

        //Select Connection Moodle
        HeaderConnectionManager
                .expandOpenConnectionManager()
                .selectConnection(ConnectionsTypeEnum.MOODLE.defaultName);

        //Create Store Log event
        HeaderObject
                .init()
                .openApp(HeaderAppsItemEnum.INCONTACT);

        InContactMainPage
                .init()
                .openFilter()
                .openStoredLog()
                .setSisIDs(sisIds)
                .setCourse(course)
                .setTextMessage(eventName)
                .setDate(date)
                .setOption("Communication", "[All Options]")
                .setOption("Behavior", "[All Options]")
                .submitForm();
        //Check that event exist
        InContactMainPage
                .init()
                .switchMode(InContactViewOptionEnum.ALBUMS);

        assertThat(InContactMainPage.init().checkEventExist(eventName, date))
                .withFailMessage("Event Stored Logs : %s is not existed", eventName)
                .isTrue();

        //Delete Event
        InContactMainPage
                .init()
                .deleteEvent(eventName, date);
    }
}