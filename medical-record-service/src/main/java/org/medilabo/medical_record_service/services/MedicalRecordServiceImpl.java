package org.medilabo.medical_record_service.services;

import lombok.AllArgsConstructor;
import org.medilabo.medical_record_service.dtos.HistoricalDto;
import org.medilabo.medical_record_service.dtos.MedicalHistoricalDto;
import org.medilabo.medical_record_service.entities.MedicalRecord;
import org.medilabo.medical_record_service.entities.PatientLocal;
import org.medilabo.medical_record_service.repositories.MedicalRecordRepository;
import org.medilabo.medical_record_service.services.interfaces.IMedicalRecord;
import org.medilabo.medical_record_service.services.interfaces.IPatientLocal;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MedicalRecordServiceImpl implements IMedicalRecord {
    private MedicalRecordRepository medicalRepository;
    private final ModelMapper modelMapper;
    private final IPatientLocal patientService;

//    @Override
//    public List<HistoricalDto> getAllMedicalHistorical() {
//        List<MedicalRecord> medicalRecords = getAllMedicalRecords();
//        List<PatientLocal> patientList = patientService.getAllPatient();
//        return patientList.stream()
//                .map(p -> {
//                    HistoricalDto historicalDto = new HistoricalDto();
//                    historicalDto.setPatient(p);
//                    medicalRecords.stream()
//                            .filter(mr -> Objects.equals(mr.getPatientId(), p.getId()))
//                            .map(mr -> modelMapper.map(mr, MedicalHistoricalDto.class))
//                            .forEach(historicalDto.getMedicalHistoricalDtos()::add);
//                    return historicalDto;
//                })
//                .collect(Collectors.toList());
//    }

    @Override
    public HistoricalDto getMedicalHistoryByPatientId(Long patientId){
        PatientLocal patientLocal = patientService.getPatientLocalById(patientId);
        List<MedicalRecord> medicalRecords = medicalRepository.findByPatientId(patientId);
        List<MedicalHistoricalDto> mhdDtos = medicalRecords.stream()
                .map(mhd -> modelMapper.map(mhd, MedicalHistoricalDto.class))
                .toList();

        return new HistoricalDto(patientLocal, mhdDtos);
    }

    @Override
    public HistoricalDto addNewNote(Long patientId, MedicalHistoricalDto medicalHistoricalDto) {
        HistoricalDto historicalDto = getMedicalHistoryByPatientId(patientId);

        if (historicalDto.getMedicalHistoricalDtos() == null)
            historicalDto.setMedicalHistoricalDtos(new ArrayList<>());

        createMedicalRecord(patientId, medicalHistoricalDto);
        return historicalDto;
    }

    private void createMedicalRecord(Long patientId, MedicalHistoricalDto mhd){
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setNote(mhd.getNote());
        medicalRecord.setCreatedBy("Praticien 1");
        medicalRecord.setCreatedAt(LocalDateTime.now());
        medicalRecord.setPatientId(patientId);

        medicalRepository.save(medicalRecord);
    }

//    @Override
//    public MedicalRecord createMedicalRecord(MedicalRecord medicalRecord) {
//        if (medicalRecord == null)
//            throw new NullPointerException("The form cannot be null");
//        return medicalRepository.save(medicalRecord);
//    }

    private List<MedicalRecord> getAllMedicalRecords() { return medicalRepository.findAll(); }

}
