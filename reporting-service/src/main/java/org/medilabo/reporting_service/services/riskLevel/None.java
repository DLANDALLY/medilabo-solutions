package org.medilabo.reporting_service.services.riskLevel;

import org.medilabo.reporting_service.model.ReportingPatient;
import org.medilabo.reporting_service.services.interfaces.IBusinessRule;

import java.util.Optional;


public class None {

    public Optional<String> evaluate(ReportingPatient rp) {
        return Optional.empty();
    }
}
