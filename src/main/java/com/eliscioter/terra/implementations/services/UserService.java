package com.eliscioter.terra.implementations.services;

import com.eliscioter.terra.models.requests.CreateUserRequest;
import com.eliscioter.terra.models.wrapper.ResponseData;

import java.util.UUID;

public interface UserService {
    ResponseData fetchedUsers();
    ResponseData fetchedUser(UUID id);
    ResponseData createdUser(CreateUserRequest createUserRequest);
    ResponseData updateUser(UUID id, CreateUserRequest updateUserRequest);
    ResponseData deleteUser(UUID id, CreateUserRequest deleteUserRequest);
}
