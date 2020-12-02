package com.epam.patient.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatientValidator {

    private static final Pattern PHONE_NUMBER = Pattern.compile("\\+?\\d+");
    private static final Pattern MEDICAL_RECORD = Pattern.compile("\\d{8}");
    private static final Pattern NAME = Pattern.compile("[a-zA-Z-]");
    private static final Pattern ADDRESS = Pattern.compile("^[a-zA-Z-\\s.]+[\\s,\\-\\d]+");

    private PatientValidator() {}

    public static boolean checkPhoneNumber(String phoneNumber) {
        Matcher matcher = PHONE_NUMBER.matcher(phoneNumber);
        return matcher.matches();
    }

    public static boolean checkNumberOfMedicalRecord(int numberOfMedicalRecord) {
        Matcher matcher = MEDICAL_RECORD.matcher(String.valueOf(numberOfMedicalRecord));
        return matcher.matches();
    }

    public static boolean checkName(String name) {
        Matcher matcher = NAME.matcher(name);
        return matcher.matches();
    }

    public static boolean checkAddress(String address) {
        Matcher matcher = ADDRESS.matcher(address);
        return matcher.matches();
    }
}
