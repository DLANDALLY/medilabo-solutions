package org.medilabo.reporting_service.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Getter @Setter
@Configuration
@ConfigurationProperties(prefix = "business-rules")
public class BusinessRulesProperties {
    private List<RuleConfig> rules;

}
