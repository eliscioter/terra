package com.eliscioter.terra.controllers;

import com.eliscioter.terra.commons.utils.JWTUtils;
import com.eliscioter.terra.controllers.interfaces.IAuthService;
import com.eliscioter.terra.implementations.impl.UserDetailsImpl;
import com.eliscioter.terra.implementations.services.AuthService;
import com.eliscioter.terra.models.requests.LoginRequest;
import com.eliscioter.terra.models.wrapper.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController implements IAuthService {

    @Autowired
    AuthService authService;

    @Override
    public ResponseEntity<ResponseData> login(LoginRequest loginRequest) {
        return authService.auth(loginRequest);
    }
}
