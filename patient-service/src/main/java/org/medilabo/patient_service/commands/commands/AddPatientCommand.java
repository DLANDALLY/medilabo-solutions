package org.medilabo.patient_service.commands.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
//import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.medilabo.patient_service.entites.enums.EGender;

import java.time.LocalDate;

@Getter @AllArgsConstructor
public class AddPatientCommand {
    //@TargetAggregateIdentifier
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private LocalDate createAt;
    private EGender gender;
    private String postalAddress;
    private String phoneNumber;
}
