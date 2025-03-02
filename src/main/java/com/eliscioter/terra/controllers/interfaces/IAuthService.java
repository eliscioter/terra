package com.eliscioter.terra.controllers.interfaces;

import com.eliscioter.terra.models.requests.LoginRequest;
import com.eliscioter.terra.models.wrapper.ResponseData;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping(value = "/login")
public interface IAuthService {
    @PostMapping(consumes = "application/json", produces = "application/json")
    ResponseEntity<ResponseData> login(@Valid @RequestBody LoginRequest loginRequest);
}
