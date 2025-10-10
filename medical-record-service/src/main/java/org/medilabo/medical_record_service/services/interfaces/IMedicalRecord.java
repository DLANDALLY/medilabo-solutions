package org.medilabo.medical_record_service.services.interfaces;

import org.medilabo.medical_record_service.dtos.HistoricalDto;
import org.medilabo.medical_record_service.dtos.MedicalHistoricalDto;

public interface IMedicalRecord {
    HistoricalDto getMedicalHistoryByPatientId(Long patientId);

    HistoricalDto addNewNote(Long patientId, MedicalHistoricalDto mhd);
}
