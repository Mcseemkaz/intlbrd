package net.intelliboard.next.services.api.pojo.mailtrap;

import lombok.Data;

import java.util.ArrayList;

@Data
public class MailTrapMessageBlacklistsReportInfoPOJO {

    String result;
    String domain;
    String ip;
    ArrayList<MailTrapMessageReportPOJO> report;
}
