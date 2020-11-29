package com.epam.patient.main;

import com.epam.patient.model.entity.Diagnosis;
import com.epam.patient.model.entity.Patient;
import com.epam.patient.exception.ValidationException;
import com.epam.patient.model.service.PatientService;
import com.epam.patient.model.show.ResultsPrinting;

public class Main {
    public static void main(String[] args) {


        Hospital hospital = new Hospital();
        try {
            hospital.addPatient(new Patient("Bobrov", "Igor", "Mihaylovich", "Nezavisimosti 120, 12",
                    "+375296472390", 12112001))
                    .addPatient(new Patient("Petrov", "Nikita", "Alexandrovich", "Mavra 14, 23",
                            "+375442349065", 12112034, Diagnosis.SCOLIOSIS))
                    .addPatient(new Patient("Dunay", "Ilya", "Maximovich", "Dzerzhinskaga 109, 9",
                            "80296790912", 12111560, Diagnosis.SCOLIOSIS))
                    .addPatient(new Patient("Vasiliev", "Mihail", "Vitalyevich", "Voyskovaya 3, 42",
                            "+375294565756", 12111823, Diagnosis.COVID_19))
                    .addPatient(new Patient("Valuyeva", "Alexandra", "Vitalyevna", "Tsimiryazeva 45, 30",
                            "+375443895675", 12115678, Diagnosis.COVID_19))
                    .addPatient(new Patient("Pirova", "Diana", "Arkadyevna", "Rakassouskaga 12, 3",
                            "80336239812", 12111732));
        } catch (ValidationException e) {
            e.printStackTrace();
        }

        PatientService service = new PatientService();
        ResultsPrinting show = new ResultsPrinting();

        show.printAllPatients(hospital.getPatients());
        show.printFoundById(2, service.findPatientById(2));
        show.printFoundByDiagnosis(Diagnosis.SCOLIOSIS.toString(), service.findAllByDiagnosis(Diagnosis.SCOLIOSIS));
        show.printFoundByMedicalRecordsInInterval(12111700, 12112040,
                service.findAllByMedicalRecordsInInterval(12111700, 12112040));
        show.printNumberOfPatientsWithoutDiagnosis(service.countPatientsWithoutDiagnosis());
    }
}
