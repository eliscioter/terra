package com.eliscioter.terra.interfaces;

import com.eliscioter.terra.models.requests.CreateUserRequest;
import com.eliscioter.terra.models.wrapper.ResponseData;
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
public interface IUserService {

    @GetMapping(value = "/fetchAll")
    ResponseEntity<ResponseData> getUsers();

    @GetMapping(value = "/{id}")
    ResponseEntity<ResponseData> getUser(@PathVariable UUID id);

    @PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
    ResponseEntity<ResponseData> createUser(@RequestBody CreateUserRequest createUserRequest);

    @PatchMapping(value = "/update/{id}", consumes = "application/json", produces = "application/json")
    ResponseEntity<ResponseData> updateUser(@PathVariable("id") UUID id, @RequestBody CreateUserRequest updateUserRequest);

    @DeleteMapping(value = "/delete/{id}")
    ResponseEntity<ResponseData> deleteUser(@PathVariable UUID id, @RequestBody CreateUserRequest deleteUserRequest);
}
