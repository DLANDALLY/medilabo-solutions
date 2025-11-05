package org.medilabo.medical_record_service.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.medilabo.medical_record_service.dtos.HistoricalDto;
import org.medilabo.medical_record_service.entities.MedicalRecord;
import org.medilabo.medical_record_service.entities.PatientLocal;
import org.medilabo.medical_record_service.repositories.MedicalRecordRepository;
import org.medilabo.medical_record_service.repositories.PatientLocalRepository;
import org.medilabo.medical_record_service.services.interfaces.IMedicalRecord;
import org.medilabo.medical_record_service.services.interfaces.IPatientLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MedicalRecordServiceImplTest {
    @Autowired
    private IMedicalRecord medicalHistoryService;

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @Autowired
    private PatientLocalRepository patientService;

    private Long patientId;

    @BeforeEach
    public void setUp() {
        patientId = generateId();
        System.out.println("ID : "+ patientId);
        PatientLocal patientLocal = new PatientLocal(patientId, "Doe");
        patientService.save(patientLocal);

        MedicalRecord record1 = MedicalRecord.builder()
                .note("Diagnosis 1")
                .createdAt(LocalDateTime.now())
                .createdBy("Praticien 1")
                .patientId(patientId)
                .build();
        medicalRecordRepository.save(record1);

        MedicalRecord record2 = MedicalRecord.builder()
                .note("Diagnosis 2")
                .createdAt(LocalDateTime.now())
                .createdBy("Praticien 1")
                .patientId(patientId)
                .build();
        medicalRecordRepository.save(record2);
    }

    @Test
    @Disabled
    public void testGetMedicalHistoryByPatientId() {
        HistoricalDto result = medicalHistoryService.getMedicalHistoryByPatientId(patientId);

        assertNotNull(result);
        assertEquals(patientId, result.getPatient().getId());
        assertEquals(2, result.getMedicalHistoricalDtos().size());
        assertEquals("Diagnosis 1", result.getMedicalHistoricalDtos().get(0).getNote());
        assertEquals("Diagnosis 2", result.getMedicalHistoricalDtos().get(1).getNote());
    }

    public Long generateId() {
        return System.currentTimeMillis();
    }

}