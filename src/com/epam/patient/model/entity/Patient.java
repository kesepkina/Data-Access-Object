package com.epam.patient.model.entity;

import com.epam.patient.exception.ValidationException;
import com.epam.patient.util.IdGenerator;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.Objects;

public class Patient {

    private static final IdGenerator idGenerator = new IdGenerator();

    private final int id = idGenerator.generateIntId();
    private String lastName;
    private String firstName;
    private String patronymic;
    private String address;
    private String phoneNumber;
    private int numberOfMedicalRecord;
    private EnumSet<Diagnosis> diagnoses = EnumSet.noneOf(Diagnosis.class);


    public Patient(String lastName, String firstName, String patronymic, String address,
                   String phoneNumber, int numberOfMedicalRecord, Diagnosis... diagnoses) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.numberOfMedicalRecord = numberOfMedicalRecord;
        this.diagnoses.addAll(Arrays.asList(diagnoses));
    }

    public Patient(String lastName, String firstName, String patronymic, String address,
                   String phoneNumber, int numberOfMedicalRecord) throws ValidationException {
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.numberOfMedicalRecord = numberOfMedicalRecord;
    }

    public int getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getNumberOfMedicalRecord() {
        return numberOfMedicalRecord;
    }

    public void setNumberOfMedicalRecord(int numberOfMedicalRecord) {
        this.numberOfMedicalRecord = numberOfMedicalRecord;
    }

    public EnumSet<Diagnosis> getDiagnoses() {
        return diagnoses;
    }

    public void setDiagnoses(EnumSet<Diagnosis> diagnoses) {
        this.diagnoses = diagnoses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return id == patient.id &&
                numberOfMedicalRecord == patient.numberOfMedicalRecord &&
                Objects.equals(lastName, patient.lastName) &&
                Objects.equals(firstName, patient.firstName) &&
                Objects.equals(patronymic, patient.patronymic) &&
                Objects.equals(address, patient.address) &&
                Objects.equals(phoneNumber, patient.phoneNumber) &&
                Objects.equals(diagnoses, patient.diagnoses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lastName, firstName, patronymic, address, phoneNumber, numberOfMedicalRecord, diagnoses);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Patient{");
        sb.append("id=").append(id);
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", patronymic='").append(patronymic).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append(", phoneNumber='").append(phoneNumber).append('\'');
        sb.append(", numberOfMedicalRecord=").append(numberOfMedicalRecord);
        sb.append(", diagnoses=").append(diagnoses);
        sb.append('}');
        return sb.toString();
    }
}
