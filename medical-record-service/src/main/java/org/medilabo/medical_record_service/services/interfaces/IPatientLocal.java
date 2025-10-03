package org.medilabo.medical_record_service.services.interfaces;

import org.medilabo.medical_record_service.entities.PatientLocal;

import java.util.List;

public interface IPatientLocal {
    List<PatientLocal> getAllPatient();

    PatientLocal getPatientLocalById(Long id);
}
