package org.medilabo.medical_record_service.services;

import lombok.AllArgsConstructor;
import org.medilabo.medical_record_service.dtos.HistoricalDto;
import org.medilabo.medical_record_service.dtos.MedicalHistoricalDto;
import org.medilabo.medical_record_service.dtos.MedicalRecordDto;
import org.medilabo.medical_record_service.entities.MedicalRecord;
import org.medilabo.medical_record_service.entities.PatientLocal;
import org.medilabo.medical_record_service.model.Patient;
import org.medilabo.medical_record_service.repositories.MedicalRecordRepository;
import org.medilabo.medical_record_service.repositories.PatientLocalRepository;
import org.medilabo.medical_record_service.services.interfaces.IMedicalRecord;
import org.medilabo.medical_record_service.services.interfaces.IPatientLocal;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MedicalRecordServiceImpl implements IMedicalRecord {
    private MedicalRecordRepository mongoRepository;
    private final PatientLocalRepository patientLocalRepository;
    private final ModelMapper modelMapper;
    private final IPatientLocal patientService;

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
    public List<HistoricalDto> getAllMedicalHistorical() {
        List<MedicalRecord> medicalRecords = getAllMedicalRecords();
        List<PatientLocal> patientList = patientService.getAllPatient();
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

    @Override
    public HistoricalDto getMedicalHistoryByPatientId(Long patientId) {
        List<MedicalRecord> medicalRecords = mongoRepository.findAll()
                .stream()
                .filter(mr -> mr.getPatientId().equals(patientId))
                .toList();

        List<MedicalHistoricalDto> mhdList = medicalRecords.stream().map(mr -> {
            MedicalHistoricalDto mhd = new MedicalHistoricalDto();
            mhd.setId(mr.getId());
            mhd.setNote(mr.getNote());
            return mhd;
        }) .toList();

        PatientLocal patientLocal = patientService.getPatientLocalById(patientId);
        return new HistoricalDto(patientLocal, mhdList);
    }
}
