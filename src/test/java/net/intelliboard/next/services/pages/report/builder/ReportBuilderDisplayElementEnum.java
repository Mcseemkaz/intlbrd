package net.intelliboard.next.services.pages.report.builder;

public enum ReportBuilderDisplayElementEnum {

    USERS_CATEGORY_USERS_ID("Users Id"),
    USERS_CATEGORY_USERS_USER_NAME("Users User Name"),
    USERS_CATEGORY_FULL_NAME("Full Name"),
    USERS_CATEGORY_EMAIL("Email"),
    USERS_CATEGORY_USERS_LOGINS_COUNT("Users Logins Count");

    public final String value;

    ReportBuilderDisplayElementEnum(String value) {
        this.value = value;
    }
}
