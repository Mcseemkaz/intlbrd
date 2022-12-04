package net.intelliboard.next.services.pages.connections.connection.qwickly;

public enum QwicklyProcessingFreqencyTypeEnum {

    DAILY("Daily"),
    WEEKLY("Weekly");

    public String value;

    QwicklyProcessingFreqencyTypeEnum(String value) {
        this.value = value;
    }
}
