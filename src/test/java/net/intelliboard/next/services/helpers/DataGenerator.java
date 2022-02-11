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
        return Faker.instance().number().digits(8);
    }

    public static String getRandomValidPassword() {
        return Faker.instance().regexify("[A-Z][a-z][0-9][Q@#$%^]{10}");
//        return Faker.instance().regexify("[A-Za-z\\d@$!%*?&]{10}");
    }
}
