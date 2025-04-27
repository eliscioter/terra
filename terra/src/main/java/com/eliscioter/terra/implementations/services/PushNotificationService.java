package com.eliscioter.terra.implementations.services;

import com.eliscioter.terra.models.requests.NotificationRequest;
import com.eliscioter.terra.models.requests.RegisterDeviceTokenRequest;
import com.eliscioter.terra.models.wrapper.ResponseData;
import com.google.firebase.messaging.FirebaseMessagingException;
import org.springframework.http.ResponseEntity;

public interface PushNotificationService {
    ResponseEntity<ResponseData> send(NotificationRequest notificationRequest) throws FirebaseMessagingException;
    ResponseEntity<ResponseData> sendToAll(NotificationRequest notificationRequest) throws FirebaseMessagingException;
    ResponseEntity<ResponseData> registerToken(RegisterDeviceTokenRequest registerDeviceTokenRequest);
}
