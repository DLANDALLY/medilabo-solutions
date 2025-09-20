package org.medilabo.medical_record_service.services;

import lombok.AllArgsConstructor;
import org.medilabo.medical_record_service.entities.MedicalRecord;
import org.medilabo.medical_record_service.repositories.MedicalRecordRepository;
import org.medilabo.medical_record_service.services.interfaces.IMedicalRecord;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MedicalRecordServiceImpl implements IMedicalRecord {
    private MedicalRecordRepository medicalRecordRepository;

    @Override
    public List<MedicalRecord> getAllMedicalRecords() {
        return medicalRecordRepository.findAll();
    }

    @Override
    public MedicalRecord getMedicalRecordById(Long id) {
        return medicalRecordRepository.findById(id)
                .orElseThrow();
    }
}
