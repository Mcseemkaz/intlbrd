package net.intelliboard.next.services.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class OneSecMailEmailBoxesListDTO {
    @JsonProperty("MyArray")
    public List<String> myArray;
}
