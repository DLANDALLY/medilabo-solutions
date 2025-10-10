package org.medilabo.reporting_service.web;

import lombok.AllArgsConstructor;
import org.medilabo.reporting_service.model.ReportingPatient;
import org.medilabo.reporting_service.services.interfaces.IAlert;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/reporting")
@AllArgsConstructor
public class ReportingControler {
    private IAlert testAlert;

    @GetMapping("/all")
    public ResponseEntity<?> getRepporting(){
        System.out.println("# Reporting 1 #");
        String messageAlert = testAlert.processAlert(new ReportingPatient("F", 25,4));
        System.out.println("# message #"+ messageAlert);
        return ResponseEntity.ok(messageAlert);
    }
}
