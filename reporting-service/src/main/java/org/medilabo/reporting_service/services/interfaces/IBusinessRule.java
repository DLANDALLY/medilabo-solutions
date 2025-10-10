package org.medilabo.reporting_service.services.interfaces;

import org.medilabo.reporting_service.model.ReportingPatient;

import java.util.Optional;

public interface IBusinessRule {
    Optional<String> evaluate(ReportingPatient rp);
}
