package org.medilabo.medical_record_service.web;

import lombok.AllArgsConstructor;
import org.medilabo.medical_record_service.dtos.HistoricalDto;
import org.medilabo.medical_record_service.dtos.MedicalRecordDto;
import org.medilabo.medical_record_service.entities.MedicalRecord;
import org.medilabo.medical_record_service.services.interfaces.IMedicalRecord;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/medicalrecord")
@AllArgsConstructor
public class MedicalRecordController {
    private IMedicalRecord medicalRecordService;


    //Ah supprimer
    @PostMapping("/create")
    public ResponseEntity<MedicalRecord> createMedicalRecord(@RequestBody MedicalRecordDto medicalRecordDto) {
        MedicalRecord medicalRecord = medicalRecordService.createMedicalRecord(medicalRecordDto);
        return ResponseEntity.status(200).body(medicalRecord);
    }

    //Ah rectifier
    @PostMapping("/{patientId}/add")
    public ResponseEntity<?> addHistoricalNote(@PathVariable Long patientId, @RequestBody MedicalRecordDto medicalRecordDto){
        HistoricalDto historicalDto = medicalRecordService.getMedicalHistoryByPatientId(patientId);
        System.out.println("# See By Patient ID medical ");

        return ResponseEntity.ok(historicalDto);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllMedicalRecord(){
        List<HistoricalDto> medicalRecords = medicalRecordService.getAllMedicalHistorical();
        return ResponseEntity.ok(medicalRecords);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistoricalDto> getMedicalRecordById(@PathVariable Long patientId) {
        return ResponseEntity.ok(medicalRecordService.getMedicalHistoryByPatientId(patientId));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) { medicalRecordService.delete(id); }
}
