package com.epam.patient.model.service;

import com.epam.patient.exception.ServiceException;
import com.epam.patient.exception.ValidationException;
import com.epam.patient.model.entity.Diagnosis;
import com.epam.patient.model.entity.Patient;

import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

public interface PatientService {

    void addPatient(Patient patient) throws ValidationException, ServiceException;

    List<Patient> findAll();

    Optional<Patient> findPatientById(int id);

    List<Patient> findByDiagnosis(Diagnosis diagnosis);

    List<Patient> findByMedicalRecordsInInterval(int firstNumberOfInterval, int lastNumberOfInterval);

    void delete(Patient patient) throws ServiceException;

    void update(Patient patient, EnumSet<Diagnosis> diagnoses) throws ServiceException;

    int countPatientsWithoutDiagnosis();

    List<Patient> sortByLastName();

    List<Patient> sortByLastAndFirstName();

    List<Patient> sortByNumberOfMedicalRecord();

    List<Patient> sortByNumberOfMedicalRecordDesc();
}
