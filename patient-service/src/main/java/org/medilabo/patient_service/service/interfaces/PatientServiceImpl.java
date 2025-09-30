package org.medilabo.patient_service.service.interfaces;

import jakarta.ws.rs.NotFoundException;
import lombok.AllArgsConstructor;
import org.medilabo.patient_service.dtos.PatientDto;
import org.medilabo.patient_service.entites.Patient;
import org.medilabo.patient_service.repositories.PatientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements IPatient{
    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;

    public PatientServiceImpl(PatientRepository patientRepository, ModelMapper modelMapper) {
        this.patientRepository = patientRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public Patient getPatientById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("The Patient n°"+ id +" does not exist"));
    }

    @Override
    public Patient updatePatient(Long id, PatientDto patientDto) {
        if (!existsById(id))
            throw new NotFoundException("The Patient n°"+ id +" does not exist");
        Patient patientDB = getPatientById(id);
        modelMapper.map(patientDto, patientDB);

        return saving(patientDB);
    }

    @Override
    public Patient createPatient(PatientDto patientDto) {

        Patient patient = modelMapper.map(patientDto, Patient.class);
        if (patientRepository.findAll().contains(patient)){
            throw new RuntimeException("The patient ");
        }
        return saving(patient);
    }

    @Override
    public boolean deletePatient(Long id) {
        return false;
    }

    private boolean existsById(Long id){
        return patientRepository.existsById(id);
    }

    private Patient saving(Patient patient){
        if(patient == null)
            throw new NullPointerException("The Patient send in params is null");
        return patientRepository.save(patient);
    }
}
