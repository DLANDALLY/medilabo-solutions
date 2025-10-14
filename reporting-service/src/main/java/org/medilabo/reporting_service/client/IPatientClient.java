package org.medilabo.reporting_service.client;

import org.medilabo.reporting_service.dtos.medicalRecord.HistoricalDto;
import org.medilabo.reporting_service.model.Patient;
import org.medilabo.reporting_service.services.fallBack.ClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "GATEWAY-SERVICE", fallback = ClientFallback.class)
public interface IPatientClient {

    @GetMapping("/PATIENT-SERVICE/api/v1/patient/{id}")
    Patient getPatientById(@PathVariable Long id);

    @GetMapping("/MEDICAL-RECORD-SERVICE/api/v1/medicalrecord/{patientId}")
    HistoricalDto getMedicalRecordByPatientId(@PathVariable Long patientId);
}
