package org.medilabo.reporting_service.dtos.patient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class PatientLocal {
    private Long id;
    private String lastName;
}
