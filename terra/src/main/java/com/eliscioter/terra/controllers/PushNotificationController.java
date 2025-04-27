package com.eliscioter.terra.controllers;

import com.eliscioter.terra.controllers.interfaces.IPushNotificationService;
import com.eliscioter.terra.implementations.services.PushNotificationService;
import com.eliscioter.terra.models.requests.RegisterDeviceTokenRequest;
import com.eliscioter.terra.models.requests.NotificationRequest;
import com.eliscioter.terra.models.wrapper.ResponseData;
import com.google.firebase.messaging.FirebaseMessagingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PushNotificationController implements IPushNotificationService {

    PushNotificationService pushNotificationService;

    public PushNotificationController(PushNotificationService pushNotificationService) {
        this.pushNotificationService = pushNotificationService;
    }

    @Override
    public ResponseEntity<ResponseData> sendPushNotification(NotificationRequest notificationRequest) throws FirebaseMessagingException {
        return pushNotificationService.send(notificationRequest);
    }

    @Override
    public ResponseEntity<ResponseData> sendToAllPushNotification(NotificationRequest notificationRequest) throws FirebaseMessagingException {
        return pushNotificationService.sendToAll(notificationRequest);
    }

    @Override
    public ResponseEntity<ResponseData> registerDeviceToken(RegisterDeviceTokenRequest registerDeviceTokenRequest) {
        return pushNotificationService.registerToken(registerDeviceTokenRequest);
    }
}
