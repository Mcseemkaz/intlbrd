package net.intelliboard.next.services;

import java.io.IOException;

public class IBNextURLs {

    public static String MAIN_URL;

    static {
        try {
            MAIN_URL = new PropertiesGetValue().getPropertyValue("base_url");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Login Page
    public static final String LOGIN_PAGE = MAIN_URL + "/login";

    //Users
    public static final String USERS_PAGE = MAIN_URL + "/users";
    public static final String USERS_CREATE_PAGE = USERS_PAGE + "/create";

    // Sign Up Page
    public static final String SIGNUP_PAGE = MAIN_URL + "/signup";
    public static final String SIGNUP_INVITATION_PAGE = SIGNUP_PAGE + "/invite";
    public static final String CREATE_MOODLE_CONNECTION = MAIN_URL + "/connections/create/1";
}

