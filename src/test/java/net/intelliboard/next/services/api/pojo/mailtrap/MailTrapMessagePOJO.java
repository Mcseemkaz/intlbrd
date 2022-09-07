package net.intelliboard.next.services.api.pojo.mailtrap;

import lombok.Data;

@Data
public class MailTrapMessagePOJO {

    public long id;
    public int inbox_id;
    public String subject;
    public String sent_at;
    public String from_email;
    public String from_name;
    public String to_email;
    public String to_name;
    public int email_size;
    public boolean is_read;
    public String created_at;
    public String updated_at;
    public int html_body_size;
    public int text_body_size;
    public String human_size;
    public String html_path;
    public String txt_path;
    public String raw_path;
    public String download_path;
    public String html_source_path;
    public MailTrapMessageBlacklistsReportInfoPOJO blacklists_report_info;
    public MailTrapMessageSMTPInformationPOJO smtp_information;

}
