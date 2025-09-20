package org.medilabo.medical_record_service.repositories;

import org.medilabo.medical_record_service.entities.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
}
