package net.intelliboard.next.services.api.connectors.onesecmail;

public enum OneSecMailActionsEnum {

    GENERATE_RANDOM_BOX("genRandomMailbox"),
    GET_MESSAGES("getMessages"),
    READ_MESSAGE("readMessage"),
    GET_DOMAIN_LIST("getDomainList");

    public String value;

    OneSecMailActionsEnum(String value) {
        this.value = value;
    }
}
