package org.medilabo.reporting_service.client;

import org.medilabo.reporting_service.dtos.medicalRecord.HistoricalDto;
import org.medilabo.reporting_service.services.fallBack.MedicalRecordFallback;
import org.medilabo.reporting_service.services.fallBack.PatientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(
//        name = "GATEWAY-SERVICE",
//        path = "/MEDICAL-RECORD-SERVICE/api/v1/medicalrecord",
//        fallback = MedicalRecordFallback.class)
public interface IMedicalRecordClient {

//    @GetMapping("/{patientId}")
//    HistoricalDto getMedicalRecordByPatientId(@PathVariable Long patientId);
//
}
