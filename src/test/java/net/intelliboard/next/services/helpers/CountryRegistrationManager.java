package net.intelliboard.next.services.helpers;

public class CountryRegistrationManager {

    public static String getCountryByEnvironment() {
        String country;

        switch (System.getProperty("TestEnvironment")) {
            case ("prod-eu"):
                country = "France";
                break;
            case ("prod-ca"):
                country = "Canada";
                break;
            case ("prod-au"):
                country = "Australia";
                break;
            default:
                country = "United States";
        }
        return country;
    }
}
