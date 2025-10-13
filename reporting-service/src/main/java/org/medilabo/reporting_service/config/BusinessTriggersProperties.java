package org.medilabo.reporting_service.config;

import lombok.Getter;
import lombok.Setter;
import org.medilabo.reporting_service.model.Trigger;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Getter @Setter
@Configuration
@ConfigurationProperties(prefix = "business-triggers")
public class BusinessTriggersProperties {
    private List<Trigger> triggers;
}
