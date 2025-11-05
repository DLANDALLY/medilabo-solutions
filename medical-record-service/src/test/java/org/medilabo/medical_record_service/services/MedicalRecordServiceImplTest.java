package org.medilabo.medical_record_service.services;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.medilabo.medical_record_service.dtos.HistoricalDto;
import org.medilabo.medical_record_service.dtos.MedicalHistoricalDto;
import org.medilabo.medical_record_service.entities.MedicalRecord;
import org.medilabo.medical_record_service.entities.PatientLocal;
import org.medilabo.medical_record_service.repositories.MedicalRecordRepository;
import org.medilabo.medical_record_service.services.interfaces.IPatientLocal;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MedicalRecordServiceImplTest {
    @Mock
    private MedicalRecordRepository medicalRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private IPatientLocal patientService;

    @InjectMocks
    private MedicalRecordServiceImpl medicalRecordService;

    private PatientLocal patientLocal;
    private MedicalRecord medicalRecord;
    private MedicalHistoricalDto medicalHistoricalDto;

    @BeforeEach
    public void setUp() {
        patientLocal = new PatientLocal();
        patientLocal.setId(1L);
        patientLocal.setLastName("Doe");

        medicalRecord = new MedicalRecord();
        medicalRecord.setId("1L");
        medicalRecord.setNote("Note test");
        medicalRecord.setCreatedBy("Dr Test");
        medicalRecord.setCreatedAt(LocalDateTime.now());
        medicalRecord.setPatientId(1L);

        medicalHistoricalDto = new MedicalHistoricalDto();
        medicalHistoricalDto.setId("1L");
        medicalHistoricalDto.setNote("Note test");
    }

    @Test
    void getMedicalHistoryByPatientIdShouldReturnHistoricalDto() {
        when(patientService.getPatientLocalById(1L)).thenReturn(patientLocal);
        when(medicalRepository.findByPatientId(1L)).thenReturn(List.of(medicalRecord));
        when(modelMapper.map(any(MedicalRecord.class), eq(MedicalHistoricalDto.class)))
                .thenReturn(medicalHistoricalDto);

        HistoricalDto result = medicalRecordService.getMedicalHistoryByPatientId(1L);

        assertNotNull(result);
        assertEquals(patientLocal, result.getPatient());
        assertEquals(1, result.getMedicalHistoricalDtos().size());
        assertEquals("Note test", result.getMedicalHistoricalDtos().getFirst().getNote());
    }

    @Test
    void shouldAddNoteAndReturnHistoricalDto() {
        when(patientService.getPatientLocalById(1L)).thenReturn(patientLocal);
        when(medicalRepository.findByPatientId(1L)).thenReturn(List.of(medicalRecord));
        when(modelMapper.map(any(MedicalRecord.class), eq(MedicalHistoricalDto.class)))
                .thenReturn(medicalHistoricalDto);

        HistoricalDto result = medicalRecordService.addNewNote(1L, medicalHistoricalDto);

        assertNotNull(result);
        assertEquals(patientLocal, result.getPatient());
    }

    @Test
    void shouldReturnEmptyListIfNoRecord() {
        when(patientService.getPatientLocalById(1L)).thenReturn(patientLocal);
        when(medicalRepository.findByPatientId(1L)).thenReturn(List.of());

        HistoricalDto result = medicalRecordService.getMedicalHistoryByPatientId(1L);

        assertNotNull(result);
        assertEquals(patientLocal, result.getPatient());
        assertTrue(result.getMedicalHistoricalDtos().isEmpty());
    }

}