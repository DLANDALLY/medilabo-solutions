package org.medilabo.medical_record_service.services.interfaces;

import org.medilabo.medical_record_service.dtos.HistoricalDto;
import org.medilabo.medical_record_service.dtos.MedicalRecordDto;
import org.medilabo.medical_record_service.entities.MedicalRecord;
import org.medilabo.medical_record_service.model.Patient;

import java.util.List;

public interface IMedicalRecord {
    MedicalRecord createMedicalRecord(MedicalRecordDto medicalRecordDto);

    List<MedicalRecord> getAllMedicalRecords();

    void delete(String id);

    List<HistoricalDto> getAllMedicalHistorical();

    HistoricalDto getMedicalHistoryByPatientId(Long patientId);
}
