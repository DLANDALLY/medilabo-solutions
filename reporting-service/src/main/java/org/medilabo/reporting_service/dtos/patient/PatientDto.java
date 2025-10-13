package org.medilabo.reporting_service.dtos.patient;

import lombok.*;

import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Builder @ToString
public class PatientDto {
    private Long id;
    private LocalDate dateOfBirth;
    private String gender;
}
