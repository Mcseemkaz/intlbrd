package net.intelliboard.next.services.pages.connections;

public enum ConnectionIntegrationTypeEnum {

    ELLUCIAN_COLLEAGUE("Ellucian Colleague"),
    MONGOOSE_CADENCE("Mongoose Cadence"),
    QWICKLY("Qwickly Attendance");

    public String value;

    ConnectionIntegrationTypeEnum(String value) {
        this.value = value;
    }
}
