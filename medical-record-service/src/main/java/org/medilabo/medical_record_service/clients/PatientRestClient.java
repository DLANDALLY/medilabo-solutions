package org.medilabo.medical_record_service.clients;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.medilabo.medical_record_service.model.Patient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "PATIENT-SERVICE")
public interface PatientRestClient {
    @GetMapping("/api/v1/patient/{id}")
    @CircuitBreaker(name = "patientService", fallbackMethod = "getDefaultPatient")
    Patient findPatientById(@PathVariable Long id);

    @GetMapping("/api/v1/patient/all")
    @CircuitBreaker(name = "patientService", fallbackMethod = "getDefaultPatients")
    List<Patient> allPatients();

    default Patient getDefaultPatient(Long id, Exception exception){
       return Patient.builder()
               .id(id)
               .firstName("Not Vailable")
               .lastName("Not Vailable")
               .gender("Not Vailable")
               .phoneNumber("Not Vailable")
               .postalAddress("Not Vailable")
               .build();
    }

    default List<Patient> getDefaultPatients(Exception exception){
        return List.of();
    }
}
