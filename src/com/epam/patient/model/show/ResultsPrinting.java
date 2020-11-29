package com.epam.patient.model.show;

import com.epam.patient.model.entity.Patient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ResultsPrinting {
    private static final Logger logger = LogManager.getLogger();

    public void printFoundById(int id, Patient patient) {
        System.out.println("Info about patient with id = " + id + ":");
        System.out.format("%4s%35s%25s%15s%17s%20s%n", "ID", "Name", "Address", "Phone", "Medical record", "Diagnoses");
        printPatient(patient);
        System.out.println();
    }

    public void printAllPatients(List<Patient> patients) {
        System.out.println("All patients in hospital:");
        printListOfPatients(patients);
    }

    public void printFoundByDiagnosis(String diagnosis, List<Patient> patients) {
        System.out.println("All patients with diagnosis \"" + diagnosis + "\":");
        printListOfPatients(patients);
    }

    public void printFoundByMedicalRecordsInInterval(int firstNumberOfInterval,
                                                     int lastNumberOfInterval, List<Patient> patients) {
        System.out.println("All patients with medical record's number between " + firstNumberOfInterval
                + " and " + lastNumberOfInterval + ":");
        printListOfPatients(patients);
    }

    private void printPatient(Patient patient) {
        System.out.format("%4d%35s%25s%15s%17d%20s%n", patient.getId(), patient.getLastName() + " " + patient.getFirstName() +
                        " " + patient.getPatronymic(), patient.getAddress(), patient.getPhoneNumber(), patient.getNumberOfMedicalRecord(),
                patient.getDiagnoses().toString());
    }

    private void printListOfPatients(List<Patient> patients) {
        System.out.format("%4s%35s%25s%15s%17s%20s%n", "ID", "NAME", "ADDRESS", "PHONE", "MEDICAL RECORD", "DIAGNOSES");
        for (Patient patient : patients) {
            printPatient(patient);
        }
        System.out.println();
    }

    public void printNumberOfPatientsWithoutDiagnosis(int counted) {
        System.out.println("There are " + counted + " patients without diagnosis.");
    }
}
