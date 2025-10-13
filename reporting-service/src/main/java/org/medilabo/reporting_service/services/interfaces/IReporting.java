package org.medilabo.reporting_service.services.interfaces;

import org.medilabo.reporting_service.model.ReportingPatient;

public interface IReporting {
    ReportingPatient getReportingPatient(Long id);
}
