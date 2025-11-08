package org.medilabo.medical_record_service.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.medilabo.medical_record_service.entities.PatientLocal;
import org.medilabo.medical_record_service.repositories.PatientLocalRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PatientLocalServiceImplTest {
    @Mock
    private PatientLocalRepository patientRepository;

    @InjectMocks
    private PatientLocalServiceImpl patientLocalService;

    private PatientLocal patient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        patient = new PatientLocal();
        patient.setId(2L);
        patient.setLastName("TestBorderline");
    }

    @Test
    void shouldReturnPatient_whenExists() {
        when(patientRepository.findById(2L)).thenReturn(Optional.of(patient));

        PatientLocal result = patientLocalService.getPatientLocalById(2L);

        assertNotNull(result);
        assertEquals(2L, result.getId());
        assertEquals("TestBorderline", result.getLastName());
    }

    @Test
    void shouldThrowException_whenNotFound() {
        when(patientRepository.findById(987L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> patientLocalService.getPatientLocalById(99L));
    }

    @Test
    void shouldReturnTrueWhenPatientExists() {
        Long patientId = 1L;
        when(patientRepository.existsById(patientId)).thenReturn(true);

        boolean result = patientLocalService.existingPatient(patientId);

        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenPatientDoesNotExist() {
        Long patientId = 987L;
        when(patientRepository.existsById(patientId)).thenReturn(false);

        boolean result = patientLocalService.existingPatient(patientId);

        assertFalse(result);
    }

}