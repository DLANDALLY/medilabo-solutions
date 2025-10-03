package org.medilabo.medical_record_service.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder @ToString
@Document(collection = "medicals")
public class MedicalRecord {
    @Id
    private String id;
    private String note;
    private LocalDateTime createdAt;
    private String createdBy; // ou relation vers Practicien
    private Long  patientId;
}
