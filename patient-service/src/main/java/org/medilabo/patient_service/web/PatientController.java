package org.medilabo.patient_service.web;

import jakarta.validation.Valid;
import org.medilabo.patient_service.dtos.AddPatientDto;
import org.medilabo.patient_service.dtos.UpdatePatientDto;
import org.medilabo.patient_service.entites.Patient;
import org.medilabo.patient_service.service.interfaces.IPatient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("api/v1/patient")
public class PatientController {
    private final IPatient patientService;

    public PatientController(IPatient patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllPatient(){
        List<Patient> patients = patientService.getAllPatients();
        return ResponseEntity.ok(patients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id){
        Patient patient = patientService.getPatientById(id);
        return ResponseEntity.ok(patient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id,@Valid @RequestBody UpdatePatientDto updatePatientDto){
        return ResponseEntity.status(200).body(patientService.updatePatient(id, updatePatientDto));
    }

    @PostMapping("/create")
    public ResponseEntity<Patient> createPatient(@Valid @RequestBody AddPatientDto addPatientDto) {
        Patient created = patientService.createPatient(addPatientDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
//        if (patientService.deletePatient(id))
//            return ResponseEntity.noContent().build();
//        return ResponseEntity.notFound().build();
//    }
}
