package net.intelliboard.next.services;

import java.io.IOException;

public class IBNextURLs {

    public static String MAIN_URL;
    public static String CANVAS_MAIN_URL;
    static {
        try {
            MAIN_URL = new PropertiesGetValue().getPropertyValue("base_url");
            CANVAS_MAIN_URL = new PropertiesGetValue().getPropertyValue("canvas_base_url");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Login Page
    public static final String LOGIN_PAGE = MAIN_URL + "/login";

    //Users
    public static final String USERS_PAGE = MAIN_URL + "/users";
    public static final String USERS_CREATE_PAGE = USERS_PAGE + "/create";
    public static final String USERS_SYNC_PAGE = USERS_PAGE + "/sync";

    // Sign Up Page
    public static final String SIGNUP_PAGE = MAIN_URL + "/signup";
    public static final String SIGNUP_INVITATION_PAGE = SIGNUP_PAGE + "/invite";

    //All Connections
    public static final String ALL_CONNECTIONS = MAIN_URL + "/connections";

    // All Connections Integrations
    public static final String ALL_INTEGRATIONS = ALL_CONNECTIONS + "/integrate";

    // Connection Integration Page
    public static final String CREATE_CONNECTION = ALL_CONNECTIONS + "/create";
    public static final String CREATE_MOODLE_CONNECTION = CREATE_CONNECTION + "/1";
    public static final String CREATE_CANVAS_CONNECTION = CREATE_CONNECTION + "/2";
    public static final String CREATE_BLACKBOARD_CONNECTION = CREATE_CONNECTION + "/3";
    public static final String CREATE_D2L_CONNECTION = CREATE_CONNECTION + "/4";
    public static final String CREATE_ZOOM_CONNECTION = CREATE_CONNECTION + "/9";
    public static final String CREATE_ELLUCIAN_BANNER_CONNECTION = ALL_INTEGRATIONS + "/11";
    public static final String CREATE_ELLUCIAN_COLLEAGUE_CONNECTION = ALL_INTEGRATIONS + "/12";
    public static final String CREATE_ILIAS_CONNECTION = CREATE_CONNECTION + "/16";
    public static final String CREATE_SAKAI_CONNECTION = CREATE_CONNECTION + "/18";
    public static final String CREATE_MWP_MOODLE_CONNECTION = CREATE_CONNECTION + "/20";
    public static final String CREATE_TOTARA_CONNECTION = CREATE_CONNECTION + "/21";

    // Login Canvas Page
    public static final String LOGIN_CANVAS_PAGE = CANVAS_MAIN_URL + "/login/canvas";

    // LoginOauth2 Confirm Page  /login/oauth2/confirm
    public static final String LOGIN_OAUTH2_CONFIRM_PAGE = CANVAS_MAIN_URL + "/login/oauth2/confirm";

    //My Intelliboard Page
    public static final String MY_INTELLIBOARD_PAGE = MAIN_URL + "/data-sets";

    //In-Form Page
    public static final String INFORM_LIST_PAGE = MAIN_URL + "/in-form";
}

