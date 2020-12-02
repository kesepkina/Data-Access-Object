package com.epam.patient.util;

import com.epam.patient.model.entity.Patient;

import java.util.Comparator;

public enum PatientComparator implements Comparator<Patient> {

    LAST_NAME {
        @Override
        public int compare(Patient o1, Patient o2) {
            return o1.getLastName().compareTo(o2.getLastName());
        }
    },

    FIRST_NAME {
        @Override
        public int compare(Patient o1, Patient o2) {
            return o1.getFirstName().compareTo(o2.getFirstName());
        }
    },

    MEDICAL_RECORD {
        @Override
        public int compare(Patient o1, Patient o2) {
            return Integer.compare(o1.getNumberOfMedicalRecord(), o2.getNumberOfMedicalRecord());
        }

        @Override
        public Comparator<Patient> reversed() {
            return null;
        }
    }
}
