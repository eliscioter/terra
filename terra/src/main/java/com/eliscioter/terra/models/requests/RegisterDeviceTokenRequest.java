package com.eliscioter.terra.models.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegisterDeviceTokenRequest {
    @JsonProperty("fcm_token")
    private String fcmToken;

    @JsonProperty("correlation_token")
    private String correlationToken;

    public String getFcmToken() {
        return fcmToken;
    }

    public String getCorrelationToken() { return correlationToken; }
}
