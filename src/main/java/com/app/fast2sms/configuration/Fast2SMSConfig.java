package com.app.fast2sms.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Fast2SMSConfig {
    @Value("${fast2sms.api.key}")
    private String apiKey;



    public String getApiKey() {
        return apiKey;
    }
}
