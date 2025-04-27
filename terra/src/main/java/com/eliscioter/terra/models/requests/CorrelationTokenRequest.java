package com.eliscioter.terra.models.requests;

public class CorrelationTokenRequest {

    private String correlationToken;

    public CorrelationTokenRequest(String correlationToken) {
        this.correlationToken = correlationToken;
    }

    public String getCorrelationToken() {
        return correlationToken;
    }

    public void setCorrelationToken(String correlationToken) {
        this.correlationToken = correlationToken;
    }

    @Override
    public String toString() {
        return "CorrelationTokenRequest{" +
                "token:\"" + correlationToken + "\"" +
                '}';
    }
}
