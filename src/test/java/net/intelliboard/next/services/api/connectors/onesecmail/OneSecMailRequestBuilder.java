package net.intelliboard.next.services.api.connectors.onesecmail;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.specification.RequestSpecification;
import net.intelliboard.next.services.api.dto.OneSecMailEmailBoxesListDTO;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class OneSecMailRequestBuilder {

    public OneSecMailEmailBoxesListDTO generateNewMailBoxes(String countBoxes) {
        RequestSpecBuilder builder = new RequestSpecBuilder();

        HashMap<String, String> params = new HashMap<>();

        params.put("action", OneSecMailActionsEnum.GENERATE_RANDOM_BOX.value);
        params.put("count", countBoxes);
        builder.setBaseUri("https://www.1secmail.com");
        builder.setBasePath("/api/v1/");
        builder.addParams(params);
        RequestSpecification spec = builder.build();

        return given(spec)
                .when()
                .get()
                .as(OneSecMailEmailBoxesListDTO.class);
    }
}
