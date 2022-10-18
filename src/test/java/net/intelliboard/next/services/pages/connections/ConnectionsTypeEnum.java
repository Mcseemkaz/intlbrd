package net.intelliboard.next.services.pages.connections;

public enum ConnectionsTypeEnum {

    CANVAS("Canvas"),
    MOODLE("Moodle"),
    D2L("D2L"),
    SAKAI("Sakai"),
    BLACKBOARD("BlackBoard"),
    TOTARA("Totara");

    public final String value;

    ConnectionsTypeEnum(String value) {
        this.value = value;
    }
}
