package org.medilabo.patient_service.service;

import jakarta.ws.rs.NotFoundException;
import org.medilabo.patient_service.dtos.AddPatientDto;
import org.medilabo.patient_service.dtos.PatientEvent;
import org.medilabo.patient_service.dtos.UpdatePatientDto;
import org.medilabo.patient_service.entites.Patient;
import org.medilabo.patient_service.exception.ResourceNotFoundException;
import org.medilabo.patient_service.repositories.PatientRepository;
import org.medilabo.patient_service.service.interfaces.IPatient;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements IPatient {
    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;
    private final KafkaTemplate<String, PatientEvent> kafkaTemplate;

    public PatientServiceImpl(PatientRepository patientRepository, ModelMapper modelMapper, KafkaTemplate<String, PatientEvent> kafkaTemplate) {
        this.patientRepository = patientRepository;
        this.modelMapper = modelMapper;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public Patient getPatientById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("The Patient n°"+ id +" does not exist"));
    }

    @Override
    public Patient updatePatient(Long id, UpdatePatientDto updatePatientDto) {
        Patient patientDB = getPatientById(id);
        modelMapper.map(updatePatientDto, patientDB);

        Patient patient = saving(patientDB);
        publishPatientEvent(patient);
        return patient;
    }

    @Override
    public Patient createPatient(AddPatientDto addPatientDto) {
        Patient patient1 = modelMapper.map(addPatientDto, Patient.class);
        if (patientRepository.findAll().contains(patient1))
            throw new RuntimeException("The patient already exist");

        Patient patient = saving(patient1);
        publishPatientEvent(patient);
        return patient;
    }

    /**
     * Publishes a Kafka event containing the patient's information
     * @param patient The patient whose information will be sent in the event
     */
    private void publishPatientEvent(Patient patient){
        if (patient == null)
            throw new NullPointerException("Cannot publish event, the patient object is null");

        PatientEvent event = new PatientEvent(
                patient.getId(),
                patient.getFirstName(),
                patient.getLastName(),
                patient.getDateOfBirth(),
                patient.getCreateAt(),
                patient.getGender().toString());
        kafkaTemplate.send("patient-events",event);
    }

    private Patient saving(Patient patient){
        if(patient == null)
            throw new NullPointerException("The Patient send in params is null");
        return patientRepository.save(patient);
    }
}
