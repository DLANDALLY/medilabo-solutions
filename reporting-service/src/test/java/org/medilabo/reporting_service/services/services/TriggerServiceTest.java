package org.medilabo.reporting_service.services.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.medilabo.reporting_service.config.BusinessTriggersProperties;
import org.medilabo.reporting_service.model.Trigger;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TriggerServiceTest {
    @Mock
    private BusinessTriggersProperties triggersProperties;
    private TriggerService triggerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        triggerService = new TriggerService(triggersProperties);
    }

    @Test
    void countTriggers() {
        List<String> words = List.of("Je", "suis", "Fumeur", "et", "j'ai", "du", "poids");

        List<Trigger> triggerList = triggers().stream()
                .map(Trigger::new)
                .collect(Collectors.toList());

        when(triggersProperties.getTriggers()).thenReturn(triggerList);

        long count = triggerService.countTriggers(words);

        assertEquals(2, count);
    }

    Set<String> triggers(){
        return Set.of(
                "Hémoglobine A1C",
                "Microalbumine",
                "Taille",
                "Poids",
                "Fumeur",
                "Fumeuse",
                "Anormal",
                "Cholestérol",
                "Vertiges",
                "Rechute",
                "Réaction",
                "Anticorps");
    }
}