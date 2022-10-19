package net.intelliboard.next.services;

public enum ProjectFilesEnum {

    IBUSERS_IMPORT_CSV("src/test/resources/ibusers_csv_files/ibuser_csv_file.csv");

    public final String path;

    ProjectFilesEnum(String path) {
        this.path = path;
    }
}
