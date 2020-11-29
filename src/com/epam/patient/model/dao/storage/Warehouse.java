package com.epam.patient.model.dao.storage;

import com.epam.patient.model.entity.Patient;

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
        List<Patient> copy = patients;
        return copy;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }
}
