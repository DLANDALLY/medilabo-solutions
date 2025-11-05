package org.medilabo.reporting_service.client;

import org.medilabo.reporting_service.model.Patient;
import org.medilabo.reporting_service.services.fallBack.PatientClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "PATIENT-SERVICE", fallback = PatientClientFallback.class)
public interface IPatientClient {
    @GetMapping("/api/v1/patient/{id}")
    Patient getPatientById(@PathVariable Long id);
}
