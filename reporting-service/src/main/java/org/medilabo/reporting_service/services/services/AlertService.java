package org.medilabo.reporting_service.services.services;

import org.medilabo.reporting_service.config.RuleEngine;
import org.medilabo.reporting_service.model.ReportingPatient;
import org.medilabo.reporting_service.services.interfaces.IAlert;
import org.springframework.stereotype.Service;


@Service
public class AlertService implements IAlert {
    private final RuleEngine ruleEngine;

    public AlertService(RuleEngine ruleEngine) {
        this.ruleEngine = ruleEngine;
    }

    @Override
    public String processAlert(ReportingPatient rp) {
        return ruleEngine.evaluate(rp).orElse("No alert");
    }
}
