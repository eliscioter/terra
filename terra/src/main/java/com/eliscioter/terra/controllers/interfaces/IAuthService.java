package com.eliscioter.terra.controllers.interfaces;

import com.eliscioter.terra.implementations.impl.UserDetailsImpl;
import com.eliscioter.terra.models.requests.LoginRequest;
import com.eliscioter.terra.models.requests.TokenRefreshRequest;
import com.eliscioter.terra.models.wrapper.ResponseData;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@RequestMapping(value = "/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface IAuthService {

    @PostMapping(value = "/login")
    ResponseEntity<ResponseData> login(@Valid @RequestBody LoginRequest loginRequest);

    @PostMapping(value = "/refresh")
    ResponseEntity<ResponseData> refreshToken(@Valid @RequestBody TokenRefreshRequest tokenRefreshRequest);

    @PostMapping(value = "/logout")
    ResponseEntity<ResponseData> logout();
}
