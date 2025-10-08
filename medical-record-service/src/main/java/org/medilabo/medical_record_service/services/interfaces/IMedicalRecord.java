package org.medilabo.medical_record_service.services.interfaces;

import org.medilabo.medical_record_service.dtos.HistoricalDto;
import org.medilabo.medical_record_service.dtos.MedicalHistoricalDto;
import org.medilabo.medical_record_service.dtos.MedicalRecordDto;
import org.medilabo.medical_record_service.entities.MedicalRecord;
import org.medilabo.medical_record_service.model.Patient;

import java.util.List;

public interface IMedicalRecord {
    MedicalRecord createMedicalRecord(MedicalRecord medicalRecord);

    List<HistoricalDto> getAllMedicalHistorical();

    HistoricalDto getMedicalHistoryByPatientId(Long patientId);

    HistoricalDto addNewNote(Long patientId, MedicalHistoricalDto mhd);
}
