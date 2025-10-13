package org.medilabo.reporting_service.services.fallBack;

import org.medilabo.reporting_service.client.IMedicalRecordClient;
import org.medilabo.reporting_service.dtos.medicalRecord.HistoricalDto;
import org.medilabo.reporting_service.dtos.medicalRecord.NoteHistoryDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MedicalRecordFallback implements IMedicalRecordClient {

//    public HistoricalDto getMedicalRecordByPatientId(Long patientId) {
//        System.out.println("Fallback exécuté pour Medical record " + patientId);
//        HistoricalDto fallback = new HistoricalDto();
//        fallback.setPatient(null);
//        fallback.setMedicalHistoricalDtos(List.of());
//        return fallback;
//    }
}
