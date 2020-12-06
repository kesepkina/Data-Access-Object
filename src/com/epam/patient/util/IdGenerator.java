package com.epam.patient.util;

public class IdGenerator {
    private static int counter = -1;

    private IdGenerator() {}

    public static int generateIntId() {
        return ++counter;
    }
}
