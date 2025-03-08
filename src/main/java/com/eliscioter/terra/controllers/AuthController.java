package com.eliscioter.terra.controllers;

import com.eliscioter.terra.controllers.interfaces.IAuthService;
import com.eliscioter.terra.implementations.services.AuthService;
import com.eliscioter.terra.models.requests.LoginRequest;
import com.eliscioter.terra.models.wrapper.ResponseData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController implements IAuthService {
    AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public ResponseEntity<ResponseData> login(LoginRequest loginRequest) {
        return authService.auth(loginRequest);
    }
}
