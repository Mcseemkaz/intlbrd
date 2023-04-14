package net.intelliboard.next.services.pages.connections;

public enum ConnectionIntegrationTypeEnum {

    ELLUCIAN_COLLEAGUE("Ellucian Colleague"),
    ELLUCIAN_BANNER("Ellucian Banner"),
    MONGOOSE_CADENCE("Mongoose Cadence"),
    QWICKLY("Qwickly Attendance"),
    BLACK_BOARD_COLLABORATE("Blackboard Collaborate"),
    HUBSPOT("Hubspot");

    public final String value;

    ConnectionIntegrationTypeEnum(String value) {
        this.value = value;
    }
}
