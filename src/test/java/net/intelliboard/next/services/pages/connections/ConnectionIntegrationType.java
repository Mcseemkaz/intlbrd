package net.intelliboard.next.services.pages.connections;

public enum ConnectionIntegrationType {

    ELLUCIAN_COLLEAGUE("Ellucian Colleague"),
    MONGOOSE_CADENCE("Mongoose Cadence");

    String value;

    ConnectionIntegrationType(String value) {
        this.value = value;
    }
}
