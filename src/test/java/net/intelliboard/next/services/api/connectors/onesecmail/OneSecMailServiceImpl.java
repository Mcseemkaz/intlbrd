package net.intelliboard.next.services.api.connectors.onesecmail;

import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import net.intelliboard.next.services.api.connectors.MailService;
import net.intelliboard.next.services.api.pojo.onesecmail.OneSecMailMessagePOJO;
import net.intelliboard.next.services.api.pojo.onesecmail.OneSecMailMessagesShortPOJO;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;

import static io.restassured.RestAssured.given;

public class OneSecMailServiceImpl implements MailService {

    private final String BASE_URI = "https://www.1secmail.com";
    private final String BASE_PATH = "/api/v1/";

    @Step("Get Registration Link in the Email")
    @Override
    public String getRegistrationLink(String emailBoxName) {
        String messageHTMLBody = getMessageById(emailBoxName).getHtmlBody();
        return getCutRegistrationLink(messageHTMLBody);
    }

    @Step("Generate new Email Box")
    @Override
    public String generateNewMailBox() {

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

        String id = getFirstEmailId(emailBox);



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


    private String getFirstEmailId(String emailBox) {

        OneSecMailMessagesShortPOJO[] emailList;
        emailList = getMessagesList(emailBox);

        while(emailList.length == 0){
            try {
                Thread.sleep(5000);
                //TODO [MO] For Jenkins Debugging purpose only - need to remove after stabilize
                System.err.println("-------wait--------");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            emailList = getMessagesList(emailBox);
        }

        String id = String.valueOf
                (Arrays.stream(emailList)
                        .findFirst()
                        .get()
                        .getId());

        return id;
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
