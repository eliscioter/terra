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
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.MulticastMessage;
import com.google.firebase.messaging.Notification;
import com.google.gson.Gson;
import org.hibernate.engine.jdbc.batch.spi.Batch;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class PushNotificationImpl implements PushNotificationService {

    FirebaseMessaging firebaseMessaging;
    private RegisteredDevice registeredDevice;
    private final RestTemplate restTemplate;

    @Value("${django.server.url}")
    private String djangoUrl;

    public PushNotificationImpl(FirebaseMessaging firebaseMessaging, RegisteredDevice registeredDevice,
                                RestTemplate restTemplate) {
        this.firebaseMessaging = firebaseMessaging;
        this.registeredDevice = registeredDevice;
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseEntity<ResponseData> send(NotificationRequest notificationRequest) throws FirebaseMessagingException {
        RegisteredDeviceEntity deviceEntity = registeredDevice
                .findByCorrelationToken(notificationRequest.getCorrelationId() + "1");

        if (deviceEntity == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseData().add("message", "Device not registered"));
        }

        Notification notification = Notification.builder()
                .setTitle(notificationRequest.getTitle())
                .setBody(notificationRequest.getBody())
                .build();

        Message message = Message.builder()
                .setToken(deviceEntity.getDeviceToken())
                .setNotification(notification)
                .build();

        firebaseMessaging.send(message);

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseData().add("message", "Notification sent!"));

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
        token.setDeviceToken(registerDeviceTokenRequest.getFcmToken());
        token.setCorrelationToken(registerDeviceTokenRequest.getCorrelationToken());
        registeredDevice.save(token);

        sendCorrelationTokenToApi(token.getCorrelationToken());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseData().add("message", "Device token is registered successfully!"));
    }

    private void sendCorrelationTokenToApi(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> request = new HashMap<>();
        request.put("token", token);

        Gson gson = new Gson();
        String jsonPayload = gson.toJson(request);

        HttpEntity<String> entity = new HttpEntity<>(jsonPayload, headers);

        restTemplate.exchange(djangoUrl, HttpMethod.POST, entity, String.class);
    }
}
