package net.intelliboard.next.services.pages.IBUsers;

public enum CreateIBUsersFormFieldTypeEnum {

    FIRST_NAME("//input[@id='first_name']"),
    LAST_NAME("//input[@id='last_name']"),
    EMAIL("//input[@id='email']"),
    PASSWORD("//input[@id='password']"),
    JOB_TITLE("//input[@id='job_title']");

    String value;

    CreateIBUsersFormFieldTypeEnum(String value) {
        this.value = value;
    }
}
