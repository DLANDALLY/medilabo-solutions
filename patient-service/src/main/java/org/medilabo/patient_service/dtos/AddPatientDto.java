package org.medilabo.patient_service.dtos;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class AddPatientDto {
    private Long id;

    @NotBlank(message = "First name cannot be empty")
    @Size(max = 50, message = "First name cannot exceed 50 characters")
    private String firstName;

    @NotBlank(message = "Last name cannot be empty")
    @Size(max = 50, message = "Last name cannot exceed 50 characters")
    private String lastName;

    @NotNull(message = "Date of birth is required")
    private LocalDate dateOfBirth;

    private LocalDate createAt = LocalDate.now();

    @NotBlank(message = "Gender is required")
    private String gender;

    @Size(max = 255, message = "Postal address cannot exceed 255 characters")
    @Nullable
    private String postalAddress;

    @Pattern(
            regexp = "^\\+?[0-9\\s-]{10,20}$",
            message = "Phone number must contain only digits, spaces or dashes")
    @Nullable
    private String phoneNumber;
}
