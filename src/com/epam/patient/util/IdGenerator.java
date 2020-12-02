package com.epam.patient.util;

public class IdGenerator {
    private int counter;

    public IdGenerator() {
        this.counter = -1;
    }

    public int generateIntId() {
        return ++counter;
    }
}
