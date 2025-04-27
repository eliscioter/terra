package com.eliscioter.terra.implementations.services;

import com.eliscioter.terra.models.requests.LoginRequest;
import com.eliscioter.terra.models.wrapper.ResponseData;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<ResponseData> auth(LoginRequest loginRequest);
}
