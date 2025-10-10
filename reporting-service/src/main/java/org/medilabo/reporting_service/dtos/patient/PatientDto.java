package org.medilabo.reporting_service.dtos.patient;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter @Builder
public class PatientDto {
    private Long id;
    private LocalDate dateOfBirth;
    private String gender;
}
