package org.medilabo.reporting_service.services.fallBack;

import org.medilabo.reporting_service.client.IPatientClient;
import org.medilabo.reporting_service.model.Patient;
import org.springframework.stereotype.Component;

@Component
public class PatientClientFallback implements IPatientClient {
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


}
