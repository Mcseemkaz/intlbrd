package net.intelliboard.next.services.api.connectors.onesecmail;

import com.codeborne.selenide.Selenide;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import net.intelliboard.next.services.api.connectors.MailService;
import net.intelliboard.next.services.api.pojo.onesecmail.OneSecMailMessagePOJO;
import net.intelliboard.next.services.api.pojo.onesecmail.OneSecMailMessagesShortPOJO;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class OneSecMailServiceImpl implements MailService {

    private final String BASE_URI = "https://www.1secmail.com";
    private final String BASE_PATH = "/api/v1/";

    @Override
    public String getRegistrationLink(String emailBoxName) {
        String messageHTMLBody = getMessageById(emailBoxName).getHtmlBody();
        return getCutRegistrationLink(messageHTMLBody);
    }

    @Override
    public String generateNewMailBoxes() {

        // The email service needs a time for email be lend in a box
        Selenide.sleep(10000);

        RequestSpecBuilder builder = getPreparedMainRequestPart();

        HashMap<String, String> params = new HashMap<>();

        params.put("action", OneSecMailActionsEnum.GENERATE_RANDOM_BOX.value);
        params.put("count", "1");
        builder.addParams(params);
        RequestSpecification spec = builder.build();

        String jsonString = given(spec)
                .when()
                .get()
                .asString();

        return StringUtils.substringBetween(jsonString, "\"", "\"");
    }

    private OneSecMailMessagesShortPOJO[] getMessagesList(String emailBoxName) {

        RequestSpecBuilder builder = getPreparedMainRequestPart();
        HashMap<String, String> params = new HashMap<>();

        params.put("action", OneSecMailActionsEnum.GET_MESSAGES.value);
        params.put("login", getEmailLogin(emailBoxName));
        params.put("domain", getEmailDomain(emailBoxName));
        builder.addParams(params);
        RequestSpecification spec = builder.build();

        return given(spec)
                .when()
                .get()
                .as(OneSecMailMessagesShortPOJO[].class);
    }

    private OneSecMailMessagePOJO getMessageById(String emailBox) {

        String id =
                String.valueOf(
                        Arrays.stream(getMessagesList(emailBox))
                                .findFirst()
                                .get()
                                .getId());

        RequestSpecBuilder builder = getPreparedMainRequestPart();

        HashMap<String, String> params = new HashMap<>();

        params.put("action", OneSecMailActionsEnum.READ_MESSAGE.value);
        params.put("login", getEmailLogin(emailBox));
        params.put("domain", getEmailDomain(emailBox));
        params.put("id", id);
        builder.addParams(params);
        RequestSpecification spec = builder.build();

        return given(spec)
                .when()
                .get()
                .as(OneSecMailMessagePOJO.class);
    }

    private String getCutRegistrationLink(String body) {
        return StringUtils.substringBetween(body, "href=\"", "\"");
    }

    private String getEmailLogin(String emailBox) {
        return StringUtils.substringBefore(emailBox, "@");
    }

    private String getEmailDomain(String emailBox) {
        return StringUtils.substringAfter(emailBox, "@");
    }

    private RequestSpecBuilder getPreparedMainRequestPart() {
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBaseUri(BASE_URI);
        builder.setBasePath(BASE_PATH);
        return builder;
    }
}
