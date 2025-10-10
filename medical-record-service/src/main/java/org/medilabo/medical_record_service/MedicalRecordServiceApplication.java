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
							.note("Le patient déclare qu'il 'se sent très bien' Poids égal ou inférieur au poids recommandé")
							.createdAt(LocalDateTime.now())
							.createdBy("Praticien 1")
							.patientId(1L)
							.build(),
					MedicalRecord.builder()
							.note("Le patient déclare qu'il ressent beaucoup de stress au travail Il se plaint également que son audition est anormale dernièrement")
							.createdAt(LocalDateTime.now())
							.createdBy("Praticien 1")
							.patientId(2L)
							.build(),
					MedicalRecord.builder()
							.note("Le patient déclare avoir fait une réaction aux médicaments au cours des 3 derniers mois Il remarque également que son audition continue d'être anormale")
							.createdAt(LocalDateTime.now())
							.createdBy("Praticien 1")
							.patientId(2L)
							.build(),
					MedicalRecord.builder()
							.note("Le patient déclare qu'il fume depuis peu")
							.createdAt(LocalDateTime.now())
							.createdBy("Praticien 1")
							.patientId(3L)
							.build(),
					MedicalRecord.builder()
							.note("Le patient déclare qu'il est fumeur et qu'il a cessé de fumer l'année dernière Il se plaint également de crises d’apnée respiratoire anormales Tests de laboratoire indiquant un taux de cholestérol LDL élevé")
							.createdAt(LocalDateTime.now())
							.createdBy("Praticien 1")
							.patientId(3L)
							.build(),
					MedicalRecord.builder()
							.note("Le patient déclare qu'il lui est devenu difficile de monter les escaliers Il se plaint également d’être essoufflé Tests de laboratoire indiquant que les anticorps sont élevés Réaction aux médicaments")
							.createdAt(LocalDateTime.now())
							.createdBy("Praticien 2")
							.patientId(4L)
							.build(),
					MedicalRecord.builder()
							.note("Le patient déclare qu'il a mal au dos lorsqu'il reste assis pendant longtemps")
							.createdAt(LocalDateTime.now())
							.createdBy("Praticien 2")
							.patientId(4L)
							.build(),
					MedicalRecord.builder()
							.note("Le patient déclare avoir commencé à fumer depuis peut Hémoglobine A1C supérieure au niveau recommandé")
							.createdAt(LocalDateTime.now())
							.createdBy("Praticien 2")
							.patientId(4L)
							.build(),
					MedicalRecord.builder()
							.note("Taille, Poids, Cholestérol, Vertige et Réaction")
							.createdAt(LocalDateTime.now())
							.createdBy("Praticien 2")
							.patientId(4L)
							.build()
			);
			medicalRecordRepository.saveAll(medicalRecords);

		};
	}
}
