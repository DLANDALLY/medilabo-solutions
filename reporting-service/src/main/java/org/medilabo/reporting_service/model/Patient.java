package org.medilabo.reporting_service.model;

import lombok.*;
import org.medilabo.reporting_service.model.enums.EGender;

import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder @ToString
public class Patient {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private LocalDate createAt;
    private EGender gender;
    private String postalAddress;
    private String phoneNumber;
}
