package org.medilabo.medical_record_service.services.interfaces;

import org.medilabo.medical_record_service.entities.PatientLocal;

public interface IPatientLocal {
    PatientLocal getPatientLocalById(Long id);

    boolean existingPatient(Long patientId);
}
