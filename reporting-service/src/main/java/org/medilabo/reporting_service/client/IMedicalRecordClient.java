package org.medilabo.reporting_service.client;

import org.medilabo.reporting_service.dtos.medicalRecord.MedicalRecordDto;
import org.medilabo.reporting_service.services.fallBack.MedicalRecordFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "api-gateway", fallback = MedicalRecordFallback.class)
public interface IMedicalRecordClient {

    @GetMapping("/api/v1/medicalrecord/{patientId}")
    MedicalRecordDto getMedicalRecordByPatientId(@PathVariable Long patientId);
}
