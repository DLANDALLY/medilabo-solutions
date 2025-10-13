package org.medilabo.reporting_service.services.services;

import lombok.AllArgsConstructor;
import org.medilabo.reporting_service.client.IMedicalRecordClient;
import org.medilabo.reporting_service.dtos.medicalRecord.HistoricalDto;
import org.medilabo.reporting_service.dtos.medicalRecord.MedicalHistoricalDto;
import org.medilabo.reporting_service.dtos.medicalRecord.NoteHistoryDto;
import org.medilabo.reporting_service.dtos.patient.PatientDto;
import org.medilabo.reporting_service.model.ReportingPatient;
import org.medilabo.reporting_service.services.interfaces.IPatient;
import org.medilabo.reporting_service.services.interfaces.IReporting;
import org.medilabo.reporting_service.services.interfaces.ITrigger;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class ReportingServiceImpl implements IReporting {
    private final IMedicalRecordClient medicalRecordClient;
    private final ITrigger triggerService;
    private final IPatient patientService;

    @Override
    public ReportingPatient getReportingPatient(Long id) {
        PatientDto patientDto = patientService.getPatientDto(id);
        HistoricalDto historicalDto = patientService.getMedicalRecordByPatientId(id);
        System.out.println("## Patient DTO "+ patientDto );
        System.out.println("## Medical DTO "+ historicalDto );

        return new ReportingPatient(
                patientDto.getGender(),
                getAge(patientDto.getDateOfBirth()),
                getTriggersNumber(historicalDto.getMedicalHistoricalDtos()));
    }

    private int getAge(LocalDate localDate) {
        System.out.println("## LocalDate "+ localDate.toString());
        return Period.between(localDate, LocalDate.now()).getYears();
    }

    private int getTriggersNumber(List<MedicalHistoricalDto> notes) {
        List<String> words = notes.stream()
                .flatMap(n -> getExtractWords(n.getNote()).stream())
                .toList();

        long triggerCount = triggerService.countTriggers(words);
        return Math.toIntExact(triggerCount);
    }

    private List<String> getExtractWords(String text) {
        return Arrays.stream(text.replaceAll("[^a-zA-ZÀ-ÿ ]", "")
                        .toLowerCase()
                        .split("\\s+"))
                .filter(s -> !s.isBlank())
                .toList();
    }
}
