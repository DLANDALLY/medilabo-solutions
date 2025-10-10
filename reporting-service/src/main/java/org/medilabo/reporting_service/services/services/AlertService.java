package org.medilabo.reporting_service.services.services;

import lombok.RequiredArgsConstructor;
import org.medilabo.reporting_service.config.RuleEngine;
import org.medilabo.reporting_service.model.ReportingPatient;
import org.medilabo.reporting_service.services.interfaces.IAlert;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AlertService implements IAlert {
    private final RuleEngine ruleEngine;

//    public List<Alert> processPatient(ReportingPatient patient) {
//        return ruleEngine.evaluateAll(patient);
//    }

    @Override
    public String processAlert(ReportingPatient rp) {
        System.out.println("# Reporting 2 #");
        return ruleEngine.evaluate(rp).orElse("No alert");
    }
}
