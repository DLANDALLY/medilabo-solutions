package org.medilabo.reporting_service.config;

import lombok.AllArgsConstructor;
import org.medilabo.reporting_service.model.ReportingPatient;
import org.medilabo.reporting_service.model.Alert;
import org.medilabo.reporting_service.services.interfaces.IBusinessRule;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class RuleEngine {
    private final BusinessRulesProperties properties;

    public Optional<String> evaluate(ReportingPatient rp) {
        System.out.println("# Reporting 3 #");

        for (RuleConfig rule : properties.getRules()) {
            System.out.println("# Reporting 3.1 #");
            try {
                System.out.println("# Reporting 3.2 #");
                // Si la règle correspond
                if (rule.getHandlerClass() != null) {
                    System.out.println("# Reporting 3.3 #");
                    IBusinessRule customRule = (IBusinessRule)
                            Class.forName(rule.getHandlerClass())
                                    .getDeclaredConstructor()
                                    .newInstance();
                    Optional<String> result = customRule.evaluate(rp);
                    if (result.isPresent()) return result;
                } else if (rule.matches(rp)) {
                    System.out.println("# Reporting 3.4 ELSE IF #");
                    return Optional.of(rule.getAlertType());
                }
            } catch (Exception e) {
                System.out.println("# Reporting 3.5 exception #");
                throw new RuntimeException("Erreur lors du chargement de la règle : " + rule.getName(), e);
            }
        }
        return Optional.empty();
    }

    public List<Alert> evaluateAll(ReportingPatient patient) {
        return properties.getRules().stream()
                .filter(rule -> matches(rule, patient))
                .map(rule -> new Alert(rule.getAlertType(), rule.getMessage()))
                .collect(Collectors.toList());
    }

    private boolean matches(RuleConfig rule, ReportingPatient rp) {
        boolean genderOk = "*".equals(rule.getGender()) ||
                rule.getGender().equalsIgnoreCase(rp.getGenre());

        boolean ageOk = true;
        if (rule.getMinAge() != null) ageOk &= rp.getAge() >= rule.getMinAge();
        if (rule.getMaxAge() != null) ageOk &= rp.getAge() <= rule.getMaxAge();

        boolean triggersOk = rule.getMinTriggers() == null ||
                rp.getTriggers() >= rule.getMinTriggers();

        return genderOk && ageOk && triggersOk;
    }
}
