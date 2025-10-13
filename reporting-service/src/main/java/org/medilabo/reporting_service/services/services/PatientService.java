package org.medilabo.reporting_service.services.services;

import org.medilabo.reporting_service.client.IPatientClient;
import org.medilabo.reporting_service.dtos.medicalRecord.HistoricalDto;
import org.medilabo.reporting_service.dtos.patient.PatientDto;
import org.medilabo.reporting_service.model.Patient;
import org.medilabo.reporting_service.services.interfaces.IPatient;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService implements IPatient {
    private final ModelMapper modelMapper;
    private final IPatientClient patientClient;

    public PatientService(ModelMapper modelMapper, IPatientClient patientClient) {
        this.modelMapper = modelMapper;
        this.patientClient = patientClient;
    }

    @Override
    public PatientDto getPatientDto(Long id){
        System.out.println("# GET PATIENT DTO #");
        Patient patient = patientClient.getPatientById(id);
        System.out.println("## Patient "+ patient);
        return modelMapper.map(patient, PatientDto.class);
    }

    @Override
    public HistoricalDto getMedicalRecordByPatientId(Long id) {
        System.out.println("# GET MEDICAL #");
        HistoricalDto historicalDto = patientClient.getMedicalRecordByPatientId(id);
        System.out.println("## Medical -> "+ historicalDto);
        return historicalDto;
    }


}
