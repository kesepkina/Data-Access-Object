package com.epam.patient.model.dao.impl;

import com.epam.patient.exception.DaoException;
import com.epam.patient.model.dao.PatientDao;
import com.epam.patient.model.entity.Diagnosis;
import com.epam.patient.model.entity.Patient;
import com.epam.patient.model.dao.storage.Warehouse;

import java.util.*;

public class PatientDaoImpl implements PatientDao {

    @Override
    public void addPatient(Patient patient) throws DaoException {
        List<Patient> patients = Warehouse.getInstance().getPatients();

        if (contains(patient)) {
            throw new DaoException("patient already exists");
        }
        patients.add(patient);
        Warehouse.getInstance().setPatients(patients);
    }

    @Override
    public List<Patient> findAll() {
        return Warehouse.getInstance().getPatients();
    }

    @Override
    public Optional<Patient> findPatientById(int id) {
        List<Patient> allPatients = Warehouse.getInstance().getPatients();
        Patient patient = null;
        int i = 0;
        while (i < allPatients.size()) {
            if (allPatients.get(i).getId() == id) {
                patient = allPatients.get(i);
                break;
            }
            i++;
        }
        return Optional.ofNullable(patient);
    }

    @Override
    public List<Patient> findByDiagnosis(Diagnosis diagnosis){
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
                                                        int lastNumberOfInterval){
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
        boolean contains = true;

        List<Patient> patients = Warehouse.getInstance().getPatients();
        int i = 0;
        while (i < patients.size()){
            if (patients.get(i).equals(patient)) {
                contains = false;
                break;
            }
        }
        return contains;
    }

    @Override
    public void delete(Patient patient) throws DaoException {
        List<Patient> patients = Warehouse.getInstance().getPatients();

        if (!contains(patient)) {
            throw new DaoException("patient doesn't exist");
        }
        patients.remove(patient);
        Warehouse.getInstance().setPatients(patients);
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
