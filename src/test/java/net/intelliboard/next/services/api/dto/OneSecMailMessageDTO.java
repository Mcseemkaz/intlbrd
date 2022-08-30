package net.intelliboard.next.services.api.dto;

import lombok.Data;

import java.io.File;
import java.util.ArrayList;

@Data
public class OneSecMailMessageDTO {

    int id;
    String from;
    String subject;
    String date;
    ArrayList<Object> attachments;
    String body;
    String textBody;
    String htmlBody;

}
