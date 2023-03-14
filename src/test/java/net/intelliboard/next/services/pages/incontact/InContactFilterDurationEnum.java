package net.intelliboard.next.services.pages.incontact;

public enum InContactFilterDurationEnum {

    LAST_30_DAYS("Last 30 Days"),
    LAST_90_DAYS("Last 90 Days"),
    CUSTOM("Custom");

    public String value;

    InContactFilterDurationEnum(String value) {
        this.value = value;
    }
}
