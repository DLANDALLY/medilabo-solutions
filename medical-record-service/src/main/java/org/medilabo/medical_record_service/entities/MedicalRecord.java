package org.medilabo.medical_record_service.entities;

import jakarta.persistence.*;
import lombok.*;
import org.medilabo.medical_record_service.model.Patient;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "medical_records")
public class MedicalRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Lob
    private String note;

    private LocalDateTime createdAt = LocalDateTime.now();

    private String createdBy; // ou relation vers Practicien

    private Long  patientId;

    @Transient
    private Patient patient;
}
