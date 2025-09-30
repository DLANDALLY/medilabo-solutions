package org.medilabo.medical_record_service.repositories;

import org.medilabo.medical_record_service.entities.MedicalRecord;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalRecordRepository extends MongoRepository<MedicalRecord, String> {
}
