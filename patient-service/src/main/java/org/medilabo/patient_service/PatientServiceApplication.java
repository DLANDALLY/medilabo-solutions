package org.medilabo.patient_service;

import org.medilabo.patient_service.config.GloblaConfig;
import org.medilabo.patient_service.dtos.AddPatientDto;
import org.medilabo.patient_service.entites.enums.EGender;
import org.medilabo.patient_service.service.PatientServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
@EnableConfigurationProperties(GloblaConfig.class)
public class PatientServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatientServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(PatientServiceImpl service){
		return args -> {
			AddPatientDto p1 = AddPatientDto.builder()
					.firstName("Test")
					.lastName("TestNone")
					.dateOfBirth(LocalDate.of(1966,12,31))
					.gender(String.valueOf(EGender.F))
					.postalAddress("1 Brookside St")
					.phoneNumber("100-222-3333")
					.createAt(LocalDate.now())
					.build();
			service.createPatient(p1);

			AddPatientDto p2 = AddPatientDto.builder()
					.firstName("Test2")
					.lastName("TestBorderline")
					.dateOfBirth(LocalDate.of(1945,6,24))
					.gender(String.valueOf(EGender.M))
					.postalAddress("2 High St")
					.phoneNumber("200-333-4444")
					.createAt(LocalDate.now())
					.build();
			service.createPatient(p2);

			AddPatientDto p3 = AddPatientDto.builder()
					.firstName("Test3")
					.lastName("TestInDanger")
					.dateOfBirth(LocalDate.of(2004,6,18))
					.gender(String.valueOf(EGender.M))
					.postalAddress("3 Club Road")
					.phoneNumber("300-444-5555")
					.createAt(LocalDate.now())
					.build();
			service.createPatient(p3);

			AddPatientDto p4 = AddPatientDto.builder()
					.firstName("Test4")
					.lastName("TestEarlyOnset")
					.dateOfBirth(LocalDate.of(2002,6,28))
					.gender(String.valueOf(EGender.F))
					.postalAddress("4 Valley Dr")
					.phoneNumber("400-555-6666")
					.createAt(LocalDate.now())
					.build();
			service.createPatient(p4);
		};
	}
}
