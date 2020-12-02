package com.epam.patient.model.reader;

import com.epam.patient.exception.DaoException;
import com.epam.patient.exception.FillingException;
import com.epam.patient.exception.ValidationException;
import com.epam.patient.model.dao.impl.PatientDaoImpl;
import com.epam.patient.model.entity.Diagnosis;
import com.epam.patient.model.entity.Patient;
import com.epam.patient.util.PatientValidator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class WarehouseFilling {

    private static final int INCLUDING_DIAGNOSES = 7;
    private static final String FILE_PATH = "res/data/Patients.txt";
    private static final String DELIMITER = "\n";
    private static final String SEPARATOR1 = " \\| ";
    private static final String SEPARATOR2 = ", ";
    private final PatientDaoImpl patientDao = new PatientDaoImpl();

    public void fillFromTxtFile() throws FillingException {
        Path path = Paths.get(FILE_PATH);
        String[] patientInfo;
        String[] diagnosesInfo;
        Patient newPatient;
        Diagnosis[] diagnoses;
        String lastName;
        String firstName;
        String patronymic;
        String address;
        String phoneNumber;
        int numberOfMedicalRecord;

        try (Stream<String> stream = Files.lines(path)) {
            String dataFromFile = stream.toString();
            String[] patients = dataFromFile.split(DELIMITER);

            for (String patient : patients) {
                patientInfo = patient.split(SEPARATOR1);
                lastName = patientInfo[0];
                firstName = patientInfo[1];
                patronymic = patientInfo[2];
                address = patientInfo[3];
                phoneNumber = patientInfo[4];
                numberOfMedicalRecord = Integer.parseInt(patientInfo[5]);

                if (!PatientValidator.checkName(lastName)) {
                    throw new ValidationException("Last name isn't correct.");
                }
                if (!PatientValidator.checkName(firstName)) {
                    throw new ValidationException("First name isn't correct.");
                }
                if (!PatientValidator.checkName(patronymic)) {
                    throw new ValidationException("Patronymic isn't correct.");
                }
                if (!PatientValidator.checkPhoneNumber(phoneNumber)) {
                    throw new ValidationException("Phone number isn't correct.");
                }
                if (!PatientValidator.checkNumberOfMedicalRecord(numberOfMedicalRecord)) {
                    throw new ValidationException("Number of medical record isn't correct.");
                }
                if (!PatientValidator.checkAddress(address)) {
                    throw new ValidationException("Address isn't correct.");
                }

                if (patientInfo.length == INCLUDING_DIAGNOSES) {
                    diagnosesInfo = patientInfo[6].split(SEPARATOR2);
                    diagnoses = new Diagnosis[diagnosesInfo.length];
                    for (int i = 0; i < diagnoses.length; i++) {
                        diagnoses[i] = Diagnosis.valueOf(diagnosesInfo[i]);
                    }
                    newPatient = new Patient(lastName, firstName, patronymic, address, phoneNumber,
                            numberOfMedicalRecord, diagnoses);
                } else {
                    newPatient = new Patient(lastName, firstName, patronymic, address, phoneNumber,
                            numberOfMedicalRecord);
                }

                patientDao.addPatient(newPatient);
            }
        } catch (IOException | ValidationException | DaoException e) {
            throw new FillingException(e);
        }
    }
}