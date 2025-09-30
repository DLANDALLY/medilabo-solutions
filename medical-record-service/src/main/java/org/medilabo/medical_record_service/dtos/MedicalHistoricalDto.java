package org.medilabo.medical_record_service.dtos;

import jakarta.persistence.Lob;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MedicalHistoricalDto {
    private String id;
    @Lob
    private String note;
}
