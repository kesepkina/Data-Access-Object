package com.epam.patient.test.model.dao.impl;

import com.epam.patient.exception.DaoException;
import com.epam.patient.test.model.dao.PatientDao;
import com.epam.patient.test.model.entity.Diagnosis;
import com.epam.patient.test.model.entity.Patient;
import com.epam.patient.test.model.dao.storage.Warehouse;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

public class PatientDaoImpl implements PatientDao {

    @Override
    public void addPatient(Patient patient) throws DaoException {

        if (contains(patient)) {
            throw new DaoException("patient already exists");
        }

        Warehouse.getInstance().add(patient);
    }

    @Override
    public List<Patient> findAll() {
        return Warehouse.getInstance().getPatients();
    }

    @Override
    public Optional<Patient> findPatientById(int id) {
        Patient patient = Warehouse.getInstance().getPatient(id);
        return Optional.ofNullable(patient);
    }

    @Override
    public List<Patient> findByDiagnosis(Diagnosis diagnosis) {
        List<Patient> desiredPatients = new ArrayList<>();
        List<Patient> allPatients = Warehouse.getInstance().getPatients();
        EnumSet<Diagnosis> patientDiagnoses;
        for (Patient patient : allPatients) {
            patientDiagnoses = patient.getDiagnoses();
            if (!patientDiagnoses.isEmpty() && patientDiagnoses.contains(diagnosis)) {
                desiredPatients.add(patient);
            }
        }
        return desiredPatients;
    }

    @Override
    public List<Patient> findByMedicalRecordsInInterval(int firstNumberOfInterval,
                                                        int lastNumberOfInterval) {
        List<Patient> desiredPatients = new ArrayList<>();
        List<Patient> allPatients = Warehouse.getInstance().getPatients();
        int numberOfMedicalRecord;
        for (Patient patient : allPatients) {
            numberOfMedicalRecord = patient.getNumberOfMedicalRecord();
            if (numberOfMedicalRecord > firstNumberOfInterval &&
                    numberOfMedicalRecord < lastNumberOfInterval) {
                desiredPatients.add(patient);
            }
        }
        return desiredPatients;
    }

    @Override
    public boolean contains(Patient patient) {
        boolean contains = false;

        List<Patient> patients = Warehouse.getInstance().getPatients();
        int i = 0;
        while (i < patients.size()) {
            if (patients.get(i).equals(patient)) {
                contains = true;
                break;
            }
            i++;
        }
        return contains;
    }

    @Override
    public void delete(Patient patient) throws DaoException {
        if (!contains(patient)) {
            throw new DaoException("patient doesn't exist");
        }

        Warehouse.getInstance().remove(patient);
    }

    @Override
    public void update(Patient patient, EnumSet<Diagnosis> diagnoses) throws DaoException {
        List<Patient> patients = Warehouse.getInstance().getPatients();

        if (!contains(patient)) {
            throw new DaoException("patient doesn't exist");
        }

        patients.get(patient.getId()).setDiagnoses(diagnoses);
        Warehouse.getInstance().setPatients(patients);
    }

}
