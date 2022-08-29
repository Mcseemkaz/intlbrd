package net.intelliboard.next.services.api.connectors.onesecmail;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import net.intelliboard.next.services.api.dto.OneSecMailMessageDTO;
import net.intelliboard.next.services.api.dto.OneSecMailMessagesShortDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class OneSecMailRequestBuilder {

    private final String baseUri = "https://www.1secmail.com";
    private final String basePath = "/api/v1/";

    public String generateNewMailBoxes() {
        RequestSpecBuilder builder = new RequestSpecBuilder();

        HashMap<String, String> params = new HashMap<>();

        params.put("action", OneSecMailActionsEnum.GENERATE_RANDOM_BOX.value);
        params.put("count", "1");
        builder.setBaseUri(baseUri);
        builder.setBasePath(basePath);
        builder.addParams(params);
        RequestSpecification spec = builder.build();

        String jsonString = given(spec)
                .when()
                .get()
                .asString();

        return StringUtils.substringBetween(jsonString, "\"", "\"");
    }

    public OneSecMailMessagesShortDTO[] getListEmails(String emailBox) {

        RequestSpecBuilder builder = new RequestSpecBuilder();

        HashMap<String, String> params = new HashMap<>();

        params.put("action", OneSecMailActionsEnum.GET_MESSAGES.value);
        params.put("login", getEmailLogin(emailBox));
        params.put("domain", getEmailDomain(emailBox));
        builder.setBaseUri(baseUri);
        builder.setBasePath(basePath);
        builder.addParams(params);
        RequestSpecification spec = builder.build();

        return given(spec)
                .when()
                .get()
                .as(OneSecMailMessagesShortDTO[].class);
    }

    public OneSecMailMessageDTO getMessageById(String emailBox, String id) {
        RequestSpecBuilder builder = new RequestSpecBuilder();

        HashMap<String, String> params = new HashMap<>();

        params.put("action", OneSecMailActionsEnum.READ_MESSAGE.value);
        params.put("login", getEmailLogin(emailBox));
        params.put("domain", getEmailDomain(emailBox));
        params.put("id", id);
        builder.setBaseUri(baseUri);
        builder.setBasePath(basePath);
        builder.addParams(params);
        RequestSpecification spec = builder.build();

        return given(spec)
                .when()
                .get()
                .as(OneSecMailMessageDTO.class);
    }

    public String getRegistrationLink(String body) {
        return StringUtils.substringBetween(body, "href=\"", "\"");
    }

    private String getEmailLogin(String emailBox) {
        return StringUtils.substringBefore(emailBox, "@");
    }

    private String getEmailDomain(String emailBox) {
        return StringUtils.substringAfter(emailBox, "@");
    }
}
