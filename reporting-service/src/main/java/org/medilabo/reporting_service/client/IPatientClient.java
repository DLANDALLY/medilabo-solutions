package org.medilabo.reporting_service.client;

import org.medilabo.reporting_service.dtos.patient.PatientDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface IPatientClient {
    @GetMapping("/api/v1/patient/{id}")
    PatientDto getPatientById(@PathVariable Long id);
}
