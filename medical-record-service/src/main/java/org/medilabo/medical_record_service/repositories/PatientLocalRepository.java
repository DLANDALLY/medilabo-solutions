package org.medilabo.medical_record_service.repositories;

import org.medilabo.medical_record_service.entities.PatientLocal;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientLocalRepository extends MongoRepository<PatientLocal, Long> {
}
