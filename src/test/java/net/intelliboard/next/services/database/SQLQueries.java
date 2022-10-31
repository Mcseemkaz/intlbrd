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


    public static final String[] ROWS_TEST_ONE_LIST = {
            "activities",
            "activity_completion",
            "activity_types",
            "assignment_activities",
            "assignment_submissions",
            "browsers",
            "categories",
            "courses",
            "enrolment_details",
            "enrolments",
            "forum_activities",
            "forum_discussions",
            "forum_posts",
            "grade_letters",
            "grade_objects",
            "grade_objects_results",
            "login_history",
            "login_stats",
            "lti_activities",
            "operating_systems",
            "org_unit_courses",
            "org_units",
            "quiz_activities",
            "quiz_attempts",
            "quiz_question_answers",
            "quiz_question_relations",
            "quiz_questions",
            "role_assignments",
            "roles",
            "sessions",
            "terms",
            "user_tracking_by_day",
            "user_tracking_total",
            "users"
    };

    public static final String[] ROWS_TEST_THREE_LIST_ONE = {
            "categories",
            "courses",
            "role_assignments",
            "users"
    };

    public static final String[] ROWS_TEST_THREE_LIST_TWO = {
            "incontact_categories",
            "incontact_courses",
            "incontact_role_assignments",
            "incontact_users"
    };
}
