package org.medilabo.reporting_service.services.services;

import lombok.AllArgsConstructor;
import org.medilabo.reporting_service.dtos.medicalRecord.HistoricalDto;
import org.medilabo.reporting_service.dtos.medicalRecord.MedicalHistoricalDto;
import org.medilabo.reporting_service.dtos.patient.PatientDto;
import org.medilabo.reporting_service.model.ReportingPatient;
import org.medilabo.reporting_service.services.interfaces.IAlert;
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
    private final ITrigger triggerService;
    private final IPatient patientService;
    private final IAlert alertService;

    /**
     * Retrieves a by its ID and processes any relevant alerts
     *
     * <p>This method constructs a {@code ReportingPatient} object using patient data and medical history,
     * calculates triggers, then applies alert rules to generate an alert message, which is added to the object
     *
     * @param id the unique identifier of the patient
     * @return a {@code ReportingPatient} with calculated age, trigger count, and processed alert
     */
    @Override
    public ReportingPatient getReportingById(Long id){
        ReportingPatient reportingPatient = createReportingPatient(id);
        String alert = alertService.processAlert(reportingPatient);

        reportingPatient.setAlert(alert);
        return reportingPatient;
    }

    /**
     * Builds an object from the patient's data and medical history
     * This method retrieves patient information and medical records, calculates the patient's age
     * and the number of trigger words, then assembles them into a {@code ReportingPatient} instance
     *
     * @param id the unique identifier of the patient
     * @return a populated {@code ReportingPatient} containing gender, age, and trigger count
     */
    private ReportingPatient createReportingPatient(Long id) {
        PatientDto patientDto = patientService.getPatientDto(id);
        HistoricalDto historicalDto = patientService.getMedicalRecordByPatientId(id);

        int age = getAge(patientDto.getDateOfBirth());
        int triggerNumber = getTriggersNumber(historicalDto.getMedicalHistoricalDtos());

        return new ReportingPatient(patientDto.getGender(), age, triggerNumber);
    }

    /**
     * Calculates the patient's age based on their date of birth
     * @param localDate the patient's date of birth
     * @return the patient's age in years
     * @throws IllegalArgumentException if the provided date is null
     */
    private int getAge(LocalDate localDate) {
        if (localDate == null) throw new IllegalArgumentException("The LocalDate argument cannot be null");
        return Period.between(localDate, LocalDate.now()).getYears();
    }

    /**
     * Counts the number of trigger words found in the patient's medical history
     * @param notes the list of the patient's medical history entries
     * @return the number of detected triggers
     * @throws IllegalArgumentException if the patient's medical history is empty or missing
     */
    private int getTriggersNumber(List<MedicalHistoricalDto> notes) {
        if (notes.isEmpty()) throw new IllegalArgumentException("The patient's medical history is empty or missing");

        List<String> words = notes.stream()
                .flatMap(n -> getExtractWords(n.getNote()).stream())
                .toList();

        long triggerCount = triggerService.countTriggers(words);
        return Math.toIntExact(triggerCount);
    }

    /**
     * Extracts and cleans words from a medical note by removing punctuation
     * and special characters, converting all text to lowercase, and splitting it into words
     * @param note the raw text of the patient's medical history
     * @return a list of cleaned words extracted from the note
     * @throws IllegalArgumentException if the note is null or blank
     */
    private List<String> getExtractWords(String note) {
        if (note == null || note.isBlank()) throw new IllegalArgumentException("The patient's medical history is empty or missing");

        return Arrays.stream(note
                        .replaceAll("[^a-zA-ZÀ-ÿ ]", "")
                        .toLowerCase()
                        .split("\\s+"))
                .filter(s -> !s.isBlank())
                .toList();
    }
}
