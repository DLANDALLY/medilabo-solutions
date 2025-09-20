package org.medilabo.medical_record_service.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class Patient {
    private Long id;

    private String firstName;

    private String lastName;

    private LocalDate dateOfBirth;

    private LocalDate createAt = LocalDate.now();

    private String gender;

    private String postalAddress;

    private String phoneNumber;
}
