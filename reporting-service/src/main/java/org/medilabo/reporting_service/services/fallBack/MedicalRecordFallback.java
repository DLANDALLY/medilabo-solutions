package org.medilabo.reporting_service.services.fallBack;

import org.medilabo.reporting_service.client.IMedicalRecordClient;
import org.medilabo.reporting_service.dtos.medicalRecord.MedicalRecordDto;
import org.medilabo.reporting_service.dtos.medicalRecord.NoteHistoryDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MedicalRecordFallback implements IMedicalRecordClient {

    @Override
    public MedicalRecordDto getMedicalRecordByPatientId(Long patientId) {
        MedicalRecordDto fallback = new MedicalRecordDto();
        fallback.setPatientId(patientId);
        fallback.setMhd(List.of(new NoteHistoryDto(null,"Service indisponible, données par défaut")));
        return fallback;
    }
}
