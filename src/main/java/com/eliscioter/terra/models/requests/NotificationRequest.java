package com.eliscioter.terra.models.requests;

public class NotificationRequest {

    private String topic;
    private String title;
    private String body;

    public String getTopic() {
        return topic;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
}
