package org.medilabo.medical_record_service.web;

import lombok.AllArgsConstructor;
import org.medilabo.medical_record_service.clients.PatientRestClient;
import org.medilabo.medical_record_service.dtos.HistoricalDto;
import org.medilabo.medical_record_service.dtos.MedicalRecordDto;
import org.medilabo.medical_record_service.entities.MedicalRecord;
import org.medilabo.medical_record_service.model.Patient;
import org.medilabo.medical_record_service.services.interfaces.IMedicalRecord;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/medicalrecord")
@AllArgsConstructor
public class MedicalRecordController {
    private IMedicalRecord medicalRecordService;
    private PatientRestClient patientRestClient;

//    @GetMapping("/{id}")
//    public ResponseEntity<MedicalRecord> getMedicalRecordById(@PathVariable Long id){
//        MedicalRecord medicalRecord = medicalRecordService.getMedicalRecordById(id);
//
//        System.out.println("## ID patient: "+ medicalRecord.getPatientId());
//        Patient patient = patientRestClient.findPatientById(medicalRecord.getPatientId());
//
//        System.out.println("### Patient: "+ patient.getFirstName());
//        medicalRecord.setPatient(patient);
//        return ResponseEntity.ok(medicalRecord);
//    }

    @PostMapping("/create")
    public ResponseEntity<MedicalRecord> createMedicalRecord(@RequestBody MedicalRecordDto medicalRecordDto) {
        MedicalRecord medicalRecord = medicalRecordService.createMedicalRecord(medicalRecordDto);
        return ResponseEntity.status(200).body(medicalRecord);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllMedicalRecord(){
        List<Patient> patients = getAllPatients();
        if (patients.isEmpty())
            return ResponseEntity.noContent().build();

        List<HistoricalDto> medicalRecords = medicalRecordService.getAllMedicalHistorical(patients);
        return ResponseEntity.ok(medicalRecords);
    }

    @GetMapping("/{id}")
    public MedicalRecord getById(@PathVariable String id) { return medicalRecordService.getMedicalRecordById(id); }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) { medicalRecordService.delete(id); }

    private Patient getPatient(Long id){
        return patientRestClient.findPatientById(id);
    }

    private List<Patient> getAllPatients(){
        return patientRestClient.allPatients();
    }
}
