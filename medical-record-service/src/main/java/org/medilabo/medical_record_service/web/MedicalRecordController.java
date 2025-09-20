package org.medilabo.medical_record_service.web;

import lombok.AllArgsConstructor;
import org.medilabo.medical_record_service.entities.MedicalRecord;
import org.medilabo.medical_record_service.services.interfaces.IMedicalRecord;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/medicalrecord")
@AllArgsConstructor
public class MedicalRecordController {
    private IMedicalRecord medicalRecordService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllMedicalRecord(){
        List<MedicalRecord> medicalRecords = medicalRecordService.getAllMedicalRecords();
        return ResponseEntity.ok(medicalRecords);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicalRecord> getMedicalRecordById(@PathVariable Long id){
        MedicalRecord medicalRecord = medicalRecordService.getMedicalRecordById(id);
        return ResponseEntity.ok(medicalRecord);
    }
}
