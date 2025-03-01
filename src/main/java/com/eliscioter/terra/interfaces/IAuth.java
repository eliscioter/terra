package com.eliscioter.terra.interfaces;

import com.eliscioter.terra.model.wrapper.UserResponse;
import jakarta.ws.rs.Produces;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

public interface IAuth {

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> getUsers();
}
