package net.intelliboard.next.services.helpers;

import com.github.javafaker.Faker;

public class DataGenerator {

    public static String getRandomString(){
      return Faker.instance().regexify("[a-z]{6}");
    }

    public static String getRandomValidEmail(){
        return Faker.instance().internet().emailAddress();
    }
}
