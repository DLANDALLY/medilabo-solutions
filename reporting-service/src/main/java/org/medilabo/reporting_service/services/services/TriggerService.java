package org.medilabo.reporting_service.services.services;

import org.medilabo.reporting_service.config.BusinessTriggersProperties;
import org.medilabo.reporting_service.model.Trigger;
import org.medilabo.reporting_service.services.interfaces.ITrigger;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TriggerService implements ITrigger {
    private final BusinessTriggersProperties triggersProperties;

    public TriggerService(BusinessTriggersProperties triggersProperties) {
        this.triggersProperties = triggersProperties;
    }

    @Override
    public long countTriggers(List<String> words) {
        Set<String> triggers = new HashSet<>(getAllTriggers());

        return words.stream()
                .map(String::toLowerCase) // normalise si besoin
                .filter(triggers::contains)
                .count();
    }

    private List<String> getAllTriggers(){
        return triggersProperties.getTriggers()
                .stream()
                .map(Trigger::getName)
                .toList();
    }
}
