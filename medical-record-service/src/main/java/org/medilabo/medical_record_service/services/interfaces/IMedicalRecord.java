package org.medilabo.medical_record_service.services.interfaces;

import org.medilabo.medical_record_service.entities.MedicalRecord;

import java.util.List;

public interface IMedicalRecord {
    List<MedicalRecord> getAllMedicalRecords();

    MedicalRecord getMedicalRecordById(Long id);
}
