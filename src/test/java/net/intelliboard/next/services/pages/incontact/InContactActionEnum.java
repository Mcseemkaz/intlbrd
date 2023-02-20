package net.intelliboard.next.services.pages.incontact;

public enum InContactActionEnum {

    ADD_NEW_MENU("Add New"),
    SEE_INFORMATION_MENU("See"),
    EDIT_MENU("Edit");

    public String value;

    InContactActionEnum(String value) {
        this.value = value;
    }
}
