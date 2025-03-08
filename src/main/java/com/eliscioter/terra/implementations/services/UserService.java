package com.eliscioter.terra.implementations.services;

import com.eliscioter.terra.models.requests.CreateUserRequest;
import com.eliscioter.terra.models.requests.UpdateUserRequest;
import com.eliscioter.terra.models.wrapper.ResponseData;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface UserService {
    ResponseEntity<ResponseData> fetchedUsers();
    ResponseEntity<ResponseData> fetchedUser(UUID id);
    ResponseEntity<ResponseData> createdUser(CreateUserRequest createUserRequest);
    ResponseEntity<ResponseData> updateUser(UUID id, UpdateUserRequest updateUserRequest);
    ResponseEntity<ResponseData> deleteUser(UUID id);
}
