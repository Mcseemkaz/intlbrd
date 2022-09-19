package net.intelliboard.next.services.pages.connections.connection;

public enum ConnectionTabsEnum {

    CONNECTION_SETTINGS("Connection Settings"),
    FILTERS_SETTINGS("Filters Settings"),
    ADVANCED_SETTINGS("Advanced Settings");

    public String value;

    ConnectionTabsEnum(String value){
        this.value = value;
    }
}
