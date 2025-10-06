package org.medilabo.patient_service.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.medilabo.patient_service.dtos.AddPatientDto;
import org.medilabo.patient_service.dtos.PatientEvent;
import org.medilabo.patient_service.dtos.UpdatePatientDto;
import org.medilabo.patient_service.entites.Patient;
import org.medilabo.patient_service.entites.enums.EGender;
import org.medilabo.patient_service.exception.ResourceNotFoundException;
import org.medilabo.patient_service.repositories.PatientRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PatientServiceImplTest {
    @Mock
    private PatientRepository patientRepository;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private KafkaTemplate<String, PatientEvent> kafkaTemplate;
    @InjectMocks
    private PatientServiceImpl patientService;
    private Patient patient;

    @BeforeEach
    void setUp() {
        patient = new Patient();
        patient.setId(1L);
        patient.setFirstName("Test");
        patient.setLastName("TestNone");
        patient.setGender(EGender.F);
        patient.setDateOfBirth(LocalDate.of(1990,12,25));
        patient.setCreateAt(LocalDate.now());
    }

    @Test
    void shouldAllPatients() {
        when(patientRepository.findAll()).thenReturn(List.of(new Patient(), new Patient()));
        List<Patient> result = patientService.getAllPatients();

        assertEquals(2, result.size());
    }

    @Test
    void shouldGetAPatientById(){
        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));
        Patient result = patientService.getPatientById(1L);

        assertEquals("Test", result.getFirstName());
    }

    @Test
    void shouldThrowExceptionWhenNotFound() {
        when(patientRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> patientService.getPatientById(99L));
    }

    @Test
    void shouldSaveAndPublishEvent() {
        AddPatientDto addPatientDto = getAddPatientDto();
        // GIVEN
        when(modelMapper.map(addPatientDto, Patient.class)).thenReturn(patient);
        when(patientRepository.findAll()).thenReturn(Collections.emptyList());
        when(patientRepository.save(patient)).thenReturn(patient);

        // WHEN
        Patient result = patientService.createPatient(addPatientDto);

        // THEN
        assertNotNull(result);
        assertEquals("Test", result.getFirstName());
        verify(kafkaTemplate).send(eq("patient-events"), any(PatientEvent.class));
    }

    @Test
    void shouldUpdateAndPublish() {
        UpdatePatientDto dto = getUpdatePatientDto();
        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));
        when(patientRepository.save(any())).thenReturn(patient);

        Patient result = patientService.updatePatient(1L, dto);

        assertNotNull(result);
        verify(kafkaTemplate).send(eq("patient-events"), any(PatientEvent.class));
    }

    AddPatientDto getAddPatientDto(){
        return AddPatientDto.builder()
                .id(998L)
                .firstName("FirstTest")
                .lastName("LastTest")
                .dateOfBirth(LocalDate.of(1990,12,25))
                .gender("F")
                .createAt(LocalDate.now())
                .build();
    }

    UpdatePatientDto getUpdatePatientDto(){
        return UpdatePatientDto.builder()
                .firstName("TwoTest")
                .lastName("TestNone")
                .dateOfBirth(LocalDate.of(1990,12,25))
                .gender("F")
                .phoneNumber("0614284769")
                .postalAddress("18 rue de la paix")
                .build();
    }

}