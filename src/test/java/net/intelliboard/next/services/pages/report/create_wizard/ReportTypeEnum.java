package net.intelliboard.next.services.pages.report.create_wizard;

public enum ReportTypeEnum {

    TABLE("table"),
    BAR_CHART("bar"),
    PIE_CHART("pie");

    final String value;

    ReportTypeEnum(String value) {
        this.value = value;
    }
}
