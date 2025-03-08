package com.eliscioter.terra.implementations.impl;

import com.eliscioter.terra.implementations.services.PushNotificationService;
import com.eliscioter.terra.models.requests.RegisterDeviceTokenRequest;
import com.eliscioter.terra.models.entity.RegisteredDeviceEntity;
import com.eliscioter.terra.models.requests.NotificationRequest;
import com.eliscioter.terra.models.wrapper.ResponseData;
import com.eliscioter.terra.repositories.RegisteredDevice;
import com.google.firebase.messaging.BatchResponse;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.MulticastMessage;
import com.google.firebase.messaging.Notification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PushNotificationImpl implements PushNotificationService {

    FirebaseMessaging firebaseMessaging;
    private RegisteredDevice registeredDevice;

    public PushNotificationImpl(FirebaseMessaging firebaseMessaging, RegisteredDevice registeredDevice) {
        this.firebaseMessaging = firebaseMessaging;
        this.registeredDevice = registeredDevice;
    }

    @Override
    public ResponseEntity<ResponseData> send(NotificationRequest notificationRequest) {
        return null;
    }

    @Override
    public ResponseEntity<ResponseData> sendToAll(NotificationRequest notificationRequest) throws FirebaseMessagingException {
        Collection<String> tokens = registeredDevice.findAllDeviceToken();

        Notification notification = Notification.builder()
                .setTitle(notificationRequest.getTitle())
                .setBody(notificationRequest.getBody())
                .build();

        MulticastMessage multicastMessage = MulticastMessage.builder()
                .addAllTokens(tokens)
                .setNotification(notification)
                .build();

        BatchResponse response = firebaseMessaging.sendEachForMulticast(multicastMessage);
        if(response.getSuccessCount() == 0) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseData().add("message", "Error sending notification"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseData().add("message", "Notification sent!"));
    }

    @Override
    public ResponseEntity<ResponseData> registerToken(RegisterDeviceTokenRequest registerDeviceTokenRequest) {
        RegisteredDeviceEntity token = new RegisteredDeviceEntity();
        token.setDeviceToken(registerDeviceTokenRequest.getToken());
        registeredDevice.save(token);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseData().add("message", "Device token is registered successfully!"));
    }
}
