package org.medilabo.medical_record_service.web;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.medilabo.medical_record_service.dtos.HistoricalDto;
import org.medilabo.medical_record_service.dtos.MedicalHistoricalDto;
import org.medilabo.medical_record_service.services.interfaces.IMedicalRecord;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/medicalrecord")
@AllArgsConstructor
public class MedicalRecordController {
    private IMedicalRecord medicalRecordService;

    @GetMapping("/{patientId}")
    public ResponseEntity<HistoricalDto> getMedicalRecordById(@PathVariable Long patientId) {
        return ResponseEntity.ok(medicalRecordService.getMedicalHistoryByPatientId(patientId));
    }

    @PostMapping("/{patientId}/add")
    public ResponseEntity<?> addHistoricalNote(@PathVariable Long patientId, @Valid @RequestBody MedicalHistoricalDto mhd){
        HistoricalDto historicalDto = medicalRecordService.addNewNote(patientId, mhd);
        return ResponseEntity.ok(historicalDto);
    }
}
