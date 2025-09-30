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
							.lastName("TestNone")
							.dateOfBirth(LocalDate.of(1966,12,31))
							.gender(EGender.F)
							.postalAddress("1 Brookside St")
							.phoneNumber("100-222-3333")
							.createAt(LocalDate.now())
							.build(),
					Patient.builder()
							.firstName("Test2")
							.lastName("TestBorderline")
							.dateOfBirth(LocalDate.of(1945,6,24))
							.gender(EGender.M)
							.postalAddress("2 High St")
							.phoneNumber("200-333-4444")
							.createAt(LocalDate.now())
							.build(),
					Patient.builder()
							.firstName("Test3")
							.lastName("TestInDanger")
							.dateOfBirth(LocalDate.of(2004,6,18))
							.gender(EGender.M)
							.postalAddress("3 Club Road")
							.phoneNumber("300-444-5555")
							.createAt(LocalDate.now())
							.build(),
					Patient.builder()
							.firstName("Test4")
							.lastName("TestEarlyOnset")
							.dateOfBirth(LocalDate.of(2002,6,28))
							.gender(EGender.F)
							.postalAddress("4 Valley Dr")
							.phoneNumber("400-555-6666")
							.createAt(LocalDate.now())
							.build()
			);
			patientRepository.saveAll(patients);
		};
	}
}
