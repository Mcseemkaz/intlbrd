package net.intelliboard.next.services.pages.report.builder;

public enum ReportConnectionTypeAvailabilityEnum {

    CANVAS("Canvas"),
    D2L("D2L"),
    SAKAI("Sakai"),
    TOTARA("Totara");

    final String value;

    ReportConnectionTypeAvailabilityEnum(String value) {
        this.value = value;
    }
}
