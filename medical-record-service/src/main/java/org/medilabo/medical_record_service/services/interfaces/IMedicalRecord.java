package org.medilabo.medical_record_service.services.interfaces;

import org.medilabo.medical_record_service.dtos.HistoricalDto;
import org.medilabo.medical_record_service.dtos.MedicalRecordDto;
import org.medilabo.medical_record_service.entities.MedicalRecord;
import org.medilabo.medical_record_service.model.Patient;

import java.util.List;

public interface IMedicalRecord {
    MedicalRecord createMedicalRecord(MedicalRecordDto medicalRecordDto);

    List<MedicalRecord> getAllMedicalRecords();

    MedicalRecord getMedicalRecordById(String id);

    void delete(String id);

    List<HistoricalDto> getAllMedicalHistorical(List<Patient> patients);
}
