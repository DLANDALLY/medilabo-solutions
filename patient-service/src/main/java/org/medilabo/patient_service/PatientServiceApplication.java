package org.medilabo.patient_service;

import org.medilabo.patient_service.config.GloblaConfig;
import org.medilabo.patient_service.entites.Patient;
import org.medilabo.patient_service.entites.enums.EGender;
import org.medilabo.patient_service.repositories.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties(GloblaConfig.class)
public class PatientServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatientServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(PatientRepository patientRepository){
		return args -> {
			List<Patient> patients = List.of(
					Patient.builder()
							.firstName("Test")
							.lastName("LastTest")
							.dateOfBirth(LocalDate.of(2025,9,18))
							.gender(EGender.M)
							.postalAddress("18 rue de la paix")
							.phoneNumber("0112234558")
							.createAt(LocalDate.now())
							.build(),
					Patient.builder()
							.firstName("Test2")
							.lastName("LastTest2")
							.dateOfBirth(LocalDate.of(2025,9,18))
							.gender(EGender.F)
							.postalAddress("12 rue de la paix")
							.phoneNumber("0112234556")
							.createAt(LocalDate.now())
							.build()
			);
			patientRepository.saveAll(patients);
		};
	}
}
