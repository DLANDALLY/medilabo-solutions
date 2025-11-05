package org.medilabo.reporting_service.dtos.medicalRecord;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class MedicalHistoricalDto {
    private String id;
    private String note;
}
