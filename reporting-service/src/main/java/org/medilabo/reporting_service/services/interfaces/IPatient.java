package org.medilabo.reporting_service.services.interfaces;

import org.medilabo.reporting_service.dtos.medicalRecord.HistoricalDto;
import org.medilabo.reporting_service.dtos.patient.PatientDto;

public interface IPatient {
    PatientDto getPatientDto(Long id);

    HistoricalDto getMedicalRecordByPatientId(Long id);
}
