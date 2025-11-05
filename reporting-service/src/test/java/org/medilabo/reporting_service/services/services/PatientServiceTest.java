package org.medilabo.reporting_service.services.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.medilabo.reporting_service.client.IMedicalRecordClient;
import org.medilabo.reporting_service.client.IPatientClient;
import org.medilabo.reporting_service.dtos.medicalRecord.HistoricalDto;
import org.medilabo.reporting_service.dtos.medicalRecord.MedicalHistoricalDto;
import org.medilabo.reporting_service.dtos.patient.PatientDto;
import org.medilabo.reporting_service.model.Patient;
import org.medilabo.reporting_service.model.enums.EGender;
import org.medilabo.reporting_service.services.interfaces.IReporting;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PatientServiceTest {
    private PatientService patientService;
    private IPatientClient patientClient;
    private IMedicalRecordClient medicalRecordClient;
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        patientClient = mock(IPatientClient.class);
        medicalRecordClient = mock(IMedicalRecordClient.class);
        modelMapper = new ModelMapper();
        patientService = new PatientService(modelMapper, patientClient, medicalRecordClient);
    }

    @Test
    void getPatientDto() {
        Long patientId = 1L;
        Patient patient = new Patient();
        patient.setId(patientId);
        patient.setDateOfBirth(LocalDate.of(1990, 1, 15));
        patient.setGender(EGender.F);

        when(patientClient.getPatientById(patientId)).thenReturn(patient);

        PatientDto result = patientService.getPatientDto(patientId);

        assertNotNull(result);
        assertEquals(patientId, result.getId());
        assertEquals(LocalDate.of(1990, 1, 15), result.getDateOfBirth());
        assertEquals("F", result.getGender());
    }

    @Test
    void ShouldThrowExceptionWhenClientFails() {
        Long patientId = 2L;
        when(patientClient.getPatientById(patientId)).thenThrow(new RuntimeException("Client error"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> patientService.getPatientDto(patientId));
        assertEquals("Client error", exception.getMessage());
    }

    @Test
    void shouldGetMedicalRecordByPatientIdReturnHistoricalDto() {
        Long patientId = 3L;
        HistoricalDto historicalDto = new HistoricalDto();
        historicalDto.setMedicalHistoricalDtos(List.of(
                new MedicalHistoricalDto("1L", "Note 1"),
                new MedicalHistoricalDto("2L", "Note 2")
        ));

        when(medicalRecordClient.getMedicalRecordByPatientId(patientId)).thenReturn(historicalDto);

        HistoricalDto result = patientService.getMedicalRecordByPatientId(patientId);

        assertNotNull(result);
        assertEquals(2, result.getMedicalHistoricalDtos().size());
        assertEquals("Note 1", result.getMedicalHistoricalDtos().getFirst().getNote());

    }

    @Test
    void shouldExceptionGetMedicalRecordByPatientId() {
        Long patientId = 4L;
        when(medicalRecordClient.getMedicalRecordByPatientId(patientId))
                .thenThrow(new RuntimeException("MedicalRecord client error"));

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> patientService.getMedicalRecordByPatientId(patientId));

        assertEquals("MedicalRecord client error", exception.getMessage());
    }
}