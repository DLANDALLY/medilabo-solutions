package org.medilabo.reporting_service.services.riskLevel;

import org.medilabo.reporting_service.model.ReportingPatient;
import org.medilabo.reporting_service.services.interfaces.IBusinessRule;

import java.util.Optional;

public class EarlyOnset  {

    public Optional<String> evaluate(ReportingPatient rp) {
        // Homme / - 30ans / 5 ou + dch
        // Femme / -30ans  / 7 ou + dch
        // Patient + 30 / 8 ou + dch
        int age = rp.getAge();
        String genre = rp.getGenre();
        int triggers = rp.getTriggers();

        boolean isMale = "M".equalsIgnoreCase(genre);
        boolean isFemale = "F".equalsIgnoreCase(genre);

        if (age <= 30) {
            if ((isMale && triggers >= 5) || (isFemale && triggers >= 7)) {
                return Optional.of("Early Onset");
            }
        } else if (triggers >= 8) {
            return Optional.of("Early Onset");
        }

        return Optional.empty();
    }
}
