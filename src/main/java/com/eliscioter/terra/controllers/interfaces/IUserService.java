package com.eliscioter.terra.controllers.interfaces;

import com.eliscioter.terra.models.requests.CreateUserRequest;
import com.eliscioter.terra.models.requests.UpdateUserRequest;
import com.eliscioter.terra.models.wrapper.ResponseData;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@RequestMapping(value = "/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface IUserService {

    @GetMapping(value = "/fetchAll")
    ResponseEntity<ResponseData> getUsers();

    @GetMapping(value = "/{id}")
    ResponseEntity<ResponseData> getUser(@PathVariable UUID id);

    @PostMapping(value = "/create")
    ResponseEntity<ResponseData> createUser(@Valid @RequestBody CreateUserRequest createUserRequest);

    @PatchMapping(value = "/update/{id}")
    ResponseEntity<ResponseData> updateUser(@PathVariable("id") UUID id, @Valid @RequestBody UpdateUserRequest updateUserRequest);

    @DeleteMapping(value = "/delete/{id}")
    ResponseEntity<ResponseData> deleteUser(@PathVariable UUID id);
}
