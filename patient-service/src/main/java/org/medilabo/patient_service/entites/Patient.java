package org.medilabo.patient_service.entites;

import jakarta.persistence.*;
import lombok.*;
import org.medilabo.patient_service.entites.enums.EGender;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, length = 50)
    private String firstName;

    @Column(nullable = false, length = 50)
    private String lastName;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Column(nullable = false)
    private LocalDate createAt = LocalDate.now();

    @Enumerated(EnumType.STRING)
    private EGender gender;

    @Column(length = 255)
    private String postalAddress;

    @Column(unique = true, length = 15)
    private String phoneNumber;
}
