package com.epam.patient.model.service;

import com.epam.patient.model.dao.impl.PatientDaoImpl;
import com.epam.patient.model.entity.Patient;
import com.epam.patient.model.dao.storage.Warehouse;

import java.util.List;


public class PatientService {

    public int countPatientsWithoutDiagnosis() {
        PatientDaoImpl patientDao = new PatientDaoImpl();
        int count = 0;
        List<Patient> allPatients = patientDao.findAll();
        for (Patient patient : allPatients) {
            if (patient.getDiagnoses().isEmpty()) {
                count++;
            }
        }
        return count;
    }


}
