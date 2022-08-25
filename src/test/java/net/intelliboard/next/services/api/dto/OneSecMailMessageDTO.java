package net.intelliboard.next.services.api.dto;

import lombok.Data;

import java.io.File;

@Data
public class OneSecMailMessageDTO {

    int id;
    String from;
    String subject;
    String date;
    File[] attachments;
    String textBody;
    String htmlBody;

}
