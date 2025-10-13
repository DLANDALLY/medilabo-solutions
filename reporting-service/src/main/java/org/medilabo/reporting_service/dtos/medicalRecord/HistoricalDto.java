package org.medilabo.reporting_service.dtos.medicalRecord;

import lombok.*;
import org.medilabo.reporting_service.dtos.patient.PatientLocal;

import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class HistoricalDto {
    private PatientLocal patient;
    private List<MedicalHistoricalDto> medicalHistoricalDtos;
}
