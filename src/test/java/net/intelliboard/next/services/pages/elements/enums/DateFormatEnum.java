package net.intelliboard.next.services.pages.elements.enums;

public enum DateFormatEnum {

    DD_MM_YYYY("DD/MM/YYYY"),
    YYYY_MM_DD("YYYY-MM-DD");

    public final String value;

    DateFormatEnum(String value) {
        this.value = value;
    }
}
