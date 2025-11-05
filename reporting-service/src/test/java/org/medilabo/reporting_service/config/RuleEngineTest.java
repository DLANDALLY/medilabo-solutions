package org.medilabo.reporting_service.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.medilabo.reporting_service.model.ReportingPatient;
import org.medilabo.reporting_service.model.Rule;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RuleEngineTest {
    @Mock
    private BusinessRulesProperties properties;

    private RuleEngine ruleEngine;

    @BeforeEach
    void setUp() {
        ruleEngine = new RuleEngine(properties);
    }

    @Test
    void shouldReturnAlertType_whenRuleMatches() {
        Rule rule = new Rule();
        rule.setName("InDangerAdult");
        rule.setAlertType("In Danger");
        rule.setHandlerClass(null);

        ReportingPatient patient = new ReportingPatient("M", 40, 3);

        Rule spyRule = Mockito.spy(rule);
        when(spyRule.matches(patient)).thenReturn(true);
        when(properties.getRules()).thenReturn(List.of(spyRule));

        Optional<String> result = ruleEngine.evaluate(patient);

        assertTrue(result.isPresent());
        assertEquals("In Danger", result.get());
    }


    @Test
    void evaluate() {
    }
}