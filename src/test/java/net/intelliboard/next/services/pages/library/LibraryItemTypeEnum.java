package net.intelliboard.next.services.pages.library;

public enum LibraryItemTypeEnum {

    DASHBOARDS("Dashboards"),
    REPORTS("Reports"),
    INFORM("Form");

    public String value;

    LibraryItemTypeEnum(String value) {
        this.value = value;
    }
}
