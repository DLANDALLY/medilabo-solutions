package org.medilabo.medical_record_service.services;

import lombok.AllArgsConstructor;
import org.medilabo.medical_record_service.dtos.HistoricalDto;
import org.medilabo.medical_record_service.dtos.MedicalHistoricalDto;
import org.medilabo.medical_record_service.dtos.MedicalRecordDto;
import org.medilabo.medical_record_service.entities.MedicalRecord;
import org.medilabo.medical_record_service.model.Patient;
import org.medilabo.medical_record_service.repositories.MedicalRecordRepository;
import org.medilabo.medical_record_service.services.interfaces.IMedicalRecord;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MedicalRecordServiceImpl implements IMedicalRecord {
    private MedicalRecordRepository mongoRepository;
    private final ModelMapper modelMapper;

    @Override public List<MedicalRecord> getAllMedicalRecords() { return mongoRepository.findAll(); }
    @Override public void delete(String id) { mongoRepository.deleteById(id); }

    @Override
    public MedicalRecord createMedicalRecord(MedicalRecordDto medicalRecordDto) {
        if (medicalRecordDto == null)
            throw new NullPointerException("The form cannot be null");
        MedicalRecord medicalRecord = modelMapper.map(medicalRecordDto, MedicalRecord.class);

        return mongoRepository.save(medicalRecord);
    }

    @Override
    public MedicalRecord getMedicalRecordById(String id) {
        if (!mongoRepository.existsById(id))
            throw new NullPointerException("ID does not exist");

        return mongoRepository.findById(id).orElse(null);
    }

    @Override
    public List<HistoricalDto> getAllMedicalHistorical(List<Patient> patientList) {
        List<MedicalRecord> medicalRecords = getAllMedicalRecords();

        return patientList.stream()
                .map(p -> {
                    HistoricalDto historicalDto = new HistoricalDto();
                    historicalDto.setPatient(p);

                    medicalRecords.stream()
                            .filter(mr -> Objects.equals(mr.getPatientId(), p.getId()))
                            .map(mr -> modelMapper.map(mr, MedicalHistoricalDto.class))
                            .forEach(historicalDto.getMedicalHistoricalDtos()::add);

                    return historicalDto;
                })
                .collect(Collectors.toList());
    }
}
