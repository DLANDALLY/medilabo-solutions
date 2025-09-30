package org.medilabo.medical_record_service.dtos;

import lombok.*;
import org.medilabo.medical_record_service.model.Patient;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class HistoricalDto {
    private Patient patient;
    private List<MedicalHistoricalDto> medicalHistoricalDtos = new ArrayList<>();;
}
