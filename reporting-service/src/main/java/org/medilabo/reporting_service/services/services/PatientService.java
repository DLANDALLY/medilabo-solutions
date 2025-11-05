package org.medilabo.reporting_service.services.services;

import org.medilabo.reporting_service.client.IMedicalRecordClient;
import org.medilabo.reporting_service.client.IPatientClient;
import org.medilabo.reporting_service.dtos.medicalRecord.HistoricalDto;
import org.medilabo.reporting_service.dtos.patient.PatientDto;
import org.medilabo.reporting_service.model.Patient;
import org.medilabo.reporting_service.services.interfaces.IPatient;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
public class PatientService implements IPatient {
    private final ModelMapper modelMapper;
    private final IPatientClient patientClient;
    private final IMedicalRecordClient medicalRecordClient;

    public PatientService(ModelMapper modelMapper, IPatientClient patientClient, IMedicalRecordClient medicalRecordClient) {
        this.modelMapper = modelMapper;
        this.patientClient = patientClient;
        this.medicalRecordClient = medicalRecordClient;
    }

    @Override
    public PatientDto getPatientDto(Long id){
        Patient patient = patientClient.getPatientById(id);
        return modelMapper.map(patient, PatientDto.class);
    }

    @Override
    public HistoricalDto getMedicalRecordByPatientId(Long id) {
        return medicalRecordClient.getMedicalRecordByPatientId(id);
    }
}
