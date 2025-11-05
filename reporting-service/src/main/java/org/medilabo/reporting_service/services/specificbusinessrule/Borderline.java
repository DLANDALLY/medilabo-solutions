package org.medilabo.reporting_service.services.specificbusinessrule;

import org.medilabo.reporting_service.model.ReportingPatient;
import org.medilabo.reporting_service.services.interfaces.IBusinessRule;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Borderline implements IBusinessRule {
    @Override
    public Optional<String> evaluate(ReportingPatient rp) {
        if (rp == null)
            return Optional.empty();

        if (rp.getTriggers() >= 2 && rp.getTriggers() <= 5 && rp.getAge() > 30)
            return Optional.of("Borderline");

        return Optional.empty();
    }
}
