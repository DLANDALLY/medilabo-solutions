package org.medilabo.patient_service.service.interfaces;

import lombok.AllArgsConstructor;
import org.medilabo.patient_service.dtos.PatientDto;
import org.medilabo.patient_service.entites.Patient;
import org.medilabo.patient_service.repositories.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PatientServiceImpl implements IPatient{
    private PatientRepository patientRepository;

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public Patient getPatientById(Long id) {
        return null;
    }

    @Override
    public Patient updatePatient(Long id, PatientDto patientDto) {
        return null;
    }

    @Override
    public Patient createPatient(PatientDto patientDto) {
        return null;
    }

    @Override
    public boolean deletePatient(Long id) {
        return false;
    }
}
