package com.conference.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class ConferenceConfig {
    private static ConferenceConfig instance;
    private final Map<String, String> configuration;

    private ConferenceConfig() {
        configuration = new HashMap<>();
        // Default configurations
        configuration.put("maxSessionCapacity", "100");
        configuration.put("defaultCurrency", "USD");
        configuration.put("timeZone", "UTC");
    }

    public static synchronized ConferenceConfig getInstance() {
        if (instance == null) {
            instance = new ConferenceConfig();
        }
        return instance;
    }

    public String getConfig(String key) {
        return configuration.get(key);
    }

    public void setConfig(String key, String value) {
        configuration.put(key, value);
    }
} 