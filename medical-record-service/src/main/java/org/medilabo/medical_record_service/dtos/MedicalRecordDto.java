package org.medilabo.medical_record_service.dtos;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class MedicalRecordDto {
    private String id;
    @Lob
    private String note;
    private LocalDateTime createdAt = LocalDateTime.now();
    @NotBlank
    private Long  patientId;
}
