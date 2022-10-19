package net.intelliboard.next.services.pages.connections;

public enum ConnectionsTypeEnum {

    CANVAS("Canvas", "Automation Canvans"),
    MOODLE("Moodle", "Automation Moodle"),
    D2L("D2L", "Automation D2L"),
    SAKAI("Sakai", "Automation Sakai"),
    BLACKBOARD("BlackBoard", "Automation BlackBoard"),
    TOTARA("Totara", "Automation Totara");

    public final String value;
    public final String defaultName;

    ConnectionsTypeEnum(String value, String defaultName) {
        this.value = value;
        this.defaultName = defaultName;
    }
}
