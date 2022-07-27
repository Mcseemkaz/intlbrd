package net.intelliboard.next.services.pages.report.builder;

public enum ReportSettingsColorsEnum {

    PINK("#E182B4", "background-color: rgb(225, 130, 180);"),
    BLUE("#73ADF7", "background-color: rgb(115, 173, 247);"),
    GREEN("#47B973", "background-color: rgb(71, 185, 115);"),
    YELLOW("#FFD447", "");

    public final String value;
    public final String rgbColor;

    ReportSettingsColorsEnum(String value, String rgbColor) {
        this.value = value;
        this.rgbColor = rgbColor;
    }
}
