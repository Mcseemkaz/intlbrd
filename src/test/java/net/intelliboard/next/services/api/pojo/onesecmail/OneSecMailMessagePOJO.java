package net.intelliboard.next.services.api.pojo.onesecmail;

import lombok.Data;

import java.util.ArrayList;

@Data
public class OneSecMailMessagePOJO {

    int id;
    String from;
    String subject;
    String date;
    ArrayList<Object> attachments;
    String body;
    String textBody;
    String htmlBody;

}
