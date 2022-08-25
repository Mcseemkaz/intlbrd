package net.intelliboard.next.services.api.dto;

import lombok.Data;

@Data
public class OneSecMailMessagesShortDTO {

    int id;
    String from;
    String subject;
    String date;
}
