package org.medilabo.reporting_service.services.riskLevel;

import org.medilabo.reporting_service.model.ReportingPatient;
import org.medilabo.reporting_service.services.interfaces.IBusinessRule;

import java.util.Optional;


public class Borderline implements IBusinessRule {
    @Override
    public Optional<String> evaluate(ReportingPatient rp) {
        System.out.println("# Borderline 1 #");

        // Vérifie que rp n’est pas null pour éviter un NPE
        if (rp == null) {
            return Optional.empty();
        }

        // Condition : entre 2 et 5 déclencheurs et plus de 30 ans
        if (rp.getTriggers() >= 2 && rp.getTriggers() <= 5 && rp.getAge() > 30) {
            System.out.println("# Borderline 2 #");
            return Optional.of("Borderline"); // petite faute d’orthographe ici
        }

        return Optional.empty();
    }
}
