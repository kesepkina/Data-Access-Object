package com.epam.patient.model.creator;

import com.epam.patient.exception.DaoException;
import com.epam.patient.exception.FillingException;
import com.epam.patient.exception.ValidationException;
import com.epam.patient.model.dao.impl.PatientDaoImpl;
import com.epam.patient.model.entity.Diagnosis;
import com.epam.patient.model.entity.Patient;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.EnumSet;
import java.util.stream.Stream;

public class WarehouseFilling {
    private final static String FILE_PATH = "res/data/Patients.txt";
    private PatientDaoImpl patientDao = new PatientDaoImpl();

    public void fillFromTxtFile() throws FillingException {
        Path path = Paths.get(FILE_PATH);

        try (Stream<String> stream = Files.lines(path)) {
            String dataFromFile = stream.toString();
            String[] patients = dataFromFile.split("\n");
            String[] patientInfo;
            String[] diagnosesInfo;
            Patient newPatient;
            Diagnosis[] diagnoses;
            for (String patient : patients) {
                patientInfo = patient.split(" | ");
                diagnosesInfo = patientInfo[6].split(", ");
                diagnoses = new Diagnosis[diagnosesInfo.length];
                for(int i = 0; i < diagnoses.length; i++) {
                    diagnoses[i] = Diagnosis.valueOf(diagnosesInfo[i]);
                }

                newPatient = new Patient(patientInfo[0], patientInfo[1], patientInfo[2],
                        patientInfo[3], patientInfo[4], Integer.parseInt(patientInfo[5]),
                        diagnoses);
                patientDao.addPatient(newPatient);
            }
        } catch (IOException | ValidationException | DaoException e) {
            throw new FillingException(e);
        }


    }
}
