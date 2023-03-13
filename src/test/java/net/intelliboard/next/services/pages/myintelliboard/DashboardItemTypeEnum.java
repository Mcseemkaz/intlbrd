package net.intelliboard.next.services.pages.myintelliboard;

public enum DashboardItemTypeEnum {

    DASHBOARDS("Dashboards"),
    REPORTS("Reports"),
    INFORM("In Form");

    public String value;

    DashboardItemTypeEnum(String value) {
        this.value = value;
    }
}
