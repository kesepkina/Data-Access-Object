package com.epam.patient.test.model.service.impl;

import com.epam.patient.exception.FillingException;
import com.epam.patient.exception.ServiceException;
import com.epam.patient.exception.ValidationException;
import com.epam.patient.test.model.entity.Diagnosis;
import com.epam.patient.test.model.entity.Patient;
import com.epam.patient.test.model.output.ResultsPrinting;
import com.epam.patient.test.model.reader.WarehouseFilling;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import static org.testng.Assert.*;

public class PatientServiceImplTest {

    private static final Logger logger = LogManager.getLogger();

    private static final WarehouseFilling filling = new WarehouseFilling();
    private static final PatientServiceImpl patientService = new PatientServiceImpl();
    private static final ResultsPrinting resultsPrinting = new ResultsPrinting();

    private Patient patient;

    @BeforeClass
    public void setUp() {
        try {
            filling.fillFromTxtFile();
        } catch (FillingException e) {
            logger.error("Error by filling: ", e);
        }
        patient = new Patient("Egorov", "Andrey", "Nikityevich", "Nahimova 19, 27", "80294565570", 12112005, Diagnosis.MIGRAINE);
    }

    @Test
    public void testFindAll() {
        resultsPrinting.printListOfPatients(patientService.findAll());
    }

    @Test
    public void testAddPatient() {
        try {
            patientService.addPatient(patient);
        } catch (ValidationException | ServiceException e) {
            logger.error("Error by adding the patient: ", e);
        }
        assertNotNull(patientService.findPatientById(patient.getId()));
    }

    @Test
    public void testFindPatientById() {
        try {
            patientService.addPatient(patient);
        } catch (ValidationException | ServiceException e) {
            logger.error("Error by adding the patient: ", e);
        }
        Patient expected = patient;
        int patientId = patient.getId();
        Patient actual = null;
        actual = patientService.findPatientById(patientId);

        assertEquals(actual, expected);
    }

    @Test
    public void testFindPatientByIdFalse() {
        int patientId = 15;
        Patient actual = patientService.findPatientById(patientId);
        assertNull(actual);
    }

    @Test
    public void testFindByDiagnosis() {
        List<Patient> expected = new ArrayList<>();
        expected.add(patientService.findPatientById(3));
        expected.add(patientService.findPatientById(4));
        List<Patient> actual = patientService.findByDiagnosis(Diagnosis.COVID_19);
        assertEquals(actual, expected);
    }

    @Test
    public void testFindByMedicalRecordsInInterval() {
        List<Patient> expected = new ArrayList<>();
        expected.add(patientService.findPatientById(6));
        expected.add(patientService.findPatientById(7));
        List<Patient> actual = patientService.findByMedicalRecordsInInterval(12112001, 12112004);
        assertEquals(actual, expected);
    }

    @Test
    public void testDelete() {

        try {
            patientService.delete(patient);
        } catch (ServiceException e) {
            logger.error("Error by deleting the patient: ", e);
        }

        int patientId = patient.getId();
        assertNull(patientService.findPatientById(patientId));
    }

    @Test
    public void testUpdate() {
        Patient patientForUpdate = patientService.findPatientById(7);
        EnumSet<Diagnosis> diagnoses = EnumSet.of(Diagnosis.COVID_19, Diagnosis.MIGRAINE);

        try {
            patientService.update(patientForUpdate, diagnoses);
        } catch (ServiceException e) {
            logger.error("Error by updating the patient: ", e);
        }

        patientForUpdate = patientService.findPatientById(7);
        assertEquals(patientForUpdate.getDiagnoses(), diagnoses);
    }

    @Test
    public void testCountPatientsWithoutDiagnosis() {
        int expected = 3;
        int actual = patientService.countPatientsWithoutDiagnosis();
        assertEquals(actual, expected);
    }

    @Test
    public void testFindAllByLastName() {
        List<Patient> patientsSortedByLastName = patientService.findAllByLastName();
        resultsPrinting.printListOfPatients(patientsSortedByLastName, "All patients sorted by last name");
    }

    @Test
    public void testFindAllByLastAndFirstName() {
        List<Patient> patientsSortedByLastAndFirstName = patientService.findAllByLastAndFirstName();
        resultsPrinting.printListOfPatients(patientsSortedByLastAndFirstName, "All patients sorted by last name and then by first name");
    }

    @Test
    public void testFindAllByNumberOfMedicalRecord() {
        List<Patient> patientsSortedByNumberOfMedicalRecord = patientService.findAllByNumberOfMedicalRecord();
        resultsPrinting.printListOfPatients(patientsSortedByNumberOfMedicalRecord, "All patients sorted by number of medical record");
    }

    @Test
    public void testFindAllByNumberOfMedicalRecordDesc() {
        List<Patient> patientsSortedByNumberOfMedicalRecord = patientService.findAllByNumberOfMedicalRecordDesc();
        resultsPrinting.printListOfPatients(patientsSortedByNumberOfMedicalRecord, "All patients sorted by number of medical record in reverse order");
    }
}
