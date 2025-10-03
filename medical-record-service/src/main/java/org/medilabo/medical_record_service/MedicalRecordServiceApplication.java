package org.medilabo.medical_record_service;

import org.medilabo.medical_record_service.entities.MedicalRecord;
import org.medilabo.medical_record_service.repositories.MedicalRecordRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class MedicalRecordServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedicalRecordServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(MedicalRecordRepository medicalRecordRepository){
		return args -> {
			List<MedicalRecord> medicalRecords = List.of(
					MedicalRecord.builder()
							.note("Test 1 note description")
							.createdAt(LocalDateTime.now())
							.createdBy("Praticien 1")
							.patientId(1L)
							.build(),
					MedicalRecord.builder()
							.note("Test 2 note description")
							.createdAt(LocalDateTime.now())
							.createdBy("Praticien 2")
							.patientId(2L)
							.build()
			);
			medicalRecordRepository.saveAll(medicalRecords);
		};
	}
}
