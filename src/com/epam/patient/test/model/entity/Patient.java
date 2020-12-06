package com.epam.patient.test.model.entity;

import com.epam.patient.exception.ValidationException;
import com.epam.patient.util.IdGenerator;

import java.util.Arrays;
import java.util.EnumSet;

public class Patient {

    private final int id = IdGenerator.generateIntId();
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

        if (id != patient.id) return false;
        if (numberOfMedicalRecord != patient.numberOfMedicalRecord) return false;
        if (lastName != null ? !lastName.equals(patient.lastName) : patient.lastName != null) return false;
        if (firstName != null ? !firstName.equals(patient.firstName) : patient.firstName != null) return false;
        if (patronymic != null ? !patronymic.equals(patient.patronymic) : patient.patronymic != null) return false;
        if (address != null ? !address.equals(patient.address) : patient.address != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(patient.phoneNumber) : patient.phoneNumber != null) return false;
        return diagnoses != null ? diagnoses.equals(patient.diagnoses) : patient.diagnoses == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (patronymic != null ? patronymic.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + numberOfMedicalRecord;
        result = 31 * result + (diagnoses != null ? diagnoses.hashCode() : 0);
        return result;
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
