package com.epam.patient.model.dao;

import com.epam.patient.model.entity.Diagnosis;
import com.epam.patient.model.entity.Patient;
import com.epam.patient.exception.DaoException;

import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

public interface PatientDao {

    void addPatient(Patient patient) throws DaoException;

    List<Patient> findAll();

    Optional<Patient> findPatientById(int id);

    List<Patient> findByDiagnosis(Diagnosis diagnosis);

    List<Patient> findByMedicalRecordsInInterval(int firstNumberOfInterval,
                                                 int lastNumberOfInterval);

    boolean contains(Patient patient);

    void delete(Patient patient) throws DaoException;

    void update(Patient patient, EnumSet<Diagnosis> diagnoses) throws DaoException;
}
