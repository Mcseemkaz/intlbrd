package net.intelliboard.next.services.api.dto;

import lombok.Data;

import java.util.ArrayList;

@Data
public class OneSecMailMessagesListDTO {

    ArrayList<OneSecMailMessagesShortDTO> messagesList;
}
