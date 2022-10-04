package net.intelliboard.next.services.pages.auditlogs;

public enum UserProfileAuditTableColumnEnum {

    TIME("Time", "1"),
    USER("User", "2"),
    EVENT_TYPE_PAGE("Event Type Page", "3");

    public String value;
    public String numberColumn;

    UserProfileAuditTableColumnEnum(String value, String numberColumn) {
        this.value = value;
        this.numberColumn = numberColumn;
    }
}
