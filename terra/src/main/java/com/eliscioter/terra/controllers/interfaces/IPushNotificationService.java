package com.eliscioter.terra.controllers.interfaces;

import com.eliscioter.terra.models.requests.RegisterDeviceTokenRequest;
import com.eliscioter.terra.models.requests.NotificationRequest;
import com.eliscioter.terra.models.wrapper.ResponseData;
import com.google.firebase.messaging.FirebaseMessagingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/notification")
public interface IPushNotificationService {

    @PostMapping("/send")
    ResponseEntity<ResponseData> sendPushNotification(@RequestBody NotificationRequest notificationRequest) throws FirebaseMessagingException;

    @PostMapping("/send-to-all")
    ResponseEntity<ResponseData> sendToAllPushNotification(@RequestBody NotificationRequest notificationRequest) throws FirebaseMessagingException;

    @PostMapping("/register-device-token")
    ResponseEntity<ResponseData> registerDeviceToken(@RequestBody RegisterDeviceTokenRequest registerDeviceTokenRequest);
}
