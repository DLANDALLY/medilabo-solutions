package org.medilabo.reporting_service.model;

import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
public class Alert {
    private String type;
    private String message;
}
