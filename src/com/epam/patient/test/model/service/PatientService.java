package com.epam.patient.test.model.service;

import com.epam.patient.exception.ServiceException;
import com.epam.patient.exception.ValidationException;
import com.epam.patient.test.model.entity.Diagnosis;
import com.epam.patient.test.model.entity.Patient;

import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

public interface PatientService {

    void addPatient(Patient patient) throws ValidationException, ServiceException;

    List<Patient> findAll();

    Patient findPatientById(int id) throws ServiceException;

    List<Patient> findByDiagnosis(Diagnosis diagnosis);

    List<Patient> findByMedicalRecordsInInterval(int firstNumberOfInterval, int lastNumberOfInterval);

    void delete(Patient patient) throws ServiceException;

    void update(Patient patient, EnumSet<Diagnosis> diagnoses) throws ServiceException;

    int countPatientsWithoutDiagnosis();

    List<Patient> findAllByLastName();

    List<Patient> findAllByLastAndFirstName();

    List<Patient> findAllByNumberOfMedicalRecord();

    List<Patient> findAllByNumberOfMedicalRecordDesc();
}
