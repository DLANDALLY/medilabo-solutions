package org.medilabo.reporting_service.client;

import org.medilabo.reporting_service.dtos.medicalRecord.HistoricalDto;
import org.medilabo.reporting_service.services.fallBack.MedicalRecordClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "MEDICAL-RECORD-SERVICE", fallback = MedicalRecordClientFallback.class)
public interface IMedicalRecordClient {
    @GetMapping("/api/v1/medicalrecord/{patientId}")
    HistoricalDto getMedicalRecordByPatientId(@PathVariable Long patientId);
}
