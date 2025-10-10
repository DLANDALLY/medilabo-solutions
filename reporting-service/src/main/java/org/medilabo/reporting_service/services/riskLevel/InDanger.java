package org.medilabo.reporting_service.services.riskLevel;

import lombok.Getter;
import lombok.Setter;
import org.medilabo.reporting_service.model.ReportingPatient;
import org.medilabo.reporting_service.services.interfaces.IBusinessRule;

import java.util.Objects;
import java.util.Optional;

@Getter @Setter
public class InDanger  {
    private int agePatient = 30;
    private int trigger = 2;


    public Optional<String> evaluate(ReportingPatient rp) {
        // Homme / - 30ans / 3 decl
        // FEMME / - 30ans /4 dch
        if (rp.getAge() < agePatient
                && rp.getTriggers() >= 3
                && Objects.equals(rp.getGenre(),"M")){
            return Optional.of("In Danger");
        } else if (rp.getAge() < agePatient
                && rp.getTriggers() >= 4
                && Objects.equals(rp.getGenre(),"F")) {
            return Optional.of("In Danger");
        }

        return Optional.empty();
    }
}
