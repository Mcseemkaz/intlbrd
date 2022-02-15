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

    // Sign Up Page
    public static final String SIGNUP_PAGE = MAIN_URL + "/signup";
    public static final String SIGNUP_INVITATION_PAGE = SIGNUP_PAGE + "/invite";

    // Connection Integration Page
    public static final String CREATE_CONNECTION = MAIN_URL + "/connections/create";
    public static final String CREATE_MOODLE_CONNECTION = CREATE_CONNECTION + "/1";
    public static final String CREATE_CANVAS_CONNECTION = CREATE_CONNECTION + "/2";

    // Login Canvas Page
    public static final String LOGIN_CANVAS_PAGE = CANVAS_MAIN_URL + "/login/canvas";

    // LoginOauth2 Confirm Page  /login/oauth2/confirm
    public static final String LOGIN_OAUTH2_CONFIRM_PAGE = CANVAS_MAIN_URL + "/login/oauth2/confirm";
}

