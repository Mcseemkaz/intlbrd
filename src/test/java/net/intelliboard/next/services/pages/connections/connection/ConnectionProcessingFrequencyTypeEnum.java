package net.intelliboard.next.services.pages.connections.connection;

public enum ConnectionProcessingFrequencyTypeEnum {

    DAILY("Daily"),
    WEEKLY("Weekly");

    public String value;

    ConnectionProcessingFrequencyTypeEnum(String value) {
        this.value = value;
    }
}
