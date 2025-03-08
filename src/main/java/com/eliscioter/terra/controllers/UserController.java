package com.eliscioter.terra.controllers;

import com.eliscioter.terra.implementations.services.UserService;
import com.eliscioter.terra.controllers.interfaces.IUserService;
import com.eliscioter.terra.models.requests.CreateUserRequest;
import com.eliscioter.terra.models.requests.UpdateUserRequest;
import com.eliscioter.terra.models.wrapper.ResponseData;
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
        return userService.fetchedUsers();
    }

    @Override
    public ResponseEntity<ResponseData> getUser(UUID id) {
        return userService.fetchedUser(id);
    }

    @Override
    public ResponseEntity<ResponseData> createUser(CreateUserRequest createUserRequest) {
        return userService.createdUser(createUserRequest);
    }

    @Override
    public ResponseEntity<ResponseData> updateUser(UUID id, UpdateUserRequest updateUserRequest) {
        return userService.updateUser(id, updateUserRequest);
    }

    @Override
    public ResponseEntity<ResponseData> deleteUser(UUID id) {
        return userService.deleteUser(id);
    }
}
