package org.medilabo.medical_record_service.entities;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Document(collection = "patients_local")
public class PatientLocal {
    @Id
    private Long id;
    private String lastName;
}
