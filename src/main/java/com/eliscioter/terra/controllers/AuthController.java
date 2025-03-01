package com.eliscioter.terra.controllers;

import com.eliscioter.terra.interfaces.IAuth;
import com.eliscioter.terra.model.dto.UserDTO;
import com.eliscioter.terra.model.wrapper.UserResponse;
import com.eliscioter.terra.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/login")
public class AuthController implements IAuth {

    UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<UserResponse> getUsers() {
        List<UserDTO> fetchedUsers = userRepository
                .findAll()
                .stream()
                .map(UserDTO::fromUser).collect(Collectors.toList());

        if (fetchedUsers.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new UserResponse("No Users found"));
        }

        return ResponseEntity.ok(new UserResponse(fetchedUsers));
    }
}
