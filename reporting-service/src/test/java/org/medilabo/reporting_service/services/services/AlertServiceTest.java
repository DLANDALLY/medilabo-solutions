package org.medilabo.reporting_service.services.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.medilabo.reporting_service.config.RuleEngine;
import org.medilabo.reporting_service.model.ReportingPatient;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AlertServiceTest {
    private AlertService alertService;
    private RuleEngine ruleEngine;

    @BeforeEach
    void setUp() {
        ruleEngine = mock(RuleEngine.class);
        alertService = new AlertService(ruleEngine);
    }

    @Test
    void shouldRetourAlertMessage() {
        ReportingPatient rp = new ReportingPatient();
        when(ruleEngine.evaluate(rp)).thenReturn(Optional.of("Diabetes Alert"));

        String result = alertService.processAlert(rp);

        assertEquals("Diabetes Alert", result);
    }

    @Test
    void ShouldReturnDefaultMessage() {
        // GIVEN
        ReportingPatient rp = new ReportingPatient();
        when(ruleEngine.evaluate(rp)).thenReturn(Optional.empty());

        // WHEN
        String result = alertService.processAlert(rp);

        // THEN
        assertEquals("No alert", result);
        verify(ruleEngine, times(1)).evaluate(rp);
    }
}