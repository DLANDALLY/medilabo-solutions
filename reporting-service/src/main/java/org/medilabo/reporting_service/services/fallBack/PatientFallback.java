package org.medilabo.reporting_service.services.fallBack;

import org.medilabo.reporting_service.client.IPatientClient;
import org.medilabo.reporting_service.dtos.patient.PatientDto;
import org.springframework.stereotype.Component;

@Component
public class PatientFallback implements IPatientClient {
    @Override
    public PatientDto getPatientById(Long id) {
        return PatientDto.builder()
                .id(id)
                .gender("Non disponible")
                .build();
    }
}
