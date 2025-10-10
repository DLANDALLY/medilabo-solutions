package org.medilabo.reporting_service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class ReportingPatient {
    private String genre;
    private int age;
    private int triggers;
}