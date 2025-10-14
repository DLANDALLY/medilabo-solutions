package org.medilabo.reporting_service.services.fallBack;

import org.medilabo.reporting_service.client.IPatientClient;
import org.medilabo.reporting_service.dtos.medicalRecord.HistoricalDto;
import org.medilabo.reporting_service.model.Patient;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClientFallback implements IPatientClient {
    @Override
    public Patient getPatientById(Long id) {
        System.out.println("Fallback exécuté pour patient " + id);
        return Patient.builder()
                .id(id)
                .firstName("Non disponible")
                .lastName("Non disponible")
                .gender(null)
                .phoneNumber("Non disponible")
                .postalAddress("Non disponible")
                .createAt(null)
                .dateOfBirth(null)
                .build();
    }

    @Override
    public HistoricalDto getMedicalRecordByPatientId(Long patientId) {
        System.out.println("Fallback exécuté pour Medical record " + patientId);
        HistoricalDto fallback = new HistoricalDto();
        fallback.setPatient(null);
        fallback.setMedicalHistoricalDtos(List.of());
        return fallback;
    }
}
