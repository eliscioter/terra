package com.eliscioter.terra.controllers.interfaces;

import com.eliscioter.terra.models.requests.LoginRequest;
import com.eliscioter.terra.models.wrapper.ResponseData;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/login")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface IAuthService {
    @PostMapping()
    ResponseEntity<ResponseData> login(@Valid @RequestBody LoginRequest loginRequest);
}
