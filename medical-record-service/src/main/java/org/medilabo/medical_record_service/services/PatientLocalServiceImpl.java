package org.medilabo.medical_record_service.services;

import lombok.AllArgsConstructor;
import org.medilabo.medical_record_service.entities.PatientLocal;
import org.medilabo.medical_record_service.repositories.PatientLocalRepository;
import org.medilabo.medical_record_service.services.interfaces.IPatientLocal;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PatientLocalServiceImpl implements IPatientLocal {
    private PatientLocalRepository patientRepository;

    @Override
    public List<PatientLocal> getAllPatient(){
        return patientRepository.findAll();
    }

    @Override
    public PatientLocal getPatientLocalById(Long id){
        return patientRepository.findById(id).orElseThrow();
    }
}
