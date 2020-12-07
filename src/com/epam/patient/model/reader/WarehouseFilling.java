package com.epam.patient.model.reader;

import com.epam.patient.exception.DaoException;
import com.epam.patient.exception.FillingException;
import com.epam.patient.exception.ValidationException;
import com.epam.patient.model.entity.Diagnosis;
import com.epam.patient.model.entity.Patient;
import com.epam.patient.model.dao.impl.PatientDaoImpl;
import com.epam.patient.util.PatientValidator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WarehouseFilling {

    private static final int MAX_NUMBER_OF_PARAMETERS = 7;
    private static final String FILE_PATH = "res/data/Patients.txt";
    private static final String DELIMITER = ";";
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
            String dataFromFile = stream.collect(Collectors.joining());
            String[] patients = dataFromFile.split(DELIMITER);

            for (String patient : patients) {
                patientInfo = patient.split(SEPARATOR1);
                lastName = patientInfo[0].trim();
                firstName = patientInfo[1].trim();
                patronymic = patientInfo[2].trim();
                address = patientInfo[3].trim();
                phoneNumber = patientInfo[4].trim();
                numberOfMedicalRecord = Integer.parseInt(patientInfo[5].trim());

                if (!PatientValidator.checkName(lastName)) {
                    throw new ValidationException("Last name isn't correct: " + lastName);
                }
                if (!PatientValidator.checkName(firstName)) {
                    throw new ValidationException("First name isn't correct: " + firstName);
                }
                if (!PatientValidator.checkName(patronymic)) {
                    throw new ValidationException("Patronymic isn't correct: " + patronymic);
                }
                if (!PatientValidator.isPhoneNumber(phoneNumber)) {
                    throw new ValidationException("Phone number isn't correct: " + phoneNumber);
                }
                if (!PatientValidator.isNumberOfMedicalRecord(numberOfMedicalRecord)) {
                    throw new ValidationException("Number of medical record isn't correct: " + numberOfMedicalRecord);
                }
                if (!PatientValidator.isAddress(address)) {
                    throw new ValidationException("Address isn't correct: " + address);
                }

                if (patientInfo.length == MAX_NUMBER_OF_PARAMETERS) {
                    diagnosesInfo = patientInfo[6].split(SEPARATOR2);
                    diagnoses = new Diagnosis[diagnosesInfo.length];

                    for (int i = 0; i < diagnoses.length; i++) {
                        diagnoses[i] = Diagnosis.valueOf(diagnosesInfo[i].trim());
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