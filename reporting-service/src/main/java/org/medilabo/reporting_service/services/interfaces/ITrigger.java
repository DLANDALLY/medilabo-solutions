package org.medilabo.reporting_service.services.interfaces;

import java.util.List;

public interface ITrigger {
    long countTriggers(List<String> words);
}
