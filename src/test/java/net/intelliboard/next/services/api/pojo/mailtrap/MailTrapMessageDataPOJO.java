package net.intelliboard.next.services.api.pojo.mailtrap;

import lombok.Data;

import java.util.ArrayList;

@Data
public class MailTrapMessageDataPOJO {

    public String mail_from_addr;
    public ArrayList<String> rcpt_to_addrs;
    public String client_ip;

}