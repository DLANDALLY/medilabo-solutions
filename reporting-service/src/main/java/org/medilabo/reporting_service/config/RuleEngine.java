package org.medilabo.reporting_service.config;

import lombok.extern.slf4j.Slf4j;
import org.medilabo.reporting_service.exceptions.RuleEvaluationException;
import org.medilabo.reporting_service.model.ReportingPatient;
import org.medilabo.reporting_service.model.Rule;
import org.medilabo.reporting_service.services.interfaces.IBusinessRule;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class RuleEngine {
    private final BusinessRulesProperties properties;

    public RuleEngine(BusinessRulesProperties properties) {
        this.properties = properties;
    }

    /**
     * Evaluates all configured business rules for a given reporting patient.
     * <p>
     * The method iterates through each rule defined in the configuration and checks whether
     * the patient data satisfies the rule conditions. If a custom handler is defined, it is
     * executed dynamically using reflection; otherwise, the rule's default matching logic is applied.
     * </p>
     *
     * @param reportingPatient the patient data to evaluate against the rules
     * @return an {@link Optional} containing the alert type if a rule is triggered, or an empty {@code Optional} if none match
     * @throws RuleEvaluationException if an error occurs while loading or evaluating a rule
     */
    public Optional<String> evaluate(ReportingPatient reportingPatient) {
        for (Rule rule : properties.getRules()) {
            try {
                if (hasCustomHandler(rule)) {
                    Optional<String> result = executeCustomRule(rule, reportingPatient);
                    if (result.isPresent()) return result;}
                else if (rule.matches(reportingPatient)) {
                    return Optional.of(rule.getAlertType());}

            } catch (Exception e) {
                log.error("Error during rule evaluation: {}", rule.getName(), e);
                throw new RuleEvaluationException("Error while loading rule: "+ rule.getName());
            }
        }
        return Optional.empty();
    }

    /**
     * Checks whether the given rule provides a custom handler
     *
     * @param rule the rule to check
     * @return {@code true} if the rule defines a custom handler, {@code false} otherwise
     */
    private boolean hasCustomHandler(Rule rule) {
        return rule.getHandlerClass() != null && !rule.getHandlerClass().isBlank();
    }

    /**
     * Executes a custom business rule dynamically using reflection
     *
     * @param rule the rule containing the handler class to execute
     * @param reportingPatient the patient data used for rule evaluation
     * @return an {@link Optional} containing the evaluation result, or an empty {@code Optional} if no alert is produced
     * @throws ReflectiveOperationException if an error occurs while instantiating or invoking the custom rule
     */
    private Optional<String> executeCustomRule(Rule rule, ReportingPatient reportingPatient)
            throws ReflectiveOperationException {
        IBusinessRule customRule = (IBusinessRule)
                Class.forName(rule.getHandlerClass())
                        .getDeclaredConstructor()
                        .newInstance();
        return customRule.evaluate(reportingPatient);
    }
}
