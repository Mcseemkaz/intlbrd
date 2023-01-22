package net.intelliboard.next.services.pages.report;

public enum ReportExportFormat {

    XLS("XLS"),
    CSV("CSV");

    public final String value;

    ReportExportFormat(String value) {
        this.value = value;
    }
}
