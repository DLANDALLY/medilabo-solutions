package org.medilabo.patient_service.service.interfaces;

import jakarta.validation.Valid;
import org.medilabo.patient_service.dtos.AddPatientDto;
import org.medilabo.patient_service.dtos.UpdatePatientDto;
import org.medilabo.patient_service.entites.Patient;

import java.util.List;

public interface IPatient {
    List<Patient> getAllPatients();

    Patient getPatientById(Long id);

    Patient updatePatient(@Valid Long id, UpdatePatientDto updatePatientDto);

    Patient createPatient(@Valid AddPatientDto addPatientDto);
}
