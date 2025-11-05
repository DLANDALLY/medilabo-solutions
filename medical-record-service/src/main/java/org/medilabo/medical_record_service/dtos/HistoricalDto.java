package org.medilabo.medical_record_service.dtos;

import lombok.*;
import org.medilabo.medical_record_service.entities.PatientLocal;

import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class HistoricalDto {
    private PatientLocal patient;
    private List<MedicalHistoricalDto> medicalHistoricalDtos;
}
