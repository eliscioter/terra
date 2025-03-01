package com.eliscioter.terra.controllers;

import com.eliscioter.terra.implementations.services.UserService;
import com.eliscioter.terra.interfaces.IUserService;
import com.eliscioter.terra.models.requests.CreateUserRequest;
import com.eliscioter.terra.models.wrapper.ResponseData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class UserController implements IUserService {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<ResponseData> getUsers() {
        return ResponseEntity.ok(userService.fetchedUsers());
    }

    @Override
    public ResponseEntity<ResponseData> getUser(UUID id) {
        return ResponseEntity.ok(userService.fetchedUser(id));
    }

    @Override
    public ResponseEntity<ResponseData> createUser(CreateUserRequest createUserRequest) {
        ResponseData responseData = userService.createdUser(createUserRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseData);
    }

    @Override
    public ResponseEntity<ResponseData> updateUser(UUID id, CreateUserRequest updateUserRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.updateUser(id, updateUserRequest));
    }

    @Override
    public ResponseEntity<ResponseData> deleteUser(UUID id, CreateUserRequest deleteUserRequest) {
        return ResponseEntity.ok(userService.deleteUser(id, deleteUserRequest));
    }
}
