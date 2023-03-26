package net.intelliboard.next.services.helpers;

import com.github.javafaker.Faker;

public class DataGenerator {

    public static String getRandomString() {
        return Faker.instance().regexify("[A-Z]{8}");
    }

    public static String getRandomValidEmail() {
        return Faker.instance().internet().emailAddress();
    }

    public static String getRandomNumber() {
        return String.valueOf(Faker.instance().number().randomNumber(10, true));
    }

    public static String getRandomValidPassword() {
        return Faker.instance().regexify("[A-Z][a-z][0-9][Q@#$%^]{10}");
    }

    public static UnitedStatesListEnum getRandomUSState(){
        return UnitedStatesListEnum.randomState();
    }

    public static char[] convertStringToArray(String string) {
        return string.toCharArray();
    }
}
