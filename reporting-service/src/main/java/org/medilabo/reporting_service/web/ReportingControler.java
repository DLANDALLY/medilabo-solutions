package org.medilabo.reporting_service.web;

import lombok.AllArgsConstructor;
import org.medilabo.reporting_service.model.ReportingPatient;
import org.medilabo.reporting_service.services.interfaces.IAlert;
import org.medilabo.reporting_service.services.interfaces.IReporting;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/reporting")
@AllArgsConstructor
public class ReportingControler {
    private IAlert testAlert;
    private IReporting reportingService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getReportingPatient(@PathVariable Long id){
        ReportingPatient reportingPatient = reportingService.getReportingById(id);
        return ResponseEntity.ok(reportingPatient);
    }
}
