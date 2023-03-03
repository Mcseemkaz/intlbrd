package net.intelliboard.next.services.pages.incontact;

public enum InContactViewOptionEnum {

    LIST("list"),
    CALENDAR("calendar-clear"),
    ALBUMS("albums");

    public final String value;

    InContactViewOptionEnum(String value) {
        this.value = value;
    }
}
