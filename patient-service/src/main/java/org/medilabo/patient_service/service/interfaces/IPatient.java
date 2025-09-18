package org.medilabo.patient_service.service.interfaces;

import org.medilabo.patient_service.dtos.PatientDto;
import org.medilabo.patient_service.entites.Patient;

import java.util.List;

public interface IPatient {
    List<Patient> getAllPatients();

    Patient getPatientById(Long id);

    Patient updatePatient(Long id, PatientDto patientDto);

    Patient createPatient(PatientDto patientDto);

    boolean deletePatient(Long id);
}
