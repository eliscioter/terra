package com.eliscioter.terra.controllers;

import com.eliscioter.terra.model.entity.UserEntity;
import com.eliscioter.terra.model.wrapper.ResponseWrapper;
import com.eliscioter.terra.repositories.UserRepository;
import jakarta.ws.rs.Produces;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/login")
public class AuthController {

    UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseWrapper<List<UserEntity>>> getUsers() {
            List<UserEntity> fetchedUsers = userRepository.findAll();

            if (fetchedUsers.isEmpty()) {
                return ResponseEntity.ok(new ResponseWrapper<>(false, null, "No Users Found"));
            }
            return ResponseEntity.ok(new ResponseWrapper<>(true, fetchedUsers, "Success"));
    }
}
