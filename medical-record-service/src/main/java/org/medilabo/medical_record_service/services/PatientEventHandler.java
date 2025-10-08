package org.medilabo.medical_record_service.services;

import org.medilabo.medical_record_service.model.PatientEvent;
import org.medilabo.medical_record_service.entities.PatientLocal;
import org.medilabo.medical_record_service.repositories.PatientLocalRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PatientEventHandler {
    private final PatientLocalRepository repository;

    public PatientEventHandler(PatientLocalRepository repository) {
        this.repository = repository;
    }

    @KafkaListener(topics = "patient-events", groupId = "medical-record-service")
    public void consumePatientEvent(PatientEvent event) {
        PatientLocal patient = new PatientLocal(
                event.getId(),
                event.getFirstName(),
                event.getLastName(),
                event.getDateOfBirth(),
                event.getCreateAt(),
                event.getGender()
        );
        repository.save(patient);
    }
}
