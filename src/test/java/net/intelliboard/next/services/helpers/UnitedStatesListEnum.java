package net.intelliboard.next.services.helpers;

import java.util.Random;

public enum UnitedStatesListEnum {

    CA("California", "CA"),
    AL("Alabama", "AL");

    public String fullName;
    public String shortName;

    private static final Random PRNG = new Random();

    public static UnitedStatesListEnum randomState() {
        UnitedStatesListEnum[] states = values();
        return states[PRNG.nextInt(states.length)];
    }

    UnitedStatesListEnum(String fullName, String shortName) {
        this.fullName = fullName;
        this.shortName = shortName;
    }
}

