package net.intelliboard.next.services.pages.inform;

public enum InFormEntriesManagingModes {

    EVERYONE_CAN_MAGE_ENTRY("Everyone Can Manage An Entry"),
    IB_USERS_CAN_MANAGE_ENTRIES("IB Users Can Manage Their Entries"),
    NOBODY_CAN_MANAGE_ENTRIES("Nobody Can Manage An Entry");

    String value;

    InFormEntriesManagingModes(String value) {
        this.value = value;
    }
}
