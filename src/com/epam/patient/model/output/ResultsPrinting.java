package com.epam.patient.model.output;

import com.epam.patient.model.entity.Patient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ResultsPrinting {

    private static final Logger logger = LogManager.getLogger();

    public void printPatient(Patient patient) {
        String patientInfo = String.format("%4d%35s%25s%15s%17d%20s%n", patient.getId(), patient.getLastName() + " " + patient.getFirstName() +
                        " " + patient.getPatronymic(), patient.getAddress(), patient.getPhoneNumber(), patient.getNumberOfMedicalRecord(),
                patient.getDiagnoses().toString());
        logger.info(patientInfo);
    }

    public void printListOfPatients(List<Patient> patients) {
        StringBuilder patientInfo = new StringBuilder();
        includePatientsFromList(patientInfo, patients);
        logger.info(patientInfo);
    }

    public void printListOfPatients(List<Patient> patients, String info) {
        StringBuilder patientInfo = new StringBuilder();
        patientInfo.append("\n").append(info).append("\n");
        includePatientsFromList(patientInfo, patients);
        logger.info(patientInfo);
    }

    private void includePatientsFromList(StringBuilder patientInfo, List<Patient> patients) {
        patientInfo.append(String.format("%n%4s%35s%25s%15s%17s%20s%n", "ID", "NAME", "ADDRESS", "PHONE", "MEDICAL RECORD", "DIAGNOSES"));
        for (Patient patient : patients) {
            patientInfo.append(String.format("%4d%35s%25s%15s%17d%20s%n", patient.getId(), patient.getLastName() + " " + patient.getFirstName() +
                            " " + patient.getPatronymic(), patient.getAddress(), patient.getPhoneNumber(), patient.getNumberOfMedicalRecord(),
                    patient.getDiagnoses().toString()));
        }
    }
}
