package net.intelliboard.next.services.api.connectors.mailtramp;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import net.intelliboard.next.services.api.connectors.MailService;
import net.intelliboard.next.services.api.pojo.mailtrap.MailTrapMessagePOJO;
import net.intelliboard.next.services.helpers.DataGenerator;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class MailTrapServiceImpl implements MailService {

    private final String BASE_URI = "https://mailtrap.io";
    private final String API_TOKEN = "dd9e1b53344f7004c1c01c9956f9ae7c";
    private final String ACCOUNT_ID = "112881";
    private final String INBOX_ID = "1317990";

    private String getMessageById(String emailBoxName) {
        MailTrapMessagePOJO[] messages = getMessagesList();

        final ArrayList<String> messagesList = new ArrayList<>();

        Arrays.stream(messages)
                .filter(k -> k.getTo_email().contains(emailBoxName))
                .collect(Collectors.toSet())
                .forEach(k -> messagesList.add(Long.toString(k.getId())));

        return messagesList.stream().findFirst().get();
    }

    @Override
    public String getRegistrationLink(String emailBoxName) {

        String message_id = getMessageById(emailBoxName);
        RequestSpecBuilder builder = getPreparedMainRequestPart();
        builder.addPathParam("message_id", message_id);
        builder.setContentType("text/html; charset=utf-8");
        RequestSpecification spec = builder.build();

        getAuthorization();

        String messageBody = given(spec)
                .when()
                .get("/api/accounts/{account_id}/inboxes/{inbox_id}/messages/{message_id}/body.htmlsource")
                .then()
                .statusCode(200)
                .extract()
                .asString();

        return getCutRegistrationLink(messageBody);
    }

    @Override
    public String generateNewMailBox() {
        return DataGenerator.getRandomValidEmail();
    }

    private void getAuthorization() {
        RequestSpecBuilder builder = new RequestSpecBuilder();
        HashMap<String, String> header = new HashMap<>();

        header.put("API-TOKEN", API_TOKEN);
        builder.setBaseUri(BASE_URI);
        builder.addHeaders(header);
        RequestSpecification spec = builder.build();

        given(spec)
                .get("/api/accounts")
                .then()
                .statusCode(200);
    }

    private MailTrapMessagePOJO[] getMessagesList() {

        RequestSpecBuilder builder = getPreparedMainRequestPart();

        RequestSpecification spec = builder.build();

        getAuthorization();

        return given(spec)
                .when()
                .get("/api/accounts/{account_id}/inboxes/{inbox_id}/messages")
                .then()
                .statusCode(200)
                .extract()
                .as(MailTrapMessagePOJO[].class);
    }

    private RequestSpecBuilder getPreparedMainRequestPart() {
        RequestSpecBuilder builder = new RequestSpecBuilder();
        HashMap<String, String> header = new HashMap<>();
        HashMap<String, String> pathParams = new HashMap<>();

        pathParams.put("account_id", ACCOUNT_ID);
        pathParams.put("inbox_id", INBOX_ID);

        header.put("API-TOKEN", API_TOKEN);
        builder.setBaseUri(BASE_URI);
        builder.addHeaders(header);
        builder.addPathParams(pathParams);
        return builder;
    }

    private String getCutRegistrationLink(String body) {
        return StringUtils.substringBetween(body, "href=\"", "\"");
    }
}
