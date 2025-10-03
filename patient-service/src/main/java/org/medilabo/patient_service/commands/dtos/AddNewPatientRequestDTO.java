package org.medilabo.patient_service.commands.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class AddNewPatientRequestDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private LocalDate createAt = LocalDate.now();
    private String gender;
    private String postalAddress;
    private String phoneNumber;
}
