package org.medilabo.reporting_service.services.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.medilabo.reporting_service.dtos.medicalRecord.HistoricalDto;
import org.medilabo.reporting_service.dtos.medicalRecord.MedicalHistoricalDto;
import org.medilabo.reporting_service.dtos.patient.PatientDto;
import org.medilabo.reporting_service.model.ReportingPatient;
import org.medilabo.reporting_service.services.interfaces.IAlert;
import org.medilabo.reporting_service.services.interfaces.IPatient;
import org.medilabo.reporting_service.services.interfaces.ITrigger;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ReportingServiceImplTest {
    @Mock
    private ITrigger triggerService;

    @Mock
    private IPatient patientService;

    @Mock
    private IAlert alertService;

    @InjectMocks
    private ReportingServiceImpl reportingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getReportingById() {
        Long patientId = 1L;
        PatientDto mockPatient = new PatientDto();
        mockPatient.setGender("M");
        mockPatient.setDateOfBirth(LocalDate.now().minusYears(40));

        MedicalHistoricalDto note1 = new MedicalHistoricalDto();
        note1.setNote("Patient has dizziness and high blood pressure.");

        MedicalHistoricalDto note2 = new MedicalHistoricalDto();
        note2.setNote("No major issues but occasional stress");

        HistoricalDto mockHistorical = new HistoricalDto();
        mockHistorical.setMedicalHistoricalDtos(List.of(note1, note2));

        when(patientService.getPatientDto(patientId)).thenReturn(mockPatient);
        when(patientService.getMedicalRecordByPatientId(patientId)).thenReturn(mockHistorical);
        when(triggerService.countTriggers(anyList())).thenReturn(5L);
        when(alertService.processAlert(any(ReportingPatient.class))).thenReturn("In Danger");

        ReportingPatient result = reportingService.getReportingById(patientId);

        assertNotNull(result);
        assertEquals("M", result.getGenre());
        assertEquals(40, result.getAge());
        assertEquals(5, result.getTriggers());
        assertEquals("In Danger", result.getAlert());
    }
}