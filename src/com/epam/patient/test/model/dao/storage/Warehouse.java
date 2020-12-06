package com.epam.patient.test.model.dao.storage;

import com.epam.patient.test.model.entity.Patient;

import java.util.ArrayList;
import java.util.List;

public class Warehouse {

    private static final Warehouse instance = new Warehouse();
    private List<Patient> patients;

    private Warehouse() {
        this.patients = new ArrayList<>();
    }

    public static Warehouse getInstance() {
        return instance;
    }

    public List<Patient> getPatients() {
        return new ArrayList<>(patients);
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    public void add(Patient patient) {
        patients.add(patient);
    }

    public void remove(Patient patient) {
        patients.remove(patient);
    }

    public void remove(int id) {
        patients.remove(id);
    }

    public Patient getPatient(int id) {
        return patients.get(id);
    }

}
