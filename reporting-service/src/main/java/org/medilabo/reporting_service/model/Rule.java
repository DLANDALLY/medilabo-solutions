package org.medilabo.reporting_service.model;

import lombok.*;

@Data
public class Rule {
    private String name;
    private String gender;
    private Integer minAge;
    private Integer maxAge;
    private Integer minTriggers;
    private Integer maxTriggers;
    private String alertType;
    private String message;
    private String handlerClass;

    public boolean matches(ReportingPatient rp) {
        if (gender != null && !gender.equalsIgnoreCase(rp.getGenre())) return false;
        if (minAge != null && rp.getAge() < minAge) return false;
        if (maxAge != null && rp.getAge() > maxAge) return false;
        if (minTriggers != null && rp.getTriggers() < minTriggers) return false;
        if (maxTriggers != null && rp.getTriggers() > maxTriggers) return false;
        return true;
    }
}
