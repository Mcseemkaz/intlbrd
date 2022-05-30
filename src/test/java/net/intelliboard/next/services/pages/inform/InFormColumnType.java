package net.intelliboard.next.services.pages.inform;

public enum InFormColumnType {

    TEXT("Text"),
    NUMBER("Number"),
    DATE("Date"),
    ENUM("Enum"),
    MEDIA_LINK("Media Link"),
    EMAIL("Email"),
    URL("Url"),
    LOCATION("Location"),
    IP("Ip"),
    INT("Int"),
    BOOLEAN("Boolean");

    String value;

    InFormColumnType(String value) {
        this.value = value;
    }
}
