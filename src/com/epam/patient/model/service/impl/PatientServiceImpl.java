package com.epam.patient.model.service.impl;

import com.epam.patient.exception.DaoException;
import com.epam.patient.exception.ServiceException;
import com.epam.patient.exception.ValidationException;
import com.epam.patient.model.entity.Diagnosis;
import com.epam.patient.model.entity.Patient;
import com.epam.patient.model.service.PatientService;
import com.epam.patient.model.dao.impl.PatientDaoImpl;
import com.epam.patient.util.PatientComparator;
import com.epam.patient.util.PatientValidator;

import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

public class PatientServiceImpl implements PatientService {

    private static final PatientDaoImpl patientDao = new PatientDaoImpl();

    @Override
    public void addPatient(Patient patient) throws ValidationException, ServiceException {
        if (!PatientValidator.checkName(patient.getLastName())) {
            throw new ValidationException("Last name isn't correct: " + patient.getLastName());
        }
        if (!PatientValidator.checkName(patient.getFirstName())) {
            throw new ValidationException("First name isn't correct.");
        }
        if (!PatientValidator.checkName(patient.getPatronymic())) {
            throw new ValidationException("Patronymic isn't correct.");
        }
        if (!PatientValidator.isPhoneNumber(patient.getPhoneNumber())) {
            throw new ValidationException("Phone number isn't correct.");
        }
        if (!PatientValidator.isNumberOfMedicalRecord(patient.getNumberOfMedicalRecord())) {
            throw new ValidationException("Number of medical record isn't correct.");
        }
        if (!PatientValidator.isAddress(patient.getAddress())) {
            throw new ValidationException("Address isn't correct.");
        }

        try {
            patientDao.addPatient(patient);
        } catch (DaoException e) {
            throw new ServiceException("Error by adding the patient: ", e);
        }
    }

    @Override
    public List<Patient> findAll() {
        return patientDao.findAll();
    }

    @Override
    public Optional<Patient> findPatientById(int id) {
        return patientDao.findPatientById(id);
    }

    @Override
    public List<Patient> findByDiagnosis(Diagnosis diagnosis) {
        return patientDao.findByDiagnosis(diagnosis);
    }

    @Override
    public List<Patient> findByMedicalRecordsInInterval(int firstNumberOfInterval, int lastNumberOfInterval) {
        return patientDao.findByMedicalRecordsInInterval(firstNumberOfInterval, lastNumberOfInterval);
    }

    @Override
    public void delete(Patient patient) throws ServiceException {
        try {
            patientDao.delete(patient);
        } catch (DaoException e) {
            throw new ServiceException("Error by deleting: ", e);
        }
    }

    @Override
    public void update(Patient patient, EnumSet<Diagnosis> diagnoses) throws ServiceException {
        try {
            patientDao.update(patient, diagnoses);
        } catch (DaoException e) {
            throw new ServiceException("Error by updating patient info: ", e);
        }
    }

    @Override
    public int countPatientsWithoutDiagnosis() {
        int count = 0;
        List<Patient> allPatients = patientDao.findAll();
        for (Patient patient : allPatients) {
            if (patient.getDiagnoses().isEmpty()) {
                count++;
            }
        }
        return count;
    }

    @Override
    public List<Patient> findAllByLastName() {
        List<Patient> patients = patientDao.findAll();
        patients.sort(PatientComparator.LAST_NAME);
        return patients;
    }

    @Override
    public List<Patient> findAllByLastAndFirstName() {
        List<Patient> patients = patientDao.findAll();
        patients.sort(PatientComparator.LAST_NAME.thenComparing(PatientComparator.FIRST_NAME));
        return patients;
    }

    @Override
    public List<Patient> findAllByNumberOfMedicalRecord() {
        List<Patient> patients = patientDao.findAll();
        patients.sort(PatientComparator.MEDICAL_RECORD);
        return patients;
    }

    @Override
    public List<Patient> findAllByNumberOfMedicalRecordDesc() {
        List<Patient> patients = patientDao.findAll();
        patients.sort(PatientComparator.MEDICAL_RECORD.reversed());
        return patients;
    }
}
