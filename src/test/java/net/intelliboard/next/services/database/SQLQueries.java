package net.intelliboard.next.services.database;

public class SQLQueries {

    public static final String VACUUM_QUERY = "vacuum full verbose;";
    public static final String ANALYZE_QUERY = "analyze verbose;";

    public static final String GENERAL_REPORT_QUERY =
                    "select schemaname as schema_name,\n" +
                    " relname as table_name,\n" +
                    " n_live_tup as record_number\n" +
                    " from pg_stat_user_tables\n" +
                    " order by schemaname, relname;";


}
