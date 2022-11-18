package net.intelliboard.next.services.pages.library;

public enum LibraryOrderTypeEnum {

    NAME("Name"),
    CREATION_DATE("Creation Date"),
    LAST_UPDATE("Last Update"),
    MOST_VISITED("Most Visited");

    public String value;

    LibraryOrderTypeEnum(String value) {
        this.value = value;
    }
}
