package net.intelliboard.next.services;

public enum ProjectFilesEnum {

    IBUSERS_IMPORT_CSV("src/test/resources/ibusers_csv_files/ibuser_csv_file.csv"),
    INCONTACT_IMPORT_CSV("src/test/resources/ibusers_csv_files/incontact_upload_csv_file.csv"),
    INFORM_IMPORT_CSV("src/test/resources/inform/inform_import_csv.csv"),
    INFORM_IMPORT_XLS("src/test/resources/inform/inform_import_xls.xlsx");


    public final String path;

    ProjectFilesEnum(String path) {
        this.path = path;
    }
}
