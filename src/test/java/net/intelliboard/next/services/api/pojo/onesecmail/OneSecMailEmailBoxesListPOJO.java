package net.intelliboard.next.services.api.pojo.onesecmail;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class OneSecMailEmailBoxesListPOJO {
    @JsonProperty("MyArray")
    public List<String> myArray;
}
